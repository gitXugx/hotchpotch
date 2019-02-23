package jvm.gc.example1;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2019 yowits Corporation. All rights reserved.
 * @create ：2019/1/7 10:57
 */
public  class CycleReferenceTest {
    private CycleReferenceTest instance;

    public  static void main(String[] args){
        CycleReferenceTest a = new CycleReferenceTest();
        CycleReferenceTest b = new CycleReferenceTest();

        a.instance = b;
        b.instance = a;
    }
}
