package com.lrl.inject.static_factory;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestStaticFactory {

    @Test
    public void Test1(){
        UserService userService = MyBeanFactory.createService();
        userService.addUser();
    }
    @Test
    public void Test2(){
        String xmlPath="staticFactoryId.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        UserService userService = applicationContext.getBean("userServiceId", UserService.class);
        userService.addUser();
    }

}
