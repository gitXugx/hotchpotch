package design.mode.dm.behavior.op.v1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 14:37
 */
public class Vipcn implements ConcreteSubject{

    private  List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        if(observer != null){
            observers.add(observer);
        }
    }

    @Override
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notice(String message) {
        for(Observer observer : observers){
            observer.update(message);
        }
    }

    public int getObserverCount(){
        return observers.size();
    }
}
