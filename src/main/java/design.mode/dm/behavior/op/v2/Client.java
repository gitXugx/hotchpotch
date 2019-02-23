package design.mode.dm.behavior.op.v2;

import java.util.Observable;
import java.util.Observer;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 15:38
 */
public class Client {
    public  static void main(String[] args){
        Observable vipcn = new Vipcn();
        Observer qy = new QYObserver("QY");
        Observer xgx = new XgxObserver("xgx");
        vipcn.addObserver(qy);
        vipcn.addObserver(xgx);
        ((Vipcn) vipcn).notic("发布博文");
        vipcn.notifyObservers();
    }
}
