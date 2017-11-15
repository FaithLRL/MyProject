package com.lrl.setter;


import com.lrl.ioc.UserService;
import com.lrl.ioc.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class TestSetter {
    @Test
    public void Test1(){
        //Spring容器获得
        //1.获得容器
        String xmlPath= "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);

        PerSon perSon = applicationContext.getBean("perSonId",PerSon.class);
        System.out.println(perSon);

    }
}
