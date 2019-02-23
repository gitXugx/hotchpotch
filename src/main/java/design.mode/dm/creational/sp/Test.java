package design.mode.dm.creational.sp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/21 11:21
 */
public class Test {
    public  static void main(String[] args){
        EnumSingle instance = EnumSingle.INSTANCE;
        EnumSingle[] values = EnumSingle.values();
        EnumSingle enumSingle = EnumSingle.valueOf("INSTANCE");

        EnumSingle instance1 = EnumSingle.INSTANCE;
        EnumSingle instance2 = EnumSingle.INSTANCE;
        EnumSingle instance3 = EnumSingle.INSTANCE;
    }
}
