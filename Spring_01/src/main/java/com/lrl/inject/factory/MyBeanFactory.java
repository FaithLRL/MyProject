package com.lrl.inject.factory;

public class MyBeanFactory {

    /**
     * 创建实例：实例工厂
     * @return
     */
    public  UserService createService(){
        return  new UserServiceImpl();
    }
}
