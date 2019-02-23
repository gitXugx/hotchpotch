package jvm.classlife.example2;

/**
 * @author ：apple
 * @description ：
 * @copyright ：	Copyright 2019 yowits Corporation. All rights reserved.
 * @create ：2019/1/1 下午9:45
 */
public class Test {
    static {
        size = 10;
//        System.out.println(size);
    }
    private static int size = (int)Math.random()*5 ;


    public static void main(String[] args) {
        System.out.println(size);
    }
}
