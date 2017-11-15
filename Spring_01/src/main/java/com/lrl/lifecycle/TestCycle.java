package com.lrl.lifecycle;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCycle {

    @Test
    public void Test2(){
        String xmlPath="applicationContext.xml";

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        UserService userService = applicationContext.getBean("lifecycleId", UserService.class);
        userService.addUser();

        //1.容器必须close ，销毁方法才会执行2.必须是单例
        applicationContext.close();
    }

}
