package design.mode.dm.creational.sp.SoundCode;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2019 yowits Corporation. All rights reserved.
 * @create ：2019/1/2 15:54
 */
public class Test {

    public  static void main(String[] args){
        Runtime runtime = Runtime.getRuntime();
        long l = runtime.freeMemory();
        System.out.println("freeMemory： "+  l);
        //gc
        runtime.gc();
        //
        long l1 = runtime.totalMemory();
        System.out.println("totalMemory: "+ l1);
    }
}
