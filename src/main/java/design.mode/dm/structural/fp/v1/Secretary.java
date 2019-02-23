package design.mode.dm.structural.fp.v1;

/**
 * @author ：ex-xugaoxiang001
 * @description ：秘书
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/27 16:26
 */
public class Secretary {

    private  Boss boss = new Boss();

    public void bossWork(){
        boss.work();
    }

    public void conference(){
        boss.conference();
    }

}
