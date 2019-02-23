package design.mode.dm.structural.pp.v1.Test;

import design.mode.dm.structural.pp.v1.BrokerProxy;
import design.mode.dm.structural.pp.v1.Star;

/**
 * @author ：ex-xugaoxiang001
 * @description ：静态代理模式
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/27 18:06
 */
public class Client {

    public  static void main(String[] args){

        BrokerProxy brokerProxy = new BrokerProxy(new Star());

        brokerProxy.openingAConcert();

    }

}
