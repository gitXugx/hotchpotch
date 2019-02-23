# 命令行模式(Command pattern)
> 将一个请求封装成一个对象，从而让你使用不同的请求把客户端参数化，对请 求排队或者记录请求日志，可以提供命令的撤销和恢复功能。
## 1.基础介绍

**类型:** 行为型

**使用目的**
+ 把请求包装成一个命令对象,通过调用者来执行对应的命令,达到请求者和执行者的解耦

**要点**


**缺点**
+ 如果命令过多会有很多类

**实现**
+ 命令模式

## 2. 代码

### v1. 命令模式

**实现步骤**
1. 创建命令抽象类
2. 创建对应的命令,持有接受者
3. 创建接收者
4. 创建调用者,执行命令

创建抽象命令:
```java
public interface Command {
    void execute();
}
```

创建命令实现者:
```java
public class DoPorridgeCommand implements Command{

    private DoProrridgeHandler handler;
    //一般情况下注入
    public DoPorridgeCommand(DoProrridgeHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.doSomething();
    }
}

public class DoRiceCommand implements Command{

    private DoRiceHandler handler;

    public DoRiceCommand(DoRiceHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.doSomething();
    }
}
```

创建创建命令接收者:
```java
public class DoProrridgeHandler {

    public void doSomething() {
        System.out.println("做粥");
    }
}

public class DoRiceHandler {
    public void doSomething() {
        System.out.println("做米饭");
    }
}
```
创建命令调用者:
```java
public class Invoker {
    private Command command1;
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void invoker(){
        command.execute();
    }
}
```
客户端:
```java
public class Client {
    public  static void main(String[] args){
        DoProrridgeHandler doProrridgeHandler = new DoProrridgeHandler();
        Command doPorridgeCommand = new DoPorridgeCommand(doProrridgeHandler);
        Invoker invoker = new Invoker(doPorridgeCommand);
        invoker.invoker();

        DoRiceHandler doRiceHandler = new DoRiceHandler();
        Command doRiceCommand = new DoRiceCommand(doRiceHandler);
        Invoker invoker2 = new Invoker(doRiceCommand);
        invoker2.invoker();
    }
}
```

执行结果:
```text
做粥
做米饭
```

















