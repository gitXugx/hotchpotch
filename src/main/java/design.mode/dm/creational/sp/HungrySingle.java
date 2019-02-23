package design.mode.dm.creational.sp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：饿汉模式
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/20 16:26
 */
public class HungrySingle {

    // 1.私有化构造函数
    private HungrySingle (){}
    // 2. 利用类加载的时候时线程安全的，进行初始化
    private static final HungrySingle hungrySingle = new HungrySingle();

    public static HungrySingle getInstance(){
        return hungrySingle;
    }
}
