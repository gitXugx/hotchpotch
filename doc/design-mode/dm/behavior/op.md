# 观察者模式(Observer pattern)
> 说白了就是发布订阅模式

## 1.基础介绍

**类型:** 行为型

**使用目的**
+ 使一个对象变化对应的对象可以得到变化的通知

**要点**
+ 发布订阅模式

**缺点**
+ 如果一个被观察者对象有很多的直接和间接的观察者的话，将所有的观察者都通知到会花费很多时间。 
+ 如果在观察者和观察目标之间有循环依赖的话，观察目标会触发它们之间进行循环调用，可能导致系统崩溃。 
+ 观察者模式没有相应的机制让观察者知道所观察的目标对象是怎么发生变化的，而仅仅只是知道观察目标发生了变化。

**实现**
+ 观察者模式
+ jdk的观察者模式

## 2.代码

### v1. 观察者模式
> 微信公众号,你关注微信公众号,博主发送博文之后，会推送给你一个消息

**实现步骤**
1. 创建被观察者抽象(公众号抽象)
2. 创建公众号实现
3. 创建观察者抽象(观众抽象)
4. 创建每个观察者实现

创建公众号抽象:
```java
public interface ConcreteSubject {
    //添加观察者
    void addObserver(Observer observer);
    //删除观察者
    void deleteObserver(Observer observer);
    //通知观察者
    void notice(String message);
    //获取观察者个数
    int getObserverCount();
}
```
公众号实现:
```java
public class Vipcn implements ConcreteSubject{
    private  List<Observer> observers = new ArrayList<>();
    @Override
    public void addObserver(Observer observer) {
        if(observer != null){
            observers.add(observer);
        }
    }
    @Override
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }
    @Override
    public void notice(String message) {
        for(Observer observer : observers){
            observer.update(message);
        }
    }
    public int getObserverCount(){
        return observers.size();
    }
}

```

创建观察者抽象:
```java
public interface Observer {
    void update (String message);
}
```
创建观察者实现:

```java
public class XgxObserver implements Observer{
    private String name;

    public XgxObserver(String name) {
        this.name = name;
    }
    @Override
    public void update(String message) {
        System.out.println(name+ "收到更新:"+ message);
    }
}

public class QyObserver implements Observer{

    private String name;
    public QyObserver(String name) {
        this.name = name;
    }
    @Override
    public void update(String message) {
        System.out.println(name+ "收到更新:"+ message);
    }
}
```
客户端:
```java
public class Client {

    public  static void main(String[] args){
        ConcreteSubject vipcn = new Vipcn();
        Observer xgx = new XgxObserver("XGX");
        Observer qy = new QyObserver("QY");
        vipcn.addObserver(xgx);
        vipcn.addObserver(qy);
        int observerCount = vipcn.getObserverCount();

        System.out.println("观察者有："+ observerCount);
        vipcn.notice("今天是一个好日子,下雪了");
    }

}
```
执行结果:
```text
观察者有：2
XGX收到更新:今天是一个好日子,下雪了
QY收到更新:今天是一个好日子,下雪了
```


### v2. jdk观察者模式
> 还是上面的例子

**实现步骤**

1. 创建公众号实现`java.util.Observable`
2. 创建每个观察者实现`java.util.Observer`

创建公众号:
```java
public class Vipcn extends Observable {
    public void notic(String name){
        setChanged();
        notifyObservers(name);
    }
}
```
可以看到Observable里面好多都是线程安全的

创建每个观察者实现:
```java
public class XgxObserver implements Observer {
    private String name;

    public XgxObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(name+"收到更新： "+ arg);
    }
}

public class QYObserver implements Observer {

    private String name;

    public QYObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(name+"收到更新："+ arg);
    }
}
```
客户端:
```java
public class Client {
    public  static void main(String[] args){
        Observable vipcn = new Vipcn();
        Observer qy = new QYObserver("QY");
        Observer xgx = new XgxObserver("xgx");
        vipcn.addObserver(qy);
        vipcn.addObserver(xgx);
        ((Vipcn) vipcn).notic("发布博文");
        vipcn.notifyObservers();
    }
}
```
执行结果:
```text
xgx收到更新： 发布博文
QY收到更新：发布博文
```





















