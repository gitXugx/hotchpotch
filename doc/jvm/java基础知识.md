# 基础知识点JVM实现
> 一些基础的知识点,jvm是怎么实现的

- [this的实现](#this的实现)
- [父类构造函数](#父类构造函数)
- [编译期不可知, 运行期可知](#编译期不可知-运行期可知)
   - [重载的实现](#重载的实现)
   - [重写的实现](#重写的实现)
   - [单分派和多分派](#单分派和多分派)
- [自动拆箱, 装箱的问题](#自动拆箱-装箱的问题)
- [(从字节码看)可变参数](#从字节码看可变参数)


## this的实现
> this代表实例对象。只有实例方法才有

java编译器会在编译实例方法的时候会把this作为第一个参数传给方法，如果是类方法则没有this参数

## 父类构造函数
编译子类构造函数默认在第一行代码调用父类的构造函数，如果父类没有无参构造函数则需要手动去调用，并且在子类构造函数的第一行，确保子类初始化时，父类先初始化。


## 编译期不可知, 运行期可知 
> 当然对应的还有编译期可知, 运行期不可变。这两种都时对方法的编译时的描述, 对于静态方法和私有方法, 他们在编译的时候就已经确认调用时是哪个方法, 对于重载和重写的方法他们属于运行时确定。也就是拥有多态的会有运行时确定

**编译期不可知, 运行期可知** 
- 具有多态性的方法, 重载

**编译期可知, 运行期不可变**
- 静态方法
- final方法
- 私有方法
- 构造方法


### 重载的实现
> 方法名相同, 参数不同(参数个数, 参数类型)。重载是方法特征签名, 特征签名包括代码层面和字节码层面, 代码层面只包括 (参数顺序, 参数类型, 方法名), 字节码层面包括 (参数顺序, 参数类型, 方法名, 返回值, 受检查异常表)

- 静态重载实现
在编译期间, 根据静态类型来决定而不是实际类型来确定调用的方法, 重载的静态方法也能在编译时就确定调用的方法(或者选择最佳的)。这个其实叫 `静态分派`   

```text
public class basic.jvm.basicimp.example2.OverLoadTest {
  public static void main(java.lang.String[]);
    Code:
      .....
      33: new           #11                 // class basic/jvm/jvm/basicimp/example2/OverLoadTest$Man
      36: dup
      37: invokespecial #12                 // Method basic/jvm/jvm/basicimp/example2/OverLoadTest$Man."<init>":()V
      40: astore        5
      42: aload_1
      43: invokestatic  #13                 // Method print:(Lbasic/jvm/jvm/basicimp/example2/OverLoadTest$Person;)V
      46: aload_2
      47: invokestatic  #14                 // Method print:(Lbasic/jvm/jvm/basicimp/example2/OverLoadTest$Woman;)V
      50: aload_3
      51: invokestatic  #15                 // Method print:(Lbasic/jvm/jvm/basicimp/example2/OverLoadTest$Man;)V
      54: aload         4
      56: invokestatic  #13                 // Method print:(Lbasic/jvm/jvm/basicimp/example2/OverLoadTest$Person;)V
      59: aload         5
      61: invokestatic  #13                 // Method print:(Lbasic/jvm/jvm/basicimp/example2/OverLoadTest$Person;)V
      64: return
}

```
1. 0-40 都是创建对象初始化, 存放到局部变量表
2. 42-61 都是重复调用把局部变量加载到操作数栈, 使用 `invokestatic` 指令调用方法指向常量池 `#13`
3. 从下面编译的字节码我们就可以看出 `#13 #14 #15` 已经确定了方法是调用哪个了
```text
Constant pool:
  #13 = Methodref          #16.#58        // basic/jvm/jvm/basicimp/example2/OverLoadTest.print:(Lbasic/jvm/jvm/basicimp/example2/OverLoadTest$Person;)V
  #14 = Methodref          #16.#59        // basic/jvm/jvm/basicimp/example2/OverLoadTest.print:(Lbasic/jvm/jvm/basicimp/example2/OverLoadTest$Woman;)V
  #15 = Methodref          #16.#60        // basic/jvm/jvm/basicimp/example2/OverLoadTest.print:(Lbasic/jvm/jvm/basicimp/example2/OverLoadTest$Man;)V
```
我们最后的执行结果, 与我们字节码编译的预期一致
```text
print person  #13
print woman   #14
print Man     #15
print person  #13
print person  #13
```

### 重写的实现
> 子类覆盖父类的方法, 来实现多态

```java
public  class OverRewriteTest {
    static abstract class Person{
        abstract void say();
    }
    static class Man extends Person{
        void say() {
            System.out.println("say Man");
        }
    }

    static class Woman extends Person{
        void say() {
            System.out.println("say Woman");
        }
    }

    public  static void main(String[] args){
        Person woman = new Woman();
        Person man = new Man();
        woman.say();
        man.say();
    }
}
```
为什么要写成静态的内部类, 其实这个和静态方法一样, 静态方法是属于类, 而实例方法必须创建实例才能调用实例方法, 所以说为了不创建外部类实例而使用静态内部类。

执行结果:
```text
say Woman
say Man
``` 
下面是上面代码编译的字节码:
```text
public class basic.jvm.basicimp.example1.OverRewriteTest {
  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class basic/jvm/jvm/basicimp/example1/OverRewriteTest$Woman
       3: dup
       4: invokespecial #3                  // Method basic/jvm/jvm/basicimp/example1/OverRewriteTest$Woman."<init>":()V
       7: astore_1
       8: new           #4                  // class basic/jvm/jvm/basicimp/example1/OverRewriteTest$Man
      11: dup
      12: invokespecial #5                  // Method basic/jvm/jvm/basicimp/example1/OverRewriteTest$Man."<init>":()V
      15: astore_2
      16: aload_1
      17: invokevirtual #6                  // Method basic/jvm/jvm/basicimp/example1/OverRewriteTest$Person.say:()V
      20: aload_2
      21: invokevirtual #6                  // Method basic/jvm/jvm/basicimp/example1/OverRewriteTest$Person.say:()V
      24: return
}
```
1. 1 - 15是创建对象, 并把变量方法局部变量表的1, 2两个槽
2. 16 - 17是加载第一变量到操作数栈, 通过 `invokevirtual` 命令来调用方法, 但是指向的是常量池 `#6`
3. 20 - 21是加载第二变量到操作数栈, 通过 `invokevirtual` 命令来调用方法, 和上面一样也是 `#6`

```text
Constant pool:
   #6 = Methodref          #12.#33        // basic/jvm/jvm/basicimp/example1/OverRewriteTest$Person.say:()V
```

现在看来最主要的就是 `invokevirtual` 命令了, 它主要做了什么:
1. 根据 `Person` 静态类型找到实际类型 `Woman` 或者 `Man` 
2. 根据**方法描述**在方法表中查找符合的方法, 如果找到则返回直接引用, 否则抛异常(一般情况下会父类的方法也会在子类的方法表中)
***(根据方法表的设计有所不同, 这种设计性能比较差, 一般是上面的冗余做法)如果有父类接着根据父类继承关系从下往上查找方法表, 如果有则返回直接引用***
[分派的优化]()

这种在运行期根据实际类型来调用方法称为 `动态分派`

### 单分派和多分派
- 宗量: 方法的接收者与方法的参数统称为方法的宗量。
- 静态分派: 依赖静态类型来定位方法执行版本的分派动作称为静态分派
- 动态分派: 依赖动态类型来定位方法执行版本的分派动作称为动态分派
- 多分派: 编译器需要根据方法的变量的静态类型和参数才能确定方法的描述符。
- 单分派: 方法的名称和描述已确定, 根据实际类型去决定执行的方法版本。


## 自动拆箱, 装箱的问题

```java
public class AutoTest {
    public  static void main(String[] args){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        System.out.println(c == d);//true
        System.out.println(e == f);//false
        System.out.println(c == (a+b));//true
        System.out.println(c .equals(a+b));//true
        System.out.println(g == (a+b));//true
        System.out.println(g .equals (a+b));//false
    }
}
```

这是java虚拟机第二版上面的, 上面注释后面是运行结果, 注意 `g == (a+b)` 他在编译成下面的字节码:
```text
       132: aload         7
       134: invokevirtual #10                 // Method java/lang/Long.longValue:()J
       137: aload_1
       138: invokevirtual #8                  // Method java/lang/Integer.intValue:()I
       141: aload_2
       142: invokevirtual #8                  // Method java/lang/Integer.intValue:()I
       145: iadd                              //1+2
       146: i2l                               //把int类型转换成long类型
       147: lcmp                              //做一个long类型的比较
       148: ifne          155
       151: iconst_1
       152: goto          156
       155: iconst_0
```

## (从字节码看)可变参数

```java
public class ChangeableParameterTest {
    public  static void main(String[] args){
        ChangeableParameter(1111,2222,333);
    }

    public static void ChangeableParameter(int... args ){

    }
}
```
主要看字节码 `ChangeableParameter`这个方法:

```text
public static void ChangeableParameter(int...);
    descriptor: ([I)V
    flags: ACC_PUBLIC, ACC_STATIC, ACC_VARARGS
    Code:
      stack=0, locals=1, args_size=1
         0: return
      LineNumberTable:
        line 17: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       1     0  args   [I         //看这个参数, 是一个数组
```

可以看出可变参数其实就是一个数组类型的参数。













































































