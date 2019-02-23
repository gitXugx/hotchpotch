package design.mode.dm.behavior.crp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/29 13:02
 */
public class SignChain {
   private  List<Sign> signs = new ArrayList<>();


    public void add(Sign sign){
        if(sign != null){
            signs.add(sign);
        }
    }

    public void doApply(String name , String comment){

        for (Sign sign :signs){
            sign.apply(name , comment);
        }
    }
}
