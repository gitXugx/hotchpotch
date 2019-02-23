package design.mode.dm.behavior.crp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 13:08
 */
public class Client {
    public  static void main(String[] args){
        //创建审批链
        SignChain signChain = new SignChain();
        //创建审批人
        Sign manager = new Manager();
        Sign ceo = new CEO();
        Sign boss = new Boss();

        //添加审批人
        signChain.add(manager);
        signChain.add(ceo);
        signChain.add(boss);
        //开始审批
        signChain.doApply("xgx" , "过年提前一个月回家");

    }
}
