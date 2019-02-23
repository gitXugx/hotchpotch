package design.mode.designphilosophy.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/18 15:06
 */
public class EmailMarketing extends MarketingBasics{

    @Override
    List<Person> loadPerson() {
        //随便加载一点人
        List<Person> personList = new ArrayList<>();
        //随便模拟几个人
        for(int i = 0 ; i < 5 ; i++){
            Person person = new Person();
            person.setEmail("60394969"+i+"@qq.com");
            person.setNick("xug"+i);
            personList.add(person);
        }
        return personList;
    }

    @Override
    void send(List<Person> personList) {
        personList.forEach(x -> System.out.println("开始邮件发送：邮箱："+x.getEmail()+"  昵称："+x.getNick() ));
    }

    @Override
    public void execute() {
        System.out.println("重写父类方法");
    }
}
