package design.mode.dm.behavior.op.v1;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 14:27
 */
public interface ConcreteSubject {

    void addObserver(Observer observer);
    void deleteObserver(Observer observer);
    void notice(String message);
    int getObserverCount();
}
