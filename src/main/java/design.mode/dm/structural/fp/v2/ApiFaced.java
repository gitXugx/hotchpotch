package design.mode.dm.structural.fp.v2;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/27 17:34
 */
public class ApiFaced {

    private NotificationService notificationService;
    private TransferService transferService;

    public ApiFaced(NotificationService notificationService, TransferService transferService) {
        this.notificationService = notificationService;
        this.transferService = transferService;
    }

    public void transferApi(){
        //可以做任何事情
        transferService.transfer();
        notificationService.notification();
    }

}
