# 模板方法模式(Template Pattern)
> 定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。
## 1.基础介绍

**类型:** 行为型

**使用目的**
+ 父类定义算法结构，子类不能修改(一般申明为final)
+ 父类定义一些通用的方法
+ 提高代码复用性

**要点**
+ 定义可重用的非抽象方法(一般申明为final)
+ 特色的方法在子类中实现

**缺点**
+ 每个不同的实现都要一个子类去实现

**实现**
+ 模板方法模式

## 2. 代码

### v1.模板方法模式
> 游戏设计,每个游戏(不论是网络游戏还是单机)的启动和结束都会有一个算法，这个算法可以说使不变的

1. 创建抽象类,固定算法由自己实现
2. 创建子类，特色方法由自己实现


游戏抽象类实现:
```java
public abstract class Game {
    //初始化游戏
    abstract void initGame();
    //开始游戏
    abstract void startGame();
    //结束游戏
    abstract void endGame();
    //模板方法
    public final void play(){
        initGame();
        startGame();
        endGame();
    }
}
```
创建子类:
```java
public class SingleGame extends Game{

    @Override
    void initGame() {
        System.out.println("单机游戏初始化成功");
    }

    @Override
    void startGame() {
        System.out.println("开始玩游戏");
    }

    @Override
    void endGame() {
        System.out.println("游戏结束");
    }
}

public class NetworkGame extends Game{

    @Override
    void initGame() {
        System.out.println("初始化网络游戏成功");
    }

    @Override
    void startGame() {
        System.out.println("开始玩网路游戏");
    }

    @Override
    void endGame() {
        System.out.println("网络游戏结束");
    }
}

```

创建客户端:
```java
public class Client {
    public  static void main(String[] args){
        Game singleGame = new SingleGame();
        singleGame.play();

        Game networkGame = new NetworkGame();
        networkGame.play();
    }
}
```

执行结果:
```text
单机游戏初始化成功
开始玩游戏
游戏结束
初始化网络游戏成功
开始玩网路游戏
网络游戏结束
```

## 3. 源码分析































