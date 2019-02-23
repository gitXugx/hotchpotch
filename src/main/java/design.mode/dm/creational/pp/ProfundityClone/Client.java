package design.mode.dm.creational.pp.ProfundityClone;

import java.util.Date;

/**
 * @author ：ex-xugaoxiang001
 * @description ：深度克隆
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/26 10:41
 */
public class Client {

    public  static void main(String[] args) throws CloneNotSupportedException {
        Bean task = new Bean();
        task.setName("xgx");
        Date date = new Date(12121312L);
        task.setCreateTime(date);
        System.out.println(task);

        Bean task2 = (Bean)task.clone();
        date.setTime(22121231L);

        System.out.println("task2 :  "+task2);
        System.out.println("task1 :  "+task);
    }

}
