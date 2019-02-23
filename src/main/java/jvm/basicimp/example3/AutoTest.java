package jvm.basicimp.example3;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2019 yowits Corporation. All rights reserved.
 * @create ：2019/1/7 16:08
 */
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
