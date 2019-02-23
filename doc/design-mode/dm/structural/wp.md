# 装饰者模式(Wrapper pattern)
>  动态地给一个对象添加一些额外的职责。就增加功能来说， Decorator模式相比生成子类更为灵活。该模式以对客 户端透明的方式扩展对象的功能。

## 1.基础介绍

**类型:** 结构型

**使用目的**
+ 在不影响原有类的情况下,以动态透明的方式增加增强原有的类
+ 装饰者模式提供了除了继承的另一种扩展类的方法
+ 装饰的组件支持开闭原则

**要点**
+ 抽象装饰者需持有被装饰者的引用和实现被装饰者的抽象
+ 抽象出需要装饰的功能
+ 组件需要实现抽象装饰者类

**缺点**

**实现**

## 2.代码
**场景**
> 自己每天早上的穿衣服等等，每一件衣服都是包装。

**实现步骤**
1. 创建穿衣抽象
2. 创建自己
3. 创建包装抽象
4. 创建包装组件(衣服)

### v1.装饰者

穿衣抽象: 

```java
public interface Person {
    void dress();
}
```
自己实现:

```java
public class XgxMan implements Person{

    @Override
    public void dress() {
        System.out.println("今天怎么穿");
    }
}
```
抽象包装者:

```java
public abstract class Wrapper implements Person{
    //持有被装饰者的引用
    private Person person;

    public Wrapper(Person person) {
        this.person = person;
    }

    @Override
    public void dress() {
        person.dress();
    }
}
```
实现组件:
```java
public class Jacket extends Wrapper{
    public Jacket(Person person) {
        super(person);
    }
    @Override
    public void dress() {
        super.dress();
        System.out.println("穿个夹克..");
    }
}

public class Pants extends Wrapper{
    public Pants(Person person) {
        super(person);
    }
    @Override
    public void dress() {
        super.dress();
        System.out.println("穿个短裤");
    }
}

public class LeatherShoes extends Wrapper{
    public LeatherShoes(Person person) {
        super(person);
    }
    @Override
    public void dress() {
        super.dress();
        System.out.println("穿个皮鞋...");
    }
}
```
开始起床:

```java
public class GetUp {
    public  static void main(String[] args){
        Person xgxMan = new XgxMan();
        Wrapper jacket = new Jacket(xgxMan);
        Wrapper pants = new Pants(jacket);
        Wrapper leatherShoes= new LeatherShoes(pants);
        leatherShoes.dress();
    }
}
```
执行结果:
```text
今天怎么穿
穿个夹克..
穿个短裤
穿个皮鞋...
```

## 3.源码分析













































