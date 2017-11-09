package com.lrl.b_hello;

import com.lrl.a_hello.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class Configuration_test {
    @Test
    public void fun1(){
        //1 读取配置文件
        Configuration  conf = new Configuration().configure();
        //2 根据配置 创建Factory
        SessionFactory sessionFactory = conf.buildSessionFactory();
        //3 通过获得操作数据库的session对象
        Session session = sessionFactory.openSession();
        //4 操作数据库
        User u = new User();

        //5 关闭资源
        session.close();
        sessionFactory.close();
    }
}