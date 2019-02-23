package design.mode.dm.creational.bp.v1;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/25 17:31
 */
public class EmailBuilder extends Builder{

    @Override
    void buildLoadPerson() {
        super.markting.setLoadPerson("加载需要邮件营销人");
    }

    @Override
    void buildSend() {
        super.markting.setSend("开始发送邮件");
    }

    @Override
    void buildFilter() {
        super.markting.setFilterPerson("过滤邮件名单");
    }

}
