package design.mode.dm.structural.pp.v2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/28 14:55
 */
public class FrontDeskProxy implements InvocationHandler {

    private Object object;

    public void setObject(Object object) {
        this.object = object;
    }

    public FrontDeskProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(object , args);
        after();
        return null;
    }


    private void before(){
        System.out.println("接受到邮件");
    }

    private void after(){
        System.out.println("处理完毕邮件通知");
    }

}
