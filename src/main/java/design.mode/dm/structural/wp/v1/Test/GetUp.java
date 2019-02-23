package design.mode.dm.structural.wp.v1.Test;

import design.mode.dm.structural.wp.v1.*;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/28 16:33
 */
public class GetUp {

    public  static void main(String[] args){
        Person xgxMan = new XgxMan();
        Wrapper jacket = new Jacket(xgxMan);
        Wrapper pants = new Pants(jacket);
        Wrapper leatherShoes= new LeatherShoes(pants);
        leatherShoes.dress();
    }
}
