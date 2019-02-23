package design.mode.dm.creational.fp.fp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：工厂模式
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/24 16:03
 */
public class Client {
    public  static void main(String[] args){
        CarFactory compactFactory = new CompactFactory();
        ICar car = compactFactory.createCar();
        car.showCar();

        CarFactory suvFactory = new SUVFactory();
        ICar suv = suvFactory.createCar();
        suv.showCar();
    }
}
