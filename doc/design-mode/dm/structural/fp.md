# 外观者模式(faced pattern)
> 外部与一个子系统的通信必须通过一个统一的门面对象进行。门面模式提供一个高层次的接口，使得子系统更易于使用。每一个子系统只有一个门面类，而且此门面类只有一个实例，也就是说它是一个单例模式。但整个系统可以有多个门面类。

## 1.基础介绍

**类型:** 结构型

**使用目的**
+ 封装屏蔽原有的系统
+ 在原有的系统上面添加一些新的功能
+ 新加类，比修改原有的系统安全成本低
+ 给访问子系统的客户端创建一个统一接入点,简化系统

**要点**
+ 使用组合的模式，来提供子系统的功能
+ 它大部分属于架构上的设计,简化接口调用

**缺点**
+ 在不引入抽象外观类的情况下，如果新增了子系统，那就要修改外观类，其实在必要的情况下可以创建多个外观类

**实现**
+ 对类创建外观者模式
+ 对系统创建外观者模式

## 2.代码

### v1.对类创建外观者模式

> 在办公中，老板是比较繁忙的，员工如果需要去找老板都是通过秘书，老板是不对外人暴漏自己在干嘛，只对秘书暴露

**实现步骤**
1. 创建需要隐藏的类，只对外观者开放
2. 创建外观者
3. 使用继承和组合都可以

boss 类：
```java
public class Boss {

    protected void work(){
        System.out.println("老板商讨工作");
    }

    protected void conference(){
        System.out.println("老板开会");
    }

    protected void eat(){
        System.out.println("老板吃饭");
    }

    protected void sleep(){
        System.out.println("老板睡觉");
    }
}
```
秘书类外观者：
```java
public class Secretary {

    private  Boss boss = new Boss();

    public void bossWork(){
        boss.work();
    }

    public void conference(){
        boss.conference();
    }

}
```
客户端员工:
```java
public class Employe {

    public  static void main(String[] args){
        Secretary secretary = new Secretary();
        //当你直接去找boss boss是没有空的 你需要找秘书去申请,秘书决定要不要传达给老板
        Boss boss = new Boss();
        secretary.bossWork();
        secretary.conference();
    }
}
```
执行结果:
```text
老板商讨工作
老板开会
```
**使用结果**
+ 老板的其他行为是对外屏蔽的，保证老板不被员工干扰

### v2.对系统创建外观者模式
> 在多个子系统中，我们有通知系统，转账系统给客户端提供，我们可以使用外观者模式进行统一接入

**实现步骤**
1. 子系统(原本已有的系统)
2. 创建外观者
3. 使用继承和组合都可以

转账系统:
```java
public class TransferService {

    void transfer(){
        System.out.println("转账");
    }

}
```
通知系统：
```java
public class NotificationService {

    void notification(){
        System.out.println("通知转账成功");
    }
}
```
网关外观者:
```java
public class ApiFaced {

    private NotificationService notificationService;
    private TransferService transferService;

    public ApiFaced(NotificationService notificationService, TransferService transferService) {
        this.notificationService = notificationService;
        this.transferService = transferService;
    }

    public void transferApi(){
        //可以做任何事情
        transferService.transfer();
        notificationService.notification();
    }
}
```
客户端:
```java
public class Client {
    public  static void main(String[] args){
        //一般情况下都是注入
        ApiFaced apiFaced = new ApiFaced(new NotificationService() , new TransferService());
        apiFaced.transferApi();
    }
}
```
执行结果：
```text
转账
通知转账成功
```

**使用结果**
+ 屏蔽子系统，减少客户端与子系统的交流，便于维护子系统
+ 可以通过外观者对请求进行额外的统一处理


## 3.源码分析



















































