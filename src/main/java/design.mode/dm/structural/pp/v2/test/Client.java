package design.mode.dm.structural.pp.v2.test;

import design.mode.dm.structural.pp.v2.*;

import java.lang.reflect.Proxy;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/27 18:10
 */
public class Client {
    public  static void main(String[] args){

        //前台收邮件
        FrontDeskProxy frontDeskProxy = new FrontDeskProxy(new ReceiveDelivery());
        Receive receive = (Receive)Proxy.newProxyInstance(frontDeskProxy.getClass().getClassLoader(), new Class[]{Receive.class}, frontDeskProxy);
        receive.receive();

        //办卡
        frontDeskProxy.setObject(new ApplyDoorCard());
        ApplyForCard applyForCard = (ApplyForCard)Proxy.newProxyInstance(frontDeskProxy.getClass().getClassLoader(), new Class[]{ApplyForCard.class}, frontDeskProxy);
        applyForCard.ApplyCard();
    }
}
