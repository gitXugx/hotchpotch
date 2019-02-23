package design.mode.dm.creational.bp.v1.test;

import design.mode.dm.creational.bp.v1.Director;
import design.mode.dm.creational.bp.v1.Markting;
import design.mode.dm.creational.bp.v1.SMSBuilder;

/**
 * @author ：ex-xugaoxiang001
 * @description ：建造者模式
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/25 17:56
 */
public class Client {

    public  static void main(String[] args){
        Director director = new Director(new SMSBuilder());
        Markting construct = director.construct();
        System.out.println(construct.toString());
    }

}
