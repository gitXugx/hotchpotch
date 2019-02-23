package design.mode.dm.structural.flyweightp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/28 17:57
 */
public class Client {
    public  static void main(String[] args){
        AppleX phone = new AppleX(FlyweightFactory.getPhone("phone"));

        phone.setColor("红色");
        phone.setCpu("845");
        phone.setResolutionRatio("1080x920");
        AppleX phone1 = new AppleX(FlyweightFactory.getPhone("phone"));

        phone1.setColor("蓝色");
        phone1.setCpu("645");
        phone1.setResolutionRatio("920x920");

        int size = FlyweightFactory.flyweight.size();
        System.out.println(size);

        System.out.println(phone);
        System.out.println(phone1);
    }

}
