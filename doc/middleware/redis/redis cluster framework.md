# redis culster
> 在redis 集群中除了 sentinel模式 还有就是 redis culster


`redis sentinel` 解决了高可用，故障转移，但是 `redis sentinel` 存储的数据有限，写操作和数据量依然是未解决的瓶颈，而redis culster就是解决这个问题的。

**redis culster 提供了哪些功能**
1. `master` 可以横向扩展，并且master可以挂备份从节点
2. 使用比一致性hash更为简单的solt来对数据进行分片
3. master的扩展使得写qps得到突破


## redis culster 模式



1. 上面为3台集群主节点和3台备份从节点，这是最小的集群，因为在集群中如果主节点小于3个，在主节点宕机后无法做投票选举从节点晋升为主节点，导致全集群不可用
2. 上面每个备份节点未给主节点部署同一个机器，为了提高可用性
3. 在 `redis sentinel` 模式下故障转移是sentinel来进行，sentinel的发现是通过pub/sub 来进行做发现的，使用的是gossip协议，在 `redis culster` 中同样的也通过gossip协议进行集群master发现。
4. 在 `redis sentinel` 中故障转移是sentinel来进行选举和监控，在 `redis culster` 中监控是每个主节点来互相监控，如果主节点未发现宕机，从节点会自动发起选举投票，然后主节点进行选举，如果选举成功，从节点会宣布自己为主节点。



## 客户端








