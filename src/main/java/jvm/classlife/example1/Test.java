package jvm.classlife.example1;

/**
 * @author ：apple
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/27 下午10:34
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(10000L);

        User user = new User();
        user.setAge(1);
        user.setPassWord("1234");
        user.setUserName("xgx");
        System.out.println(user);
    }

}
