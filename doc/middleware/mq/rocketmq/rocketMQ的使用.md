# RocketMQ的使用
> RocketMQ作为阿里开源的一款高性能、高吞吐量的消息中间件。

**名词**
1. topic : 定义的通道
2. messagequeue：一个topic可以有多个messagequeue
3. broker：MQ的核心存储消息同步消息push消息的组件
4. product：消息生产者
5. consumer：消费者
6. nameserver：做消费者生产者broker服务发现和元数据的存储，优点像redis 的sentinel但是没有选举和故障转移的功能

## RocketMQ单机架构

<div align="center"> <img src="https://github.com/gitXugx/doc-images/blob/master/images/redis/redis%E4%B8%BB%E4%BB%8E%E5%A4%8D%E5%88%B6.jpg"  /> </div><br>

可以看到上述的名词，但是单机架构只能适用于开发或者测试环境，不能作为生产环境的架构，因为不具有高可用。为什么单机架构下还需要nameServer组件的服务发现？


**nameServer主要作用**
在行业中有很多可以替代nameServer的中间件，为什么不用zk来作为服务发现注册和元数据的保存呢？
zk属于cp模型，在发生脑裂的时候会导致部分zk不可用，消费者无法获取对应broker。可能最主要的是这个原因

nameServer是无状态的所有的数据都会由每个broker上传元数据，因为在配置文件中配置有所有的nameServer，主要在内存中存储topic对应的broker信息、brokername对应的master和slave的信息、brokerAdress和broker的实时信息、clusterName和brokerName的映射，当客户端来访问时：
1. 通过topic获取到broker信息
2. 然后进行获取broker的地址

## RocketMQ的高可用架构

<div align="center"> <img src="https://github.com/gitXugx/doc-images/blob/master/images/redis/redis%E4%B8%BB%E4%BB%8E%E5%A4%8D%E5%88%B6.jpg"  /> </div><br>






