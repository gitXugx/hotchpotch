package jvm.runningdatablock.example1;

/**
 * @author ：apple
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/27 下午10:34
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        User user = new User();
        Integer age = findAge(1995, 2019);
        user.setAge(age);
    }


    static Integer findAge(Integer startYear, Integer endYear){
        if(endYear - startYear < 0){
            return 20;
        }
        return endYear - startYear;
    }
}
