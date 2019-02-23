package design.mode.dm.creational.sp;

import java.io.Serializable;

/**
 * @author ：ex-xugaoxiang001
 * @description ：懒汉单例设计模式
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/20 14:18
 */
public class IdlerSingle implements Serializable {

    private static final long serialVersionUID = 2721181468902911235L;

    //1. 私有化构造函数
    private IdlerSingle(){}
    //2. 声明变量
    private static volatile IdlerSingle idlerSingle = null;
    //3. 创建获取实例方法
    public static  IdlerSingle getInstance(){//这个方法并不是线程安全，可能创建多个对象
        if(idlerSingle == null){ // 1.
            synchronized(IdlerSingle.class){ // 2.
                if (idlerSingle == null){  // 3.
                    idlerSingle = new IdlerSingle();
                }
            }
        }
        return idlerSingle;
    }
    //解决反序列化多个对象问题
    private Object readResolve(){
        if(idlerSingle == null){
            return getInstance();
        }else {
            return idlerSingle;
        }
    }
}
