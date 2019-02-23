package design.mode.dm.behavior.tp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 13:46
 */
public class SingleGame extends Game{

    @Override
    void initGame() {
        System.out.println("单机游戏初始化成功");
    }

    @Override
    void startGame() {
        System.out.println("开始玩游戏");
    }

    @Override
    void endGame() {
        System.out.println("游戏结束");
    }
}
