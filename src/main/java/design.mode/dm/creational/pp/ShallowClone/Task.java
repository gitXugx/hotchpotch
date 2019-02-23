package design.mode.dm.creational.pp.ShallowClone;

import java.util.Date;

/**
 * @author ：ex-xugaoxiang001
 * @description ：浅度克隆
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/26 10:32
 */
public class Task implements Cloneable{
    private String name;
    private Date startDate;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
