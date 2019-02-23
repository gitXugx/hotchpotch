package jvm.gc.example1;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2019 yowits Corporation. All rights reserved.
 * @create ：2019/1/7 10:57
 */
public  class RootSerchTest {
    private RootSerchTest instance;

    public  static void main(String[] args){
        RootSerchTest a = new RootSerchTest();
        RootSerchTest b = new RootSerchTest();

        a.instance = b;
        b.instance = a;
    }


    public void setObject(Object object){

    }

}
