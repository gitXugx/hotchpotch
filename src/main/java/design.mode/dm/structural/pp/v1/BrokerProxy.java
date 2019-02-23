package design.mode.dm.structural.pp.v1;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/27 17:57
 */
public class BrokerProxy implements IStar{

    private IStar star;
    //一般情况下这里是bean注入
    public BrokerProxy(IStar star) {
        this.star = star;
    }

    @Override
    public void openingAConcert() {
        before();
        star.openingAConcert();
        after();
    }


    private void before(){
        System.out.println("价钱谈好");
    }

    private void after(){
        System.out.println("结账");
    }

}
