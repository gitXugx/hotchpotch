package jvm.basicimp.example2;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2019 yowits Corporation. All rights reserved.
 * @create ：2019/1/7 16:08
 */
public class OverLoadTest {
    static  class Person{
    }
    static class Man extends Person{
    }

    static class Woman extends Person{
    }


    private static void print(Person person){
        System.out.println("print person ");
    }

    private static void print(Man man){
        System.out.println("print Man ");
    }
    private static void print(Woman woman){
        System.out.println("print woman ");
    }

    public  static void main(String[] args){
        Person person = new Person();
        Woman woman = new Woman();
        Man man = new Man();

        Person personWoamn = new Woman();
        Person personMan = new Man();

        print(person);
        print(woman);
        print(man);
        print(personWoamn);
        print(personMan);
    }

}
