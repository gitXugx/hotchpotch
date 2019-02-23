package design.mode.dm.creational.fp.afp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/24 16:52
 */
public class AdidasFactory implements Factory{
    @Override
    public IShoe createShoe() {
        return new AdidasShoe();
    }

    @Override
    public IClothes createClothes() {
        return new AdidasClothes();
    }
}
