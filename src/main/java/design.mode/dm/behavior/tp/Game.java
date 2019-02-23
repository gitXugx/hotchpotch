package design.mode.dm.behavior.tp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 13:42
 */
public abstract class Game {
    //初始化游戏
    abstract void initGame();
    //开始游戏
    abstract void startGame();
    //结束游戏
    abstract void endGame();

    public final void play(){
        initGame();
        startGame();
        endGame();
    }
}
