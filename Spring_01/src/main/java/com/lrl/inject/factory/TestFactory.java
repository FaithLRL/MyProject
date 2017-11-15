package com.lrl.inject.factory;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestFactory {

    @Test
    public void Test1(){
        //自定义实例工厂
        //创建工厂
        MyBeanFactory myBeanFactory = new MyBeanFactory();
        //通过工厂实例获得对象
        UserService userService = myBeanFactory.createService();
        userService.addUser();
    }
    @Test
    public void Test2(){
        String xmlPath="applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        UserService userService = applicationContext.getBean("userServiceId", UserService.class);
        userService.addUser();
    }

}
