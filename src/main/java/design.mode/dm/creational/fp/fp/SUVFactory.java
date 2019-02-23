package design.mode.dm.creational.fp.fp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/24 16:01
 */
public class SUVFactory implements CarFactory {

    @Override
    public ICar createCar() {
        return new SUVCar();
    }
}
