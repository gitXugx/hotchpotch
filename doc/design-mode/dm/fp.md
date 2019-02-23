# 工厂模式
> 定义了一个创建产品对象的工厂接口，将实际创建工作推迟到子类工厂当中。

**类型:** 创建型

**使用目的**
+ 使其更有扩展性，降低客户端耦合

**要点**
+ 定义一个创建的接口给客户端
+ 使创建延迟到子类

## 1. 代码

+ 简单工厂
+ 工厂模式
+ 抽象工厂

每个模式都解决了不同维度的问题

### 1.1 简单工厂

假设有一家汽车厂家，客户过来订车

1. 创建汽车抽象
2. 实现汽车细节
3. 创建工厂
4. 创建client

首先创建产品抽象,我们的产品是汽车,这边没有写汽车的行为

``````java
public interface Car {
}
`````

下面实现细节 小型轿车

``````java
public class CompactCar implements  Car{
    private String name;
    private String price;
}
``````
不同的产品:
``````java
public class SUVCar  implements  Car{
    private String name;
    private String price;
}
``````
生产汽车的工厂类

``````java
public class   SimpleFactory {

    public static Car createCar(String type){
        switch (type){

            case "A":
                return new CompactCar("小型轿车" ,"10W - 15W");
            case "B":
                return new SUVCar("SUV轿车" ,"20W - 30W");
            default: throw new RuntimeException("没有你要的机型");
        }
    }

}
``````

具体的客户端:

``````java

public class Client {
    public  static void main(String[] args){
        Car a = SimpleFactory.createCar("A");
        System.out.println(a);
        Car b = SimpleFactory.createCar("B");
        System.out.println(b);
    }
}
``````

可以看到好像创建的时候传入字符串并不优雅，当增加新的产品时我们还需要去修改工厂，我们可以通过反射来修整一下:具体修改的 SimpleFactory类

``````java
public class   SimpleFactory {

    public static Car createCar(Class clazz){
        try {
            return (Car)Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace(); //这种错误打印很不好
            return null;
        }
    }
}

public class Client {
    public  static void main(String[] args){
        Car a = SimpleFactory.createCar(CompactCar.class); //传参就变成了class对象
        System.out.println(a);
        Car b = SimpleFactory.createCar(SUVCar.class);
        System.out.println(b);
    }
}

``````
这样我们就可以复合开闭原则.简单工厂名字，就和它的名字差不都，设计很简单
通过这样一修改，就符合前面我们设计原则中的开闭原则，迪米特原则;

**存在的问题:**
1. 但是其实我们使用反射来进行创建简单对象还是可以的，如果一个是复杂的对象，我们还是只能ifelse来进行创建，然后对不同的对象进行初始化，所以说反射只时符合开闭原则，但实用性差。

### 1.2 工厂模式
工厂模式就是简单工厂的解决不了的问题，开闭原则。

假设有一家汽车厂家，客户过来订车

1. 创建汽车抽象
2. 实现汽车细节
3. 创建抽象工厂
4. 实现细节
5. 创建client

首先创建汽车抽象,这里简单写一下:

``````java
public interface ICar {

    void showCar();
}
``````

现在有两种类型的汽车:
suv
sc

SUV的实现:

``````java
public class SUVCar implements ICar{
    @Override
    public void showCar() {
        System.out.println("生产 SUV");
    }
}
``````
sc实现:

``````java
public class CompactCar implements ICar{
    @Override
    public void showCar() {
        System.out.println("生产 轿跑");
    }
}
``````

创建抽象工厂: 
``````java
public interface CarFactory {

    ICar createCar();

}
``````
创建工厂实现类:

SUVFactory：

``````java
public class SUVFactory implements CarFactory {

    @Override
    public ICar createCar() {
        return new SUVCar();
    }
}
``````

scFactory:

``````java
public class CompactFactory implements CarFactory {
    @Override
    public ICar createCar() {
        return new CompactCar();
    }
}
``````
客户端实现:

``````java
public class Client {
    public  static void main(String[] args){
        CarFactory compactFactory = new CompactFactory();
        ICar car = compactFactory.createCar();
        car.showCar();

        CarFactory suvFactory = new SUVFactory();
        ICar suv = suvFactory.createCar();
        suv.showCar();
    }
}
``````

这是一个业务简单的工厂模式，当我们增加汽车类型的时候，只需要增加对应的工厂实现类和对应的产品即可。我们简单工厂没解决的开闭原则在这里也解决了。主要把产品的创建延迟到对应的工厂中，然后对其复杂的创建，什么校验，初始化等。如果使用反射来做就有些复杂了。

**存在的问题:**
1. 当有新的不同种类产品(自行车什么的)加入还要创建对应的工厂，这样类就会很多。
2. 需要解决产品族的问题。


### 1.3 抽象工厂
抽象工厂主要解决工厂模式所解决不了的问题

> 产品族的理解： adidas的鞋子，衣服，帽子是一个产品族，jordon的鞋子，衣服，帽子是一个产品族

1. 创建产品抽象
2. 实现产品抽象细节，以产品族的方式
3. 创建抽象产品族工厂
4. 实现产品族工厂细节

下面以一个adidas和jordon店铺来解析抽象工厂模式

衣服产品抽象:


``````java

public interface IClothes {

    void showClothes();

}

``````
鞋子产品抽象: 

``````java
public interface IShoe {

    void showShoe();

}
``````

具体产品族实现:

Adidas产品：

``````java
public class AdidasShoe implements IShoe{

    @Override
    public void showShoe() {
        System.out.println("阿迪达斯鞋子 : AdidasShoe");
    }
}

public class AdidasClothes implements IClothes{
    @Override
    public void showClothes() {
        System.out.println("阿迪达斯衣服: AdidasClothes");
    }
}

``````


Jordon：

``````java
public class JordonShoe implements IShoe {

    @Override
    public void showShoe() {
        System.out.println("乔丹鞋子: JordonShoe");
    }
}

public class JordonClothes implements IClothes{
    @Override
    public void showClothes() {
        System.out.println("乔丹衣服: AdidasClothes");
    }
}

``````

产品工厂抽象 ：

``````java
public interface Factory {

    IShoe createShoe();
    IClothes createClothes();

}
``````

产品族工厂的实现:

``````java
public class JordonFactory implements Factory{
    @Override
    public IShoe createShoe() {
        return new JordonShoe();
    }

    @Override
    public IClothes createClothes() {
        return new JordonClothes();
    }
}

public class AdidasFactory implements Factory{
    @Override
    public IShoe createShoe() {
        return new AdidasShoe();
    }

    @Override
    public IClothes createClothes() {
        return new AdidasClothes();
    }
}

``````


客户端的实现:

``````java
public class Client {
    public  static void main(String[] args){
        Factory adidasFactory =  new AdidasFactory();
        IShoe shoe = adidasFactory.createShoe();
        shoe.showShoe();
        IClothes adidasClothes = adidasFactory.createClothes();
        adidasClothes.showClothes();
        Factory jordonFactory =  new JordonFactory();
        IShoe jordonShoe = jordonFactory.createShoe();
        jordonShoe.showShoe();

        IClothes jordonClothes = jordonFactory.createClothes();
        jordonClothes.showClothes();
    }
}
``````

抽象工厂解决了产品族的问题，但是在没有新加产品的种类的情况下是复合开闭原则的，一旦新增一个种类的产品，就需要变动很多。所以在选择使用工厂时要复合自己的需求去选择。


## 2. 源码使用场景











































