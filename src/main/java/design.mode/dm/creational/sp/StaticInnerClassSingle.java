package design.mode.dm.creational.sp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：静态内部类单例设计模式
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/20 18:33
 */
public class StaticInnerClassSingle {
    // 1.私有构造方法
    private StaticInnerClassSingle(){}
    // 2.创建内部类
    private static class SingleHandler{
        private static final StaticInnerClassSingle INSTANCE = new StaticInnerClassSingle();
    }
    // 3.获取实例
    public static  StaticInnerClassSingle getInstance(){
        return SingleHandler.INSTANCE;
    }
}
