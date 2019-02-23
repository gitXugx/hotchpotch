package design.mode.dm.creational.fp.sfp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/21 14:53
 */
public class CompactCar implements  Car{
    private String name;
    private String price;

    public CompactCar(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CompactCar{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
