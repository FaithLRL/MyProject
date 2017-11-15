package com.lrl.inject.static_factory;

public class MyBeanFactory {

    /**
     * 创建实例：静态工厂
     * @return
     */
    public static UserService createService(){
        return  new UserServiceImpl();
    }
}
