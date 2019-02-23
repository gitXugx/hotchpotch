package design.mode.dm.behavior.op.v2;

import java.util.Observable;
import java.util.Observer;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 15:30
 */
public class XgxObserver implements Observer {

    private String name;

    public XgxObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(name+"收到更新： "+ arg);
    }
}
