package design.mode.dm.structural.ap.v2;

/**
 * @author ：ex-xugaoxiang001
 * @description ：适配器模式
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/27 16:08
 */
public class Client {
    public  static void main(String[] args){
        HeadsetAdapter headsetAdapter = new HeadsetAdapter(new Circular(), new TypecHeadset());
        System.out.println("圆形耳机 插入");
        headsetAdapter.circularPlugInEarphones();
        System.out.println("type-c耳机 插入");
        headsetAdapter.typecPlugInEarphones();

    }
}
