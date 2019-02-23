package design.mode.dm.structural.fp.v2.test;

import design.mode.dm.structural.fp.v2.ApiFaced;
import design.mode.dm.structural.fp.v2.NotificationService;
import design.mode.dm.structural.fp.v2.TransferService;

/**
 * @author ：ex-xugaoxiang001
 * @description ：屏蔽对外的细节
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/27 17:42
 */
public class Client {

    public  static void main(String[] args){

        ApiFaced apiFaced = new ApiFaced(new NotificationService() , new TransferService());
        apiFaced.transferApi();

    }
}
