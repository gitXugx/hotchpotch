package design.mode.designphilosophy.lsp;

import java.util.List;

/**
 * @author ：ex-xugaoxiang001
 * @description ：里氏替换原则
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/18 14:26
 */
public abstract class MarketingBasics {

    /**
     * 可以有多种加载人的实现 数据库，excel文件 ， 第三方等
     * @return
     */
    abstract List<Person>  loadPerson();

    /**
     * 所有营销都应该以
     * 1. 先找要营销的人
     * 2. 发送
     */
     public void execute(){
        List<Person> people = loadPerson();
        send(people);
    }

    //发送同样可以 短信  邮件 app通知 等不同类型 这边只是简单打印
    abstract void send(List<Person> personList);
}
