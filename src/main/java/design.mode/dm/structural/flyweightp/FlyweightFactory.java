package design.mode.dm.structural.flyweightp;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/28 17:48
 */
public class FlyweightFactory {
    private FlyweightFactory(){}
    public static Map<String , Apple> flyweight = new ConcurrentHashMap<>();


    public synchronized static Apple getPhone(String type){
        Apple phone = flyweight.get(type);
        if(phone == null ){
            if("phone".equals(type)){
                phone = new Apple();
                flyweight.put(type , phone);
            }
        }
        return phone;
    }
}
