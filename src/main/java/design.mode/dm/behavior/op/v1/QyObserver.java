package design.mode.dm.behavior.op.v1;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 14:55
 */
public class QyObserver implements Observer{

    private String name;

    public QyObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name+ "收到更新:"+ message);
    }
}
