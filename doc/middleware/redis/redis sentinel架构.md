# redis 集群
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
2. 当从节点启动后，会创建socket，每秒定时尝试与主节点建立socket长连接，如果该连接可用，则进行复制操作
3. 复制分为两种全量复制、部分复制，如果从节点第一次连接主节点会发送 `psync ？ -1` psync 为同步命令 ? 是对应复制的redis runid -1 是从节点的偏移量。如果是网络波动或者网络断开时，当网络恢复的时候，从节点会向主节点发送 psync runid offset 来向主节点获取数据，如果主节点判断 `从节点offset+复制积压区大小 < 主节点offset`
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

min-slaves-to-write 3与min-slaves-max-lag 10：规定了主节点的最小从节点数目及对应的最大延迟，如果从节点小于3或者所有节点的同步延迟大于10，则不会对外提供写服务









