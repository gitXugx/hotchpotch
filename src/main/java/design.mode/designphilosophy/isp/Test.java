package design.mode.designphilosophy.isp;

/**
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/18 16:14
 */
public class Test {

    public  static void main(String[] args){
        SMSMarketingService smsMarketingService = new SMSMarketingServiceImpl();
        //这里Test客户端依赖的是SMSMarketingService，是不是很想我们日常代码中的Spring注入的Service
        //其它类只与接口进行交流，而不是实现类
        smsMarketingService.sendMarketingSMS();
    }

}
