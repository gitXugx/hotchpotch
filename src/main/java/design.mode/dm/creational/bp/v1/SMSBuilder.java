package design.mode.dm.creational.bp.v1;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/25 17:31
 */
public class SMSBuilder extends Builder{

    @Override
    void buildLoadPerson() {
        super.markting.setLoadPerson("加载需要短信营销人");
    }

    @Override
    void buildSend() {
        super.markting.setSend("开始发生短信营销");
    }

    @Override
    void buildFilter() {
        super.markting.setFilterPerson("过滤短信黑名单");
    }

}
