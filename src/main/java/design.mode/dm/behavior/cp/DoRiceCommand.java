package design.mode.dm.behavior.cp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 16:18
 */
public class DoRiceCommand implements Command{

    private DoRiceHandler handler;

    public DoRiceCommand(DoRiceHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.doSomething();
    }
}
