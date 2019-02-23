# 接口隔离原则
> 客户端不应该依赖它不需要的接口，一个类对另一个类的依赖应该建立在最小的接口上。

- [x] 定义
- [x] 使用的目的
- [x] 使用场景
- [ ] 总结
## 定义
客户端不应该依赖它不需要的接口；一个类对另一个类的依赖应该建立在最小的接口上。

1. 如果一个类要遵守单一职责原则，那他实现的接口也应该遵守单一职责原则。
2. 单一原则主要约束的是类和实现。接口隔离主要约束的是接口和抽象，让我们更加合理的构建框架。
3. 客户端不应该依赖他不需要的接口，实际上是臃肿的接口进行拆分，继承来提高灵活度，也可以是增强接口的内聚性，来减少不必要的接口，来减少接口与客户端的联系（降低耦合性，提高可扩展性）
4. 类与类的依赖应该建立在最小的接口，为依赖接口的暴露最少的方法提供服务（高内聚）

## 使用的目的
- 定义了接口的约束规范
- 降低类与类之间的交流，降低耦合性，使用最少的接口提供完善的服务，提高内聚性

## 使用场景

**SMSMarketingService 接口**

``````java
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
``````

**SMSMarketingServiceImpl 接口**

``````java

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

``````

**客户端：**

``````java

public class Test {

    public  static void main(String[] args){
        SMSMarketingService smsMarketingService = new SMSMarketingServiceImpl();
        //这里Test客户端依赖的是SMSMarketingService，是不是很想我们日常代码中的Spring注入的Service
        //其它类只与接口进行交流，而不是实现类
        smsMarketingService.sendMarketingSMS();
    }

}

``````

## 总结

- 如果项目中稍微复杂的情况下，模块接口划分要尽量清晰或者Service之间调用，应该准守类与类之间的依赖是通过接口而不是实现类，其实暴露太多接口会造成客户端调用困惑，不知道调用哪个





