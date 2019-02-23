package design.mode.dm.behavior.cp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 16:20
 */
public class Client {
    public  static void main(String[] args){
        DoProrridgeHandler doProrridgeHandler = new DoProrridgeHandler();
        Command doPorridgeCommand = new DoPorridgeCommand(doProrridgeHandler);
        Invoker invoker = new Invoker(doPorridgeCommand);
        invoker.invoker();

        DoRiceHandler doRiceHandler = new DoRiceHandler();
        Command doRiceCommand = new DoRiceCommand(doRiceHandler);
        Invoker invoker2 = new Invoker(doRiceCommand);
        invoker2.invoker();
    }
}
