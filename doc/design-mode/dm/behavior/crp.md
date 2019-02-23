# 责任链模式(Chain of Responsibility Pattern)
> 为请求创建了一个接收者对象的链。这种模式给予请求的类型，对请求的发送者和接收者进行解耦。这种类型的设计模式属于行为型模式。

## 1.基础介绍

**类型:** 行为型

**使用目的**
+ 使一个请求被多个对象处理,降低请求方与后台的处理(如果不用责任链,可能请求方要发送多个对象)

**要点**
+ 提供服务呈链式结构
+ 可灵活加入节点

**缺点**
+ 链式结构就避免不了调用链长的问题

**实现**
+ 责任链模式

## 2. 代码

### v1 责任链模式
**场景**
> 我们工作中都会有签报系统，我们请假干嘛的都要多个领导审批，例如请假3天以内上级审批，请假7天ceo审批，请假1月以上boss要看下
1. 创建需要处理的请求抽象接口
2. 创建处理节点

创建签报接口:
```java
public interface Sign {
    void apply(String name , String comment);
}
```
创建处理节点:
```java
public class Manager implements Sign {
    @Override
    public void apply(String name, String comment) {
        System.out.println("经理同意了"+ name + "的申请: "+ comment);
    }
}

public class CEO implements Sign {
    @Override
    public void apply(String name, String comment) {
        System.out.println("CEO同意了"+ name + "的申请: "+ comment);
    }
}

public class Boss implements Sign {
    @Override
    public void apply(String name, String comment) {
        System.out.println("经理同意了"+ name + "的申请: "+ comment);
    }
}
```

创建签报链:
```java

public class SignChain {
   private  List<Sign> signs = new ArrayList<>();

    public void add(Sign sign){
        if(sign != null){
            signs.add(sign);
        }
    }

    public void doApply(String name , String comment){
        for (Sign sign :signs){
            sign.apply(name , comment);
        }
    }
}
```
员工提签报:
```java
public class Client {
    public  static void main(String[] args){
        //创建审批链
        SignChain signChain = new SignChain();
        //创建审批人
        Sign manager = new Manager();
        Sign ceo = new CEO();
        Sign boss = new Boss();

        //添加审批人
        signChain.add(manager);
        signChain.add(ceo);
        signChain.add(boss);
        //开始审批
        signChain.doApply("xgx" , "过年提前一个月回家");
    }
}
```
执行结果:
```text
经理同意了xgx的申请: 过年提前一个月回家
CEO同意了xgx的申请: 过年提前一个月回家
boss同意了xgx的申请: 过年提前一个月回家
```

### 3. 源码分析










































