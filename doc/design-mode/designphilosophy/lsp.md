# 里氏替换原则(Liskov Substitution Principle LSP)

> 里氏替换原则(Liskov Substitution Principle LSP)面向对象设计的基本原则之一。 里氏替换原则中说，任何基类可以出现的地方，子类一定可以出现。 LSP是继承复用的基石，只有当衍生类可以替换掉基类，软件单位的功能不受到影响时，基类才能真正被复用，而衍生类也能够在基类的基础上增加新的行为。


- [x] 定义
- [x] 使用目的
- [x] 使用场景


## 定义
里氏替换原则有两种定义:
+ 如果对每一个类型为S的对象o1，都有类型为T的对象o2，使得以T定义的所有程序P在所有的对象o1都代换为o2，程序P的行为没有发生变化，那么类型S是类型T的子类型。
+ 所有引用基类的地方必须透明的使用其子类的对象


**规范**

+ **对象o1都代换为o2，程序P的行为没有发生变化**，所有的父类都能替换成子类，意味着父类的方法在子类中不能有任何重载,如果覆盖了父类已实现的细节，可能会造成不必要的影响，当然如果我们不想让子类覆盖，可以声明成fnail方法
   
+ 子类不能完整地实现父类的方法，或者父类的一些方法在子类中已经发生畸变，则建议断开继承关系，采用依赖，聚集，组合等关系代替继承。
   
+ 子类不能代换成父类，意味着子类可以有自己的行为和属性
   
+ 覆盖或实现父类的方法时输入参数可以被放大
   
+ 覆写或实现父类的方法时输出结果可以被缩小。

**有人说多态和里氏替换原则冲突**

**继承和多态的要求**

+ 父类中必须有抽象方法或虚方法 
+ 子类必须重写父类中的抽象方法或虚方法 
+ 子类对象必须转换成父类类型去使用
  
所以重继承和多态的要求来说，并不和里氏替换原则冲突



## 使用目的
1. 定义子类和父类的关系，约束继承关系,使其构建更好的抽象设计
2. 提高代码复用性，减少代码冗余度。
3. 提高扩展性。
   

## 使用场景
可能我们在开发中不知不觉中就在使用着这些原则，当然有时也会没有遵循这些原则
场景: 
> 在企业营销中，我们可能有短信营销，也可能有邮件等营销


**营销父类:**

``````java
/**
 * @author ：ex-xugaoxiang001
 * @description ：里氏替换原则
 **/
public abstract class MarketingBasics {

    /**
     * 可以有多种加载人的实现 数据库，excel文件 ， 第三方等
     * @return
     */
    abstract List<Person>  loadPerson();

    /**
     * 所有营销都应该以
     * 1. 先找要营销的人
     * 2. 发送
     */
     public void execute(){
        List<Person> people = loadPerson();
        send(people);
    }

    //发送同样可以 短信  邮件 app通知 等不同类型 这边只是简单打印
    abstract void send(List<Person> personList);
}

``````
**短信营销类:**

``````java
public class SMSMarketing extends MarketingBasics{

    List<Person> loadPerson() {
        List<Person> personList = new ArrayList<>();
        //随便模拟几个人
        for(int i = 0 ; i < 5 ; i++){
            Person person = new Person();
            person.setMobile("176021270"+i);
            person.setNick("xgx"+i);
            personList.add(person);
        }
        return personList;
    }

    void send(List<Person> personList) {
        personList.forEach(x -> System.out.println("开始短信发送：手机号："+x.getMobile()+"  昵称："+x.getNick() ));
    }
}
``````


**邮件营销类:**

这里主要覆盖了父类的execute()方法

``````java
public class EmailMarketing extends MarketingBasics{

    @Override
    List<Person> loadPerson() {
        //随便加载一点人
        List<Person> personList = new ArrayList<>();
        //随便模拟几个人
        for(int i = 0 ; i < 5 ; i++){
            Person person = new Person();
            person.setEmail("60394969"+i+"@qq.com");
            person.setNick("xug"+i);
            personList.add(person);
        }
        return personList;
    }

    @Override
    void send(List<Person> personList) {
        personList.forEach(x -> System.out.println("开始邮件发送：邮箱："+x.getEmail()+"  昵称："+x.getNick() ));
    }

    @Override
    public void execute() {
        System.out.println("重写父类方法");
    }
}
``````


**Person类：**
``````java
public class Person {
    private String mobile;
    private String email;
    private String nick;
}

``````



**Test类：**
``````java
public class Test {
    public  static void main(String[] args){
        MarketingBasics smsMarketing = new SMSMarketing();

        smsMarketing.execute();

        //导致输出与预期不一致
        MarketingBasics emailMarketing = new EmailMarketing();

        emailMarketing.execute();
    }
}
``````










