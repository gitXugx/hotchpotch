package design.mode.dm.creational.fp.sfp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/21 14:50
 */
public class   SimpleFactory {

    public static Car createCar(Class clazz){
        try {
            return (Car)Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace(); //这种错误打印很不好
            return null;
        }
    }
}
