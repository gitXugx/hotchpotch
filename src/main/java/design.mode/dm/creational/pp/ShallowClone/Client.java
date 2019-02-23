package design.mode.dm.creational.pp.ShallowClone;

import java.util.Date;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/26 10:41
 */
public class Client {

    public  static void main(String[] args) throws CloneNotSupportedException {
        Task task = new Task();
        task.setName("xgx");
        Date date = new Date(12121312L);
        task.setStartDate(date);
        System.out.println(task);

        Task task2 = (Task)task.clone();
        date.setTime(22121231L);

        System.out.println("task2 :  "+task2);
        System.out.println("task1 :  "+task);
    }

}
