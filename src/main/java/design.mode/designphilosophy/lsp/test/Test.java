package design.mode.designphilosophy.lsp.test;

import design.mode.designphilosophy.lsp.EmailMarketing;
import design.mode.designphilosophy.lsp.MarketingBasics;
import design.mode.designphilosophy.lsp.SMSMarketing;

/**
 * @author ：ex-xugaoxiang001
 * @description ：里氏替换
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/18 13:31
 */
public class Test {
    public  static void main(String[] args){
        MarketingBasics smsMarketing = new SMSMarketing();

        smsMarketing.execute();

        MarketingBasics emailMarketing = new EmailMarketing();

        emailMarketing.execute();
    }
}
