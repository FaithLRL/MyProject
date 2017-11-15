package com.lrl.di;


import com.lrl.ioc.UserService;
import com.lrl.ioc.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class TestDI {
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

        //User
//        UserService u =(UserService) applicationContext.getBean("userserviceId");
//        u.addUser();

        //Book
        BookService bookService =(BookService) applicationContext.getBean("bookserviceId");
        bookService.addBook();

    }

    @Test
    public void Test3(){
        //Spring容器获得
        //1.获得容器
        String xmlPath= "applicationContext.xml";
        BeanFactory beanFactory= new XmlBeanFactory(new ClassPathResource(xmlPath));

        //2.获取内容----不需要去new，都是从Spring容器获得
        BookService bookService =(BookService) beanFactory.getBean("bookserviceId");
        bookService.addBook();

    }
}
