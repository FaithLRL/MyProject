package com.lrl.constructor;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConstructor {

    @Test
    public void Test2(){
        String xmlPath="applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        User user = applicationContext.getBean("userId", User.class);
        System.out.println(user);

    }

}
