package design.mode.dm.creational.pp.ProfundityClone;

import java.util.Date;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/26 10:47
 */
public class Bean implements Cloneable{

    private String name;
    private Date createTime;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Bean clone = (Bean) super.clone();
        clone.setCreateTime( (Date)this.createTime.clone());
        return clone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
