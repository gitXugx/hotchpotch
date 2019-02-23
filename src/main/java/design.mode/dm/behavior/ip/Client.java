package design.mode.dm.behavior.ip;

/**
 * @author ：apple
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/31 下午10:16
 */
public class Client {

    public static void main(String[] args) {
        MyList myList =  new MyContainer(10);
        myList.add(0);
        myList.add(3);
        myList.add(2);
        myList.add(9);
        myList.add(8);
        myList.add(3);
        myList.add(1);
        Iterator iterator = myList.iterator();
        while (iterator.hashNext()){
            Object next = iterator.next();
            System.out.println(next);
        }

    }

}
