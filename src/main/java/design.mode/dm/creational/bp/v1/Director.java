package design.mode.dm.creational.bp.v1;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/25 17:51
 */
public class Director {

    private Builder builder;

    public Director(Builder builder){
        this.builder = builder;
    }
    public Markting construct(){
        builder.buildLoadPerson();
        builder.buildSend();
        builder.buildFilter();
        return builder.createMarkting();
    }
}
