package design.mode.dm.behavior.tp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 13:48
 */
public class Client {
    public  static void main(String[] args){
        Game singleGame = new SingleGame();
        singleGame.play();

        Game networkGame = new NetworkGame();
        networkGame.play();
    }
}
