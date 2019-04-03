# redis culster
> 在redis 集群中除了 sentinel模式 还有就是 redis culster


`redis sentinel` 解决了高可用，故障转移，但是 `redis sentinel` 存储的数据有限，写操作和数据量依然是未解决的瓶颈，而redis culster就是解决这个问题的。

**redis culster 提供了哪些功能**
1. `master` 可以横向扩展，并且master可以挂备份从节点
2. 使用比一致性hash更为简单的solt来对数据进行分片
3. master的扩展使得写qps得到突破

## redis culster 模式

<div align="center"> <img src="https://github.com/gitXugx/doc-images/blob/master/images/redis/redis%20culster%20%E6%A8%A1%E5%BC%8F.jpg" /> </div><br>

1. 上面为3台集群主节点和3台备份从节点，这是最小的集群，因为在集群中如果主节点小于3个，在主节点宕机后无法做投票选举从节点晋升为主节点，导致全集群不可用
2. 上面每个备份节点未给主节点部署同一个机器，为了提高可用性
3. 在 `redis sentinel` 模式下故障转移是sentinel来进行，sentinel的发现是通过pub/sub 来进行做发现的，使用的是gossip协议，在 `redis culster` 中同样的也通过gossip协议进行集群master发现。
4. 在 `redis sentinel` 中故障转移是sentinel来进行选举和监控，在 `redis culster` 中监控是每个主节点来互相监控，如果主节点未发现宕机，从节点会自动发起选举投票，然后主节点进行选举，如果选举成功，从节点会宣布自己为主节点。

作为集群模式，可以横向扩展master节点，在设计上很多地方借鉴sentinel的设计。例如:

1. 主节点自动发现使用的是gossip协议， 把sentinel的职责放到 `master` 使master具有互相监控，选举主节点的功能
2. 同样主节点可以有多个从节点进行 `replication模式` 来保证数据备份


**缺点**

1. 没有像 `codis` 的监控页面，监控页面可能需要自研
2. 在单机上面可以做复制key的操作，在集群上不能使用部分命令，实现难度较大
3. 不能做数据合并，因为在多机上可能发生key冲突

## 客户端

一般java客户端有 `jedis` 和 `Lettuce` 在 `spring-boot2.0` 以后redis 客户端默认使用 `Lettuce` 












