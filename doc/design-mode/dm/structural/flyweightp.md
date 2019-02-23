# 享元模式(flyweight pattern)
> 利用共享的方式来支持大量细粒度的对象，这些对象一部分内部状态是相同的。

## 1.基础介绍

**类型:** 结构型

**使用目的**
+ 享元的目的是为了减少不必要的额外内存消耗

**要点**
+ 具有内部状态和外部状态之分
+ 内部状态是共享的状态，主要节约的就是这部分
+ 外部状态是可变的

**缺点**

**实现**

## 2.代码

### v1.享元模式
> 下面是苹果手机通用模板和手机配置的问题，手机模板可以共享，配置是独有的

**实现步骤**
1. 创建享元对象
2. 创建外部状态对象(组合享元对象)
3. 创建享元工厂(单例)

享元对象(内部状态): 

```java
public  class Apple {

    private String logo = "苹果";
    private String outLine = "长方形";
    }
```

创建外部状态对象:
```java

public class AppleX extends Apple {

    private Apple phone;
    public AppleX(Apple phone) {
        this.phone = phone;
    }
    private String cpu;
    private String color;
    private String resolutionRatio;

}
```
创建享元工厂:
```java
public class FlyweightFactory {
    private FlyweightFactory(){}
    public static Map<String , Apple> flyweight = new ConcurrentHashMap<>();
    public synchronized static Apple getPhone(String type){
        Apple phone = flyweight.get(type);
        if(phone == null ){
            if("phone".equals(type)){
                phone = new Apple();
                flyweight.put(type , phone);
            }
        }
        return phone;
    }
}
```
客户端:
```java
public class Client {
    public  static void main(String[] args){
        AppleX phone = new AppleX(FlyweightFactory.getPhone("phone"));

        phone.setColor("红色");
        phone.setCpu("845");
        phone.setResolutionRatio("1080x920");
        AppleX phone1 = new AppleX(FlyweightFactory.getPhone("phone"));

        phone1.setColor("蓝色");
        phone1.setCpu("645");
        phone1.setResolutionRatio("920x920");

        int size = FlyweightFactory.flyweight.size();
        System.out.println(size);

        System.out.println(phone);
        System.out.println(phone1);
    }
}
```

执行输出:
```text
1
ApplePhone{phone=Apple{logo='苹果', outLine='长方形'}, cpu='845', color='红色', resolutionRatio='1080x920'}
ApplePhone{phone=Apple{logo='苹果', outLine='长方形'}, cpu='645', color='蓝色', resolutionRatio='920x920'}
```
我们可以看到Apple对象只有一个。

**其他场景**
> 百度云盘文件上传,如果文件在服务器已经有了,就直接使用服务器上的,没有就上传

## 3.源码解析















