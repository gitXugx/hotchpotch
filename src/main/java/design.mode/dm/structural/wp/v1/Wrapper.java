package design.mode.dm.structural.wp.v1;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/28 16:25
 */
public abstract class Wrapper implements Person{

    private Person person;

    public Wrapper(Person person) {
        this.person = person;
    }

    @Override
    public void dress() {
        person.dress();
    }
}
