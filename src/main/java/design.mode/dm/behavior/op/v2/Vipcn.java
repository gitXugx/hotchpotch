package design.mode.dm.behavior.op.v2;

import java.util.Observable;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 15:29
 */
public class Vipcn extends Observable {

    public void notic(String name){
        setChanged();
        notifyObservers(name);
    }

}
