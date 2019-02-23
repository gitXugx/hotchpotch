package design.mode.dm.structural.pp.v2;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/27 18:11
 */
public class ReceiveDelivery implements Receive{

    @Override
    public void receive() {
        System.out.println("以帮忙接收快递");
    }
}
