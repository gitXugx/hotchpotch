package design.mode.dm.creational.sp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/21 10:33
 */
public enum  EnumSingle {

    INSTANCE;

    private EnumSingle(){
        System.out.println("init");
    }
}

