package com.lrl.coll;

import com.lrl.constructor.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestColl {

    @Test
    public void Test2(){
        String xmlPath="applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        CollData collData = applicationContext.getBean("collDataId", CollData.class);
        System.out.println(collData);

    }

}
