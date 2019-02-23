package design.mode.dm.creational.bp.v1;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/25 17:29
 */
public class Markting {
    private String loadPerson;
    private String filterPerson;
    private String send;

    public String getLoadPerson() {
        return loadPerson;
    }

    public void setLoadPerson(String loadPerson) {
        this.loadPerson = loadPerson;
    }

    public String getFilterPerson() {
        return filterPerson;
    }

    public void setFilterPerson(String filterPerson) {
        this.filterPerson = filterPerson;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    @Override
    public String toString() {
        return "Markting{" +
                "loadPerson='" + loadPerson + '\'' +
                ", filterPerson='" + filterPerson + '\'' +
                ", send='" + send + '\'' +
                '}';
    }
}
