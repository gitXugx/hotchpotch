package design.mode.designphilosophy.isp;

/**
 * @description ：接口隔离原则 客户端不需要知道他不需要的接口，所以在设计的时候需要注意
 * 接口粒度，和模块的功能
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/18 16:12
 */
public interface SMSMarketingService {

    void sendMarketingSMS();

    //客户端不需要知道怎么发
//    void loadPerson();
//    void sendSMS();
    //应该进行接口拆分
//    void sendMarketingEmail();

}
