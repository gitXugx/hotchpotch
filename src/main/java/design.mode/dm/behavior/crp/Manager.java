package design.mode.dm.behavior.crp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 13:00
 */
public class Manager implements Sign {

    @Override
    public void apply(String name, String comment) {
        System.out.println("经理同意了"+ name + "的申请: "+ comment);
    }
}
