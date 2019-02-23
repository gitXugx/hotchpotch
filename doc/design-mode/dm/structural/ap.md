# 适配器模式(adapter pattern)
> 将一个类的接口转换成客户希望的另一个接口。适配器模式让那些接口不兼容的类可以一起工作

## 1.基础介绍

**类型:** 结构型

**使用目的**
+ 想创建一个重复使用的类,用于没有关联的类
+ 系统使用一些类,但是这些接口不符合使用

**要点**
+ 有点委托的感觉
+ 类适配一般采用继承
+ 对象适配一般使用组合

**缺点**
+ 如果系统中频繁使用适配器模式会导致很复杂(使用A实际上调用的是B)

**相似设计模式**

1. 外观者设计模式
2. 装饰者设计模式

**实现**
+ 类适配
+ 对象适配

## 2.代码


### v1.类适配
**场景**
> 我们现在手机充电线基本上没有圆形耳机孔,都是<font color=#FF8C00>type-c</font>的充电头,当我们只有圆形耳机的时候就需要去做一个适配

**实现步骤**
1. 创建原接口(在实际开发中这个是已有的接口)
2. 创建客户需要的接口
3. 创建适配器并实现客户需要的接口,继承原接口

type-c 接口:
```java
public class TypecHeadset {

    void plugInEarphones(){
        System.out.println("插入type-c插头耳机");
    }

}
```

客户端所需的圆孔接口:

```java
public interface IHeadsetPlug {
    //圆形接口
    void circularPlugInEarphones();

}
```


把`type-c`适配成圆形:

```java
public class TypeHeadsetAdapter extends TypecHeadset implements IHeadsetPlug{
    @Override
    public void circularPlugInEarphones() {
        super.plugInEarphones();
    }
}
```

客户端的使用情况:

```java
public class Client {
    public  static void main(String[] args){
        IHeadsetPlug circularPlug = new TypeHeadsetAdapter();
        circularPlug.circularPlugInEarphones();
    }
}
```

适配结果:

```text
使用圆形耳机
插入type-c插头耳机
```

**使用结果**
+ 增加了类的透明度,对于客户端来说是无感知的
+ 增加了类的复用,其`type-c`依然可以使用原来的接口

### v2.对象适配
> 我们现在要做一个双向适配的适配器,这个适配器可以作用圆孔和<font color=#FF8C00 >type-c</font>

**实现步骤**
1. 创建原接口(在实际开发中这个是已有的接口)
2. 创建客户需要的接口
3. 创建适配器并实现客户需要的接口,组合原接口

type-c 实现接口:
```java
public class TypecHeadset implements ITypec{
    public void typecPlugInEarphones(){
        System.out.println("插入type-c耳机插孔");
    }
}
```

圆形耳机实现:
```java
public class Circular implements ICircular{
    public void circularPlugInEarphones(){
        System.out.println("插入圆形耳机插孔");
    }
}
```

type-c 接口:
```java
public interface ITypec {
    void typecPlugInEarphones();
}
```

圆形耳机接口：
```java
public interface ICircular {
    void circularPlugInEarphones();
}
```
耳机适配器:
```java
public class HeadsetAdapter implements ICircular , ITypec{
    private ICircular iCircular;
    private ITypec iTypec;

    public HeadsetAdapter(ICircular iCircular, ITypec iTypec) {
        this.iCircular = iCircular;
        this.iTypec = iTypec;
    }
    @Override
    public void circularPlugInEarphones() {
        iTypec.typecPlugInEarphones();
    }
    @Override
    public void typecPlugInEarphones() {
        iCircular.circularPlugInEarphones();
    }
}
```
客户使用:

```java
public class Client {
    public  static void main(String[] args){
        HeadsetAdapter headsetAdapter = new HeadsetAdapter(new Circular(), new TypecHeadset());

        headsetAdapter.circularPlugInEarphones();

        headsetAdapter.typecPlugInEarphones();
    }
}
```
执行结果:
```text
圆形耳机 插入
插入type-c耳机插孔
type-c耳机 插入
插入圆形耳机插孔
```

### 3.源码分析






















































