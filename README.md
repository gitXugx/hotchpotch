# Hotchpotch
:sweat_drops: java知识的大杂烩


-----
## jvm基础
- [基础知识从字节码解读](https://github.com/gitXugx/hotchpotch/blob/master/doc/jvm/java%E5%9F%BA%E7%A1%80%E7%9F%A5%E8%AF%86.md)
- [类型是什么](https://github.com/gitXugx/hotchpotch/blob/master/doc/jvm/%E7%B1%BB%E5%9E%8B%E7%9A%84%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F.md)
- [.class文件中存储着什么东西](https://github.com/gitXugx/hotchpotch/blob/master/doc/jvm/%E7%B1%BB%E6%96%87%E4%BB%B6%E8%A7%A3%E6%9E%90.md)
- [通常说的运行时数据区里面有哪些东西](https://github.com/gitXugx/hotchpotch/blob/master/doc/jvm/java%E8%BF%90%E8%A1%8C%E6%97%B6%E6%95%B0%E6%8D%AE%E5%8C%BA.md)
- [垃圾收集器是怎样判断垃圾的](https://github.com/gitXugx/hotchpotch/blob/master/doc/jvm/%E5%9E%83%E5%9C%BE%E6%94%B6%E9%9B%86%E5%99%A8.md)
- [类加载器为什么要设计为双亲委派](https://github.com/gitXugx/hotchpotch/blob/master/doc/jvm/%E7%B1%BB%E5%8A%A0%E8%BD%BD%E5%99%A8%E7%9B%B8%E5%85%B3.md)

## java容器源码解读

- [List容器](https://github.com/gitXugx/hotchpotch/blob/master/doc/soure-code/javacontainer/List%E5%AE%B9%E5%99%A8%E8%A7%A3%E6%9E%90.md)
- [Map容器](https://github.com/gitXugx/hotchpotch/blob/master/doc/soure-code/javacontainer/Map%E5%AE%B9%E5%99%A8%E8%A7%A3%E6%9E%90.md)
- [Set容器](https://github.com/gitXugx/hotchpotch/blob/master/doc/soure-code/javacontainer/Set%E5%AE%B9%E5%99%A8%E8%A7%A3%E6%9E%90.md)
- [CopyOnWriteArrayList源码](https://github.com/gitXugx/hotchpotch/blob/master/doc/soure-code/javacontainer/CopyOnWriteArrayList%E6%BA%90%E7%A0%81.md)
- [ConcurrentHashMap源码](https://github.com/gitXugx/hotchpotch/blob/master/doc/soure-code/javacontainer/ConcurrentHashMap%E6%BA%90%E7%A0%81.md)

## java并发源码解读
- [ReentrantLock源码](https://github.com/gitXugx/hotchpotch/blob/master/doc/soure-code/concurren/ReentrantLock%E6%BA%90%E7%A0%81.md)
- [ReentrantReadWriteLock源码](https://github.com/gitXugx/hotchpotch/blob/master/doc/soure-code/concurren/ReentrantReadWriteLock%E6%BA%90%E7%A0%81.md)
- [Semaphore源码](https://github.com/gitXugx/hotchpotch/blob/master/doc/soure-code/concurren/Semaphore%E6%BA%90%E7%A0%81.md)
- [CountDownLatch源码](https://github.com/gitXugx/hotchpotch/blob/master/doc/soure-code/concurren/CountDownLatch%E6%BA%90%E7%A0%81.md)
- [CyclicBarrier源码](https://github.com/gitXugx/hotchpotch/blob/master/doc/soure-code/concurren/CyclicBarrier%E6%BA%90%E7%A0%81.md)
- [ThreadLocal源码](https://github.com/gitXugx/hotchpotch/blob/master/doc/soure-code/concurren/Threadlocal%E6%BA%90%E7%A0%81.md)
- [Executors源码](https://github.com/gitXugx/hotchpotch/blob/master/doc/soure-code/concurren/Executors%E6%BA%90%E7%A0%81.md)
- [BlockingQueue源码](https://github.com/gitXugx/hotchpotch/blob/master/doc/soure-code/concurren/BlockingQueue%E6%BA%90%E7%A0%81.md)
- [ConcurrentLinkedQueue源码](https://github.com/gitXugx/hotchpotch/blob/master/doc/soure-code/concurren/ConcurrentLinkedQueue%E6%BA%90%E7%A0%81.md)
- [Condition源码](https://github.com/gitXugx/hotchpotch/blob/master/doc/soure-code/concurren/Condition%E6%BA%90%E7%A0%81.md)
- [ThreadPoolExcutor源码](https://github.com/gitXugx/hotchpotch/blob/master/doc/soure-code/concurren/ThreadPoolExcutor.md)
- [FutureTask源码](https://github.com/gitXugx/hotchpotch/blob/master/doc/soure-code/concurren/FutureTask%E6%BA%90%E7%A0%81.md)

## mybatis源码解读
- [mybatis是如何Mapper接口执行查询](https://github.com/gitXugx/source-debug/blob/master/doc/mybatis/mybatis%E6%BA%90%E7%A0%812.md)
- [mybatisInsert做了哪些](https://github.com/gitXugx/source-debug/blob/master/doc/mybatis/mybatis%E6%BA%90%E7%A0%813.md)
- [mybatis的核心源码有哪些](https://github.com/gitXugx/source-debug/blob/master/doc/mybatis/mybatis%E6%BA%90%E7%A0%814.md)


## 微服务
### springcloud
- [Eureka注册中心](https://github.com/gitXugx/hotchpotch/blob/master/doc/microservices/springcloud/Eureka%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83.md)
- [feign远程调用](https://github.com/gitXugx/hotchpotch/blob/master/doc/microservices/springcloud/feign%E8%BF%9C%E7%A8%8B%E8%B0%83%E7%94%A8.md)
- [ribbon负载均衡](https://github.com/gitXugx/hotchpotch/blob/master/doc/microservices/springcloud/ribbon%E8%B4%9F%E8%BD%BD%E5%9D%87%E8%A1%A1.md)
- [hystrix断路器](https://github.com/gitXugx/hotchpotch/blob/master/doc/microservices/springcloud/hystrix%E6%96%AD%E8%B7%AF%E5%99%A8.md)
### dubbo

## 中间件
### redis
### mq


## 设计
- [设计模式](https://github.com/gitXugx/design-mode/blob/master/README.md)

## 数据结构

- [数组](https://github.com/gitXugx/data-structure-arithmetic/blob/master/doc/datastructure/%E6%95%B0%E7%BB%84.md)
- [链表](https://github.com/gitXugx/data-structure-arithmetic/blob/master/doc/datastructure/%E9%93%BE%E8%A1%A8.md)
- [栈](https://github.com/gitXugx/data-structure-arithmetic/blob/master/doc/datastructure/%E6%A0%88.md)
- [队列](https://github.com/gitXugx/data-structure-arithmetic/blob/master/doc/datastructure/%E9%98%9F%E5%88%97.md)
