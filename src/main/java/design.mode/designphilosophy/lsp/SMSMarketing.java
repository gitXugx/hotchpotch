package design.mode.designphilosophy.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/18 14:49
 */
public class SMSMarketing extends MarketingBasics{

    List<Person> loadPerson() {
        List<Person> personList = new ArrayList<>();
        //随便模拟几个人
        for(int i = 0 ; i < 5 ; i++){
            Person person = new Person();
            person.setMobile("176021270"+i);
            person.setNick("xgx"+i);
            personList.add(person);
        }
        return personList;
    }

    void send(List<Person> personList) {
        personList.forEach(x -> System.out.println("开始短信发送：手机号："+x.getMobile()+"  昵称："+x.getNick() ));
    }
}
