package jvm.classlife.example4;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2019 yowits Corporation. All rights reserved.
 * @create ：2019/1/2 15:00
 */
public class StaticTest {
     int A = 2;
     static{
          System.out.println("执行static" + Thread.currentThread().getName());
          try {
               Thread.sleep(10000);
          } catch (InterruptedException e) {
               e.printStackTrace();
          }
     }
}
