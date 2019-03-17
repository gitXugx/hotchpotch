# feign远程调用
> 远程调用有很多方式，如dubbo的基于neety的rpc调用，springcloud的给出的http客户端调用的组件feign


## 特性

1. 支持注解开发
2. 支持http的编码和解码插件
3. 支持hytrix和ribbon
4. 支持多种http客户端


**支持注解开发:** feign支持springmvc的部分注解。

**支持http的编码和解码插件:** 提供插件的功能，能对其数据进行处理，像get请求不建议使用requestBody违反了restful设计，可以使用插件解决这个问题

**支持hytrix和ribbon:** 可以集成断路器和负载均衡组件

**支持多种http客户端:** feign 默认的是jdk的urlconnection长连接但是没有连接池。可以使用其他的http客户端进行替换，实现更可控的配置。



## 使用

1. 客户端在不注解@parameter的时候，feign如果body体中有值就会默认为post请求。
2. 在第一次调用feign的时候可能会出现超时的问题，超时有两种一种是ribbon另一种是hytrix，一般hytrix设置较小的情况下，服务提供方因为bean加载的情况下，会出现响应慢的问题导致请求超时。
3. 当需要token的时候，使用拦截器在请求头中添加一个key来传递token。









