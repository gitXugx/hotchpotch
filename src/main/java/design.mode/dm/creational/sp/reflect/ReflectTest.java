package design.mode.dm.creational.sp.reflect;

import design.mode.dm.creational.sp.IdlerSingle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2019 yowits Corporation. All rights reserved.
 * @create ：2019/1/2 11:12
 */
public class ReflectTest {

    public  static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //获取单例对象
        IdlerSingle instance = IdlerSingle.getInstance();
        Class<IdlerSingle> idlerSingleClass = IdlerSingle.class;
        Constructor<IdlerSingle> declaredConstructor = idlerSingleClass.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        //通过反射创建对象
        IdlerSingle idlerSingle = declaredConstructor.newInstance();
        System.out.println(instance == idlerSingle);
    }
}
