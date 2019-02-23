# 原型模式(Prototype  Pattern)
> 使用原型实例指定待创建对象的类型，并且通过复制这个原型来创建新的对象。

**类型:** 创建型

**使用目的**
+ 当创建对象比较复杂时，耗费资源时，通过拷贝来避免消耗

**为什么clone创建复杂对象时消耗资源少:**
1. new 创建对象
2. clone来创建对象，但是该对象必须实现Cloneable，由虚拟据来检查
3. 反射来创建
4. 反序列化创建对象

现在占时还是没有搞清楚，不做太多解释**搁置**

**要点**
+ 实现Cloneable接口
+ 注意深度拷贝和浅度拷贝


## 1. 代码
>  使用场景很多，像spring bean时prototype类型。 ctrl+z 的撤销场景等

下面简单说下拷贝的问题:

浅拷贝：

``````java
public class Task implements Cloneable{
    private String name;
    private Date startDate;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}

public class Client {

    public  static void main(String[] args) throws CloneNotSupportedException {
        Task task = new Task();
        task.setName("xgx");
        Date date = new Date(12121312L);
        task.setStartDate(date);
        System.out.println(task);

        Task task2 = (Task)task.clone();
        date.setTime(22121231L);

        System.out.println("task2 :  "+task2);
        System.out.println("task1 :  "+task);
    }

}

``````

执行结果:
``````txt
Task{name='xgx', startDate=Thu Jan 01 11:22:01 CST 1970}
task2 :  Task{name='xgx', startDate=Thu Jan 01 14:08:41 CST 1970}
task1 :  Task{name='xgx', startDate=Thu Jan 01 14:08:41 CST 1970}
``````



``````java
public class Bean implements Cloneable{

    private String name;
    private Date createTime;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Bean clone = (Bean) super.clone();
        clone.setCreateTime( (Date)this.createTime.clone());
        return clone;
    }
    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
public class Client {

    public  static void main(String[] args) throws CloneNotSupportedException {
        Bean task = new Bean();
        task.setName("xgx");
        Date date = new Date(12121312L);
        task.setCreateTime(date);
        System.out.println(task);

        Bean task2 = (Bean)task.clone();
        date.setTime(22121231L);

        System.out.println("task2 :  "+task2);
        System.out.println("task1 :  "+task);
    }

}

``````

执行结果:
``````txt
Bean{name='xgx', createTime=Thu Jan 01 11:22:01 CST 1970}
task2 :  Bean{name='xgx', createTime=Thu Jan 01 11:22:01 CST 1970}
task1 :  Bean{name='xgx', createTime=Thu Jan 01 14:08:41 CST 1970}

``````












