package com.lrl.ioc;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestIOC {
    @Test
    public void Test1(){
        //以前的调用方法
        UserService u = new UserServiceImpl();
        u.addUser();
    }

    @Test
    public void Test2(){
        //Spring容器获得
        //1.获得容器
        String xmlPath= "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);

        //2.获取内容----不需要去new，都是从Spring容器获得
        UserService u =(UserService) applicationContext.getBean("userserviceId");
        u.addUser();

    }
}
