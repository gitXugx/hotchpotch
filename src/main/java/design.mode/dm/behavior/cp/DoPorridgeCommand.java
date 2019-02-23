package design.mode.dm.behavior.cp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 16:16
 */
public class DoPorridgeCommand implements Command{

    private DoProrridgeHandler handler;
    //一般情况下注入
    public DoPorridgeCommand(DoProrridgeHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.doSomething();
    }
}
