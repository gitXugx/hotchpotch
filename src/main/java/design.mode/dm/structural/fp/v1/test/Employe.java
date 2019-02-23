package design.mode.dm.structural.fp.v1.test;

import design.mode.dm.structural.fp.v1.Boss;
import design.mode.dm.structural.fp.v1.Secretary;

/**
 * @author ：ex-xugaoxiang001
 * @description ：外观模式,该例子主要使用外观模式保护目标类的信息
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/27 17:29
 */
public class Employe {

    public  static void main(String[] args){
        Secretary secretary = new Secretary();
        //当你直接去找boss boss是没有空的 你需要找秘书去申请,秘书决定要不要传达给老板
        Boss boss = new Boss();
        secretary.bossWork();
        secretary.conference();
    }

}
