package design.mode.designphilosophy.srp;

/**
 * @author ：apple
 * @description ：单一职责
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/15 下午4:14
 */
public class User {

    private String name;
    private String sex;
    private Integer age;

//    /**
//     * 这个方法有问题
//     * @param name
//     */
//    public void modifyName(String name){
//        //修改名字
//        System.out.println("修改名字： "+ name);
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
