# 建造者模式(Builder Pattern)
> 它可以将复杂对象的建造过程抽象出来（抽象类别），使这个抽象过程的不同实现方法可以构造出不同表现（属性）的对象。

**类型:** 创建型

**使用目的**
+ 构造函数参数过多，或者构造函数很多时
+ 用来构建复杂对象生成不同状态的实例

**要点**
+ 创建实例
+ 管理创建的实例

## 1. 代码

+ v.1 建造者模式
+ v.2 建造者模式

### v.1 建造者模式

以营销场景为例，当客户端创建营销时根据具体的建造者来创建不同的营销方式。

1. 创建营销对象
2. 创建build抽象对象
3. 创建build实现
4. 创建build组装者

大致分为这4个步骤:

创建p一个简单的营销对象。

``````java
public class Markting {
    private String loadPerson;
    private String filterPerson;
    private String send;
}
``````

创建抽象:

``````java

public abstract class Builder {
    protected Markting markting =  new Markting();

    abstract void buildLoadPerson();

    abstract void buildSend();

    abstract void buildFilter();

    protected Markting createMarkting(){
        return markting;
    }
}

``````
具体的实现在对应的builder中，分为短信和邮件:

``````java
public class EmailBuilder extends Builder{

    @Override
    void buildLoadPerson() {
        super.markting.setLoadPerson("加载需要邮件营销人");
    }

    @Override
    void buildSend() {
        super.markting.setSend("开始发送邮件");
    }

    @Override
    void buildFilter() {
        super.markting.setFilterPerson("过滤邮件名单");
    }

}

public class SMSBuilder extends Builder{

    @Override
    void buildLoadPerson() {
        super.markting.setLoadPerson("加载需要短信营销人");
    }

    @Override
    void buildSend() {
        super.markting.setSend("开始发生短信营销");
    }

    @Override
    void buildFilter() {
        super.markting.setFilterPerson("过滤短信黑名单");
    }

}
``````

提供一个builder组装者来创建对应的对象:

``````java
public class Director {

    private Builder builder;

    public Director(Builder builder){
        this.builder = builder;
    }
    public Markting construct(){
        builder.buildLoadPerson();
        builder.buildSend();
        builder.buildFilter();
        return builder.createMarkting();
    }
}

``````

client端的实现:

``````java
public class Client {

    public  static void main(String[] args){
        Director director = new Director(new SMSBuilder());
        Markting construct = director.construct();
        System.out.println(construct.toString());
    }

}
``````

支持扩展性，如果还有其他的营销的话只需添加对应的实现即可，那有人就会说那还不如用工厂模式去创建对应的对象。

**工厂生产的是一类产品，而构建者构建的是一个真实的产品，只是可能由于对象组装不同，会构建出不同表现的对象**

由于实现构建和装配是解耦的，不同的构建器，相同的装配可以构建出不同的对象，相同的构建器，不同的装配器也可以构建成不同的对象。也就是实现了构建算法和装配算法分离，来进一步提高复用。

举个例子:

妈妈在生小宝宝时，作为外界的人，我们只知道是生男孩还是女孩，妈妈就是工厂 ，男孩女孩就是产品。但是在妈妈体内的时候需要发育在不同的阶段受到不同的影响也会影响到宝宝，有的宝宝，爱哭，有的宝宝不喜欢哭，有的宝宝胖，有的宝宝廋等等，男孩有男孩的装配和组装算法，女孩有女孩的装配算法，所以他们出生虽然说他们都是男孩，但是他们对外表现是不一致的。

我上面代码例子可能不是太合适。


### v.2 建造者模式
> 

1. 目标构建的实体类
2. 创建静态内部类

``````java
public class Menu {

    private Drink drink;

    private Dishe dishe;

    private Food food;

    public static class ProductBuild{

        private Drink drink;

        private Dishe dishe;

        private Food food;

        public ProductBuild drink(Drink drink) {
            this.drink = drink;
            return this;
        }

        public ProductBuild dishe(Dishe dishe) {
            this.dishe = dishe;
            return this;

        }

        public ProductBuild food(Food food) {
            this.food = food;
            return this;
        }

        private ProductBuild(){}

        public static ProductBuild builder(){
            return new ProductBuild();
        }

        public Menu build(){
            Menu menu = new Menu();
            menu.dishe =  this.dishe;
            menu.drink = this.drink;
            menu.food = this.food;
            return menu;
        }
    }

    public void showMenu(){
        if(food != null){
            food.showFood();
        }
        if(drink != null){
            drink.showSmell();
        }
        if(dishe != null){
            dishe.showDishes();
        }
    }

}

``````

+ 构造函数过多的情况下
+ 相同的方法，不同的执行顺序，产生不同的事件结果时。
+ 多个部件或零件,都可以装配到一个对象中，但是产生的运行结果又不相同时。
+ 产品类非常复杂，或者产品类中的调用顺序不同产生了不同的效能。
+ 创建一些复杂的对象时，这些对象的内部组成构件间的建造顺序是稳定的，但是对象的内部组成构件面临着复杂的变化。



## 2. 源码使用场景




























