package design.mode.designphilosophy.isp;

/**
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/18 16:13
 */
public class SMSMarketingServiceImpl implements SMSMarketingService{

    @Override
    public void sendMarketingSMS() {
        loadPerson();
        sendSMS();
    }

    private void loadPerson(){
        System.out.println("查询需要营销的人完毕");
    }

    private void sendSMS(){
        System.out.println("发送短信完毕");
    }

}
