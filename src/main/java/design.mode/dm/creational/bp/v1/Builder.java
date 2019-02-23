package design.mode.dm.creational.bp.v1;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/25 17:30
 */
public abstract class Builder {
    protected Markting markting =  new Markting();

    abstract void buildLoadPerson();

    abstract void buildSend();

    abstract void buildFilter();

    protected Markting createMarkting(){
        return markting;
    }
}
