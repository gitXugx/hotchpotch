# redis sentinel模式
> redis的集群实现官方提供两种方式，一种是redis sentinel 和redis culster

## redis sentinel模式
> redis 提供replication和sentinel来达到redis集群

**两种的作用**
1. replication: 实现了主从架构和读写分离
2. sentinel: 实现了监控、故障转移、提醒的功能

### replication模式

主从架构: 

<div align="center"> <img src="https://github.com/gitXugx/doc-images/blob/master/images/redis/redis%E4%B8%BB%E4%BB%8E%E5%A4%8D%E5%88%B6.jpg"  /> </div><br>

**从节点的启动**

1. 主节点不需要知道从节点，在从节点中需要配置 `slaveof <masterip> <masterport> ` 来进行设置主节点。设置master节点有多种方式，也可以启动时指定 slaveof 或者 redis-cli slaveof 来设置
2. 当从节点启动后，会创建socket，每秒定时尝试与主节点建立socket长连接，发送Ping命令如果该连接可用，有身份验证则进行身份验证。发送自己的节点信息。下面进行复制操作
3. 复制分为两种全量复制、部分复制，如果从节点第一次连接主节点会发送 `psync ？ -1`命令， `psync` 为同步命令 、`?` 是对应复制的redis runid、 `-1` 是从节点的偏移量。如果是网络波动或者网络断开时，当网络恢复的时候，从节点会向主节点发送 psync runid offset 来向主节点获取数据，如果主节点判断 `从节点offset+复制积压区大小 < 主节点offset`
则会进行全量复制，否则进行部分复制即可
4. 正常主节点发送给从节点写命令的时候还会给复制缓存区发送一份，以给offset请求的从节点进行部分同步


<div align="center"> <img src="https://github.com/gitXugx/doc-images/blob/master/images/redis/redis%E4%B8%BB%E4%BB%8E%E5%A4%8D%E5%88%B6%E6%B5%81%E7%A8%8B.jpg" /> </div><br>


**复制存在的问题**
1. bgsave去生成RDB快照是一个消耗资源的操作，如果RDB过大会造成发送给从节点超时，导致从节点一直重新连接，一直超时
2. 在从节点读取RDB文件的时候是不对外提供服务的，如果所有从节点在同一时间全量同步会导致无法读取。
3. 数据不一致问题，因为主从复制是异步，也有可能因为网络原因导致offset相差较大，slave-serve-stale-data该参数是设置如果从节点正在同步操作是否对客户端提供服务，如果在一致性较强的情况下可以设置为no。
4. 如果从节点较多的情况下，会导致主节点同步压力过大，但是如果使用树形结构，虽然解决了主节点压力问题，但是数据不一致的情况会更多
5. 版本问题，在3.2以前从节点是不会校验数据是否过期，导致可以获取过期的数据，在3.2及以后从节点读取也会校验是否过期。
6. 故障切换问题，可以手动切换，也可自动切换，官方有提供sentinel来实现故障切换
7. 从节点的部分同步是通过 runid来确定主节点，如果runid发生变化就会全力同步，那么重启主节点使用debug reload命令即可

### 命令心跳机制

在`redis replication` 模式下，实际上是有维护心跳，master会定时去向slave发送ping，发送的间隔是 `repl-ping-slave-period` 默认是10s一次，从节点会向主系欸但发送 `REPLCONF ACK` ，频率是每秒一次 `REPLCONF ACK{offset}` offset是从节点的数据偏移位置。

<div align="center"> <img src="https://github.com/gitXugx/doc-images/blob/master/images/redis/redis%20master%20slave%E5%BF%83%E8%B7%B3.jpg" /> </div><br>

上面的心跳机制涉及两个配置:
1. min-slaves-to-write 最小从节点数量以上主节点才会写数据，根据主节点去ping来判断从节点还有多少个。
2. min-slaves-max-lag 所有从节点lag延迟小于多少主节点才进行写操作, 延迟是通过从节点发送 `REPLCONF ACK` 来判断。
3. repl-timeout 超时机制
   
通过这两个配置来适当提高服务可用性
在主节点向从节点发送数据的时候还涉及到数据是否打包发送，如果打包发送网络压力会变小，但是数据一致性会变弱，不打包相反
1. repl-disable-tcp-nodelay 是否打包合并发送

### 复制模式的配置

**数据相关配置**

1. repl-disable-tcp-nodelay 是否打包发送。业务对一致性要求差的设置为true 减小网络带宽，相反
2. slave-serve-stale-data 从节点正在同步数据是否对外提供服务，对一致性要求高的可以设置为no，从节点设置
3. 主节点从起命令如果不希望数据丢失或者故障转移，可以用debug reload 来进行重启
4. min-slaves-max-lag 来保证从节点能积极同步数据。从而保证数据尽可能一致
5. slave-read-only yes 从节点的配置，只读，不然会造成数据不一致
**可用性**

1. min-slaves-to-write 当从节点很少的时候，主节点不提供写服务。因为从节点少的情况下，可能因为同步或者全量复制而无法对外提供服务
2. client-output-buffer-limit slave 256MB 64MB 60，其含义是：如果buffer大于256MB，或者连续60s大于64MB，则主节点会断开与该从节点的连接，主要是rdb发送或者命令发送，如果rdb文件比较大的情况下可以设置大一些，以防一直断开重连同步的问题
3. repl-backlog-size 设置缓存积压区，只有一个，里面有主节点的offset ，如果设置较小可能会导致，从节点频繁全量同步。

```yml

#当从库同主机失去连接或者复制正在进行，从机库有两种运行方式：1) 如果slave-serve-stale-data设置为yes(默认设置)，从库会继续响应客户端的请求。2) 如果slave-serve-stale-data设置为no，除去INFO和SLAVOF命令之外的任何请求都会返回一个错误”SYNC with master in progress”。
slave-serve-stale-data yes
#作为从服务器，默认情况下是只读的（yes），可以修改成NO，用于写（不建议）。
slave-read-only yes
#是否使用socket方式复制数据。目前redis复制提供两种方式，disk和socket。如果新的slave连上来或者重连的slave无法部分同步，就会执行全量同步，master会生成rdb文件。有2种方式：disk方式是master创建一个新的进程把rdb文件保存到磁盘，再把磁盘上的rdb文件传递给slave。socket是master创建一个新的进程，直接把rdb文件以socket的方式发给slave。disk方式的时候，当一个rdb保存的过程中，多个slave都能共享这个rdb文件。socket的方式就的一个个slave顺序复制。在磁盘速度缓慢，网速快的情况下推荐用socket方式。
repl-diskless-sync no

#diskless复制的延迟时间，防止设置为0。一旦复制开始，节点不会再接收新slave的复制请求直到下一个rdb传输。所以最好等待一段时间，等更多的slave连上来。
repl-diskless-sync-delay 5

#主节点向从节点发送ping的间隔时间，它的设置会影响到repl-timeout
repl-ping-slave-period 10

#复制连接超时时间。master和slave都有超时时间的设置。master检测到slave上次发送的时间超过repl-timeout，即认为slave离线，清除该slave信息。slave检测到上次和master交互的时间超过repl-timeout，则认为master离线。需要注意的是repl-timeout需要设置一个比repl-ping-slave-period更大的值，不然会经常检测到超时。
repl-timeout 60

#是否禁止数据打包发送，如果对数据一致性要求比较高，则不打包
repl-disable-tcp-nodelay no
#缓存积压区的大小，设置过小会导致频繁做全量复制
repl-backlog-size 5mb

#master在没有从节点的情况下，多长时间释放缓存积压区
repl-backlog-ttl 3600

#当master不可用，Sentinel会根据slave的优先级选举一个master。最低的优先级的slave，当选master。而配置成0，永远不会被选举。
slave-priority 100

#从节点总数大于3主节点才提供写服务
min-slaves-to-write 3
#从节点延迟小于10s 才对外提供写服务
min-slaves-max-lag 10
# 缓存区设置大小，如果数据比较大建议设置大一些
client-output-buffer-limit slave 256MB 64MB 60
```



### replication模式总结
复制模式解决了数据同步的问题，数据只保证最终一致性。只能通过上面的配置去调整数据一致性的强度。其次从节点无法做到扩容，主节点单机存储能力有限，无法做到故障转移，写操作无法做到负载，如果需要这些redis提供了 sentinel和culster两种

### sentinel模式 

**snetinel主要做什么**

1. 监控: 负责监控redis服务器是否正常运行
2. 提醒：如果Redis服务器出现问题，可以通过Api向其他应用程序发送通知。
3. 故障转移：如果主服务器宕机的情况下，需要从salve服务器中选择一个作为master服务器，让其他从服务器复制新的主服务器，当客户端请求的时候会返回给客户端新的服务器地址


**sentinel的实现原理**


**监控**

sentinel在后台主要有3个定时任务来进行监控:

1. 通过redis的 `pub/sub` 每2秒一次来进行sentinel之间的 `sentinel` 发现和master配置的更新比较

sentinel自动发现

<div align="center"> <img src="https://github.com/gitXugx/doc-images/blob/master/images/redis/redis%20sentinel%E7%9A%84%E8%87%AA%E5%8A%A8%E5%8F%91%E7%8E%B0.jpg" /> </div><br>

2. 通过向master每10秒发送`info`命令来获取主从结构，获取到主从节点的信息，如果主节点被标记为客观下线，则sentinel会缩短至每秒一次
3. 通过ping主节点或者从节点，监控节点是否下线，如果节点超过了down-after-milliseconds，这个实例会标记为主观下线，如果是从节点会直接从列表中剔除，如果是主节点则进行主观下线判断

主观下线和客观下线

<div align="center"> <img src="https://github.com/gitXugx/doc-images/blob/master/images/redis/redis%20sentinel%20%E5%AE%A2%E8%A7%82%E4%B8%8B%E7%BA%BF%E5%92%8C%E4%B8%BB%E8%A7%82%E4%B8%8B%E7%BA%BF.jpg" /> </div><br>

**故障转移**
故障转移
一次故障转移操作由以下步骤组成：

<div align="center"> <img src="https://github.com/gitXugx/doc-images/blob/master/images/redis/redis%20sentinel%20master%E6%95%85%E9%9A%9C%E8%BD%AC%E7%A7%BB.jpg" /> </div><br>

选出一个从服务器，并将它升级为主服务器。
1. 向被选中的从服务器发送 SLAVEOF NO ONE 命令，让它转变为主服务器。
2. 通过发布与订阅功能， 将更新后的配置传播给所有其他 Sentinel ， 其他 Sentinel 对它们自己的配置进行更新。
3. 向已下线主服务器的从服务器发送 SLAVEOF 命令， 让它们去复制新的主服务器。
4. 当所有从服务器都已经开始复制新的主服务器时， 领头 Sentinel 终止这次故障迁移操作。
每当一个 Redis 实例被重新配置（reconfigured） —— 无论是被设置成主服务器、从服务器、又或者被设置成其他主服务器的从服务器 —— Sentinel 都会向被重新配置的实例发送一个 CONFIG REWRITE 命令， 从而确保这些配置会持久化在硬盘里。

**master选举**
Sentinel 使用以下规则来选择新的主服务器：

<div align="center"> <img src="https://github.com/gitXugx/doc-images/blob/master/images/redis/redis%20sentinel%20master%E9%80%89%E4%B8%BE.jpg" /> </div><br>

1. 在失效主服务器属下的从服务器当中， 那些被标记为主观下线、已断线、或者最后一次回复 PING 命令的时间大于五秒钟的从服务器都会被淘汰。
2. 在失效主服务器属下的从服务器当中， 那些与失效主服务器连接断开的时长超过 down-after 选项指定的时长十倍的从服务器都会被淘汰。
3. 在经历了以上两轮淘汰之后剩下来的从服务器中， 我们选出复制偏移量（replication offset）最大的那个从服务器作为新的主服务器； 如果复制偏移量不可用， 或者从服务器的复制偏移量相同， 那么带有最小运行 ID 的那个从服务器成为新的主服务器。


**Sentinel 自动故障迁移的一致性**

**把Sentinel 配置看作是一个带有版本号的状态。 每个Sentinel都可能写自己的配置具有版本号，然后发送到队列中，如果发现比自己配置高的版本号则更新自己的配置。**

当出现网络分割时， 一个 Sentinel 可能会包含了较旧的配置， 而当这个 Sentinel 接到其他 Sentinel 发来的版本更新的配置时， Sentinel 就会对自己的配置进行更新。

<div align="center"> <img src="https://github.com/gitXugx/doc-images/blob/master/images/redis/redis%20sentinel%20%E7%BD%91%E7%BB%9C%E5%88%86%E5%8C%BA.jpg" /> </div><br>

如果要在网络分割出现的情况下仍然保持一致性， 那么应该使用 min-slaves-to-write 选项， 让主服务器在连接的从实例少于给定数量时停止执行写操作， 与此同时， 应该在每个运行 Redis 主服务器或从服务器的机器上运行 Redis Sentinel 进程。









