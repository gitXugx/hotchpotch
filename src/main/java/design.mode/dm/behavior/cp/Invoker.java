package design.mode.dm.behavior.cp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 17:14
 */
public class Invoker {

    private Command command1;
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void invoker(){
        command.execute();
    }
}
