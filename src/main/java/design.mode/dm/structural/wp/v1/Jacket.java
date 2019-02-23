package design.mode.dm.structural.wp.v1;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/28 16:30
 */
public class Jacket extends Wrapper{

    public Jacket(Person person) {
        super(person);
    }

    @Override
    public void dress() {
        super.dress();
        System.out.println("穿个夹克..");
    }
}
