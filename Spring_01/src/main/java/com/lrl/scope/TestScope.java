package com.lrl.scope;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestScope {

    @Test
    public void Test2(){
        String xmlPath="applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        UserService userService1 = applicationContext.getBean("UserServiceId", UserService.class);
        UserService userService2 = applicationContext.getBean("UserServiceId", UserService.class);


        System.out.println(userService1);
        System.out.println(userService2);
    }

}
