package design.mode.dm.behavior.op.v1;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 14:57
 */
public class Client {

    public  static void main(String[] args){
        ConcreteSubject vipcn = new Vipcn();
        Observer xgx = new XgxObserver("XGX");
        Observer qy = new QyObserver("QY");
        vipcn.addObserver(xgx);
        vipcn.addObserver(qy);
        int observerCount = vipcn.getObserverCount();

        System.out.println("观察者有："+ observerCount);
        vipcn.notice("今天是一个好日子,下雪了");
    }

}
