package jvm.classlife.example4;

/**
 * @author ：apple
 * @description ：
 * @copyright ：	Copyright 2019 yowits Corporation. All rights reserved.
 * @create ：2019/1/1 下午9:45
 */
public class Test implements Runnable{
    public void run() {
        System.out.println(Thread.currentThread().getName());
        StaticTest staticTest = new StaticTest();
        System.out.println("初始化完成" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Test test = new Test();
        Thread thread = new Thread(test);
        Thread thread2 = new Thread(test);
        thread.start();
        thread2.start();
    }
}
