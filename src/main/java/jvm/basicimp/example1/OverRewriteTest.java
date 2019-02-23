package jvm.basicimp.example1;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2019 yowits Corporation. All rights reserved.
 * @create ：2019/1/7 10:57
 */
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
