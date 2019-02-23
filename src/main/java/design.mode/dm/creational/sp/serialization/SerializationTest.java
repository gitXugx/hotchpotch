package design.mode.dm.creational.sp.serialization;

import design.mode.dm.creational.sp.IdlerSingle;

import java.io.*;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2019 yowits Corporation. All rights reserved.
 * @create ：2019/1/2 10:00
 */
public class SerializationTest {

    public  static void main(String[] args) throws IOException, ClassNotFoundException {
        //懒汉模式
        IdlerSingle instance = IdlerSingle.getInstance();
        ObjectOutputStream serialization = new ObjectOutputStream(new FileOutputStream("serialization"));
        serialization.writeObject(instance);
        serialization.close();
        //反序列化
        ObjectInputStream serialization1 = new ObjectInputStream(new FileInputStream(new File("serialization")));
        IdlerSingle o = (IdlerSingle)serialization1.readObject();
        System.out.println(instance == o);
    }
}
