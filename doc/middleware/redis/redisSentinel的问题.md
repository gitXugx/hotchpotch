# Redis Sentinel 模式的问题
> redis 有两种高可用方案，一种是redis Sentinel 和Redis Cluster 。

Sentinel 模式也redis官方提供的一种高可用方案具体实现是主从复制



## Sentinel 具体做什么工作

1. 监控: 负责监控redis服务器是否正常运行
2. 提醒：如果Redis服务器出现问题，可以通过Api向其他应用程序发送通知。
3. 故障转移：如果主服务器宕机的情况下，需要从salve服务器中选择一个作为master服务器，让其他从服务器复制新的主服务器，当客户端请求的时候会返回给客户端新的服务器地址


**监控redis服务是否正常运行是如何实现的？**












