package design.mode.dm.behavior.tp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 13:47
 */
public class NetworkGame extends Game{

    @Override
    void initGame() {
        System.out.println("初始化网络游戏成功");
    }

    @Override
    void startGame() {
        System.out.println("开始玩网路游戏");
    }

    @Override
    void endGame() {
        System.out.println("网络游戏结束");
    }
}
