# 代理模式(proxy pattern)
> 为其他对象提供一种代理以便控制对这个对象的访问。

## 1.基础介绍

**类型:** 结构型

**使用目的**
+ 安全代理,可以详细控制访问某个类（对象）的方法，在调用这个方法前作的前置处理（统一的流程代码放到代理中处理）。调用这个方法后做后置处理。
+ 延迟加载(在使用到的时候才会初始化,例如项目启动)
+ 虚拟代理,提高性能的时候使用
+ 对类的功能增强
**要点**
+ 使用组合或者继承的方式实现静态代理
+ 使用jdk自带的动态代理
+ 使用CGLIB来实现动态代理
+ javaassist字节码操作库实现
+ ASM（底层使用指令，可维护性较差）
+ 也是一种委托模式

**缺点**
+ 

**实现**
+ 静态代理
+ jdk动态代理


## 2.代码

### v1.静态代理

**场景**
> 明星有经纪人，每个人只能通过经纪人访问明星

**实现步骤**
1. 创建明星抽象
2. 创建明星细节实现
3. 创建经纪人代理

明星抽象:
```java
public interface IStar {
    void openingAConcert();
}
```
明星实现:
```java
public class Star implements IStar{

    @Override
    public void openingAConcert() {
        System.out.println("开演唱会");
    }
}
```
经纪人:
```java
public class BrokerProxy implements IStar{

    private IStar star;
    //一般情况下这里是bean注入
    public BrokerProxy(IStar star) {
        this.star = star;
    }

    @Override
    public void openingAConcert() {
        before();
        star.openingAConcert();
        after();
    }
    private void before(){
        System.out.println("价钱谈好");
    }

    private void after(){
        System.out.println("结账");
    }
}
```
客户端：
```java
public class Client {
    public  static void main(String[] args){
        BrokerProxy brokerProxy = new BrokerProxy(new Star());

        brokerProxy.openingAConcert();
    }
}
```

执行结果:
```text
价钱谈好
开演唱会
结账
```

**使用结果**

优点:
+ 实现简单

缺点:
+ 如果实现修改则代理类和目标类都要修改
+ 每个目标类都要写对应的代理类，如果需要代理的目标类很多，那使用静态代理就是灾难

### v2.jdk动态代理

**场景**
> 每个公司都会有前台,我们可能需要前台帮我们代理办门卡，帮我们去收快递

**实现步骤**
1. 创建目标类和接口
2. 创建代理类实现jdk的InvocationHandler接口
3. `Proxy.newProxyInstance`获取代理对象

创建代收快递类和办门卡类以及接口，因为jdk代理必须要接口

```java
//接收类
public interface Receive  {
    public void receive();
}
//收快递
public class ReceiveDelivery implements Receive{
    @Override
    public void receive() {
        System.out.println("以帮忙接收快递");
    }
}
//申请卡
public interface ApplyForCard {
    void ApplyCard();
}
//申请门卡
public class ApplyDoorCard implements ApplyForCard {
    @Override
    public void ApplyCard() {
        System.out.println("申请门禁卡");
    }
}
```
前台类实现`InvocationHandler`接口:
```java
public class FrontDeskProxy implements InvocationHandler {

    private Object object;

    public void setObject(Object object) {
        this.object = object;
    }

    public FrontDeskProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(object , args);
        after();
        return null;
    }

    private void before(){
        System.out.println("接受到邮件");
    }

    private void after(){
        System.out.println("处理完毕邮件通知");
    }

}
```
客户端:
```java
public class Client {
    public  static void main(String[] args){

        //前台收邮件
        FrontDeskProxy frontDeskProxy = new FrontDeskProxy(new ReceiveDelivery());
        //通过它来获取代理对象
        Receive receive = (Receive)Proxy.newProxyInstance(frontDeskProxy.getClass().getClassLoader(), new Class[]{Receive.class}, frontDeskProxy);
        receive.receive();

        //办卡
        frontDeskProxy.setObject(new ApplyDoorCard());
        ApplyForCard applyForCard = (ApplyForCard)Proxy.newProxyInstance(frontDeskProxy.getClass().getClassLoader(), new Class[]{ApplyForCard.class}, frontDeskProxy);
        applyForCard.ApplyCard();
    }
}
```

执行结果
```text
接受到邮件
以帮忙接收快递
处理完毕邮件通知
接受到邮件
申请门禁卡
处理完毕邮件通知
```

**使用结果**
优点:
+ 不需要为每一个类去创建一个代理对象，代理对象由jdk自动生成

缺点:
+ 必须依赖接口

## 3.源码解析





































