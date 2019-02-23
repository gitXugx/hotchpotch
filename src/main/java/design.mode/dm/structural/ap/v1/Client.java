package design.mode.dm.structural.ap.v1;

/**
 * @author ：ex-xugaoxiang001
 * @description ：收集是type把他适配成圆孔耳机
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/27 15:57
 */
public class Client {

    public  static void main(String[] args){
        IHeadsetPlug circularPlug = new TypeHeadsetAdapter();
        System.out.println("使用圆形耳机");
        circularPlug.circularPlugInEarphones();
    }

}
