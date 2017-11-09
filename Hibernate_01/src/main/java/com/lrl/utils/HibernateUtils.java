package com.lrl.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//完成Hibernate工具类
//封装配置文件读取操作
//封装Sessionfactroy创建操作
//封装session获得操作
public class HibernateUtils {

    private static SessionFactory sessionFactory;

    static {
        //1 读取配置文件
        Configuration conf = new Configuration().configure();
        //2 根据配置 创建Factory，获得工厂
        sessionFactory  = conf.buildSessionFactory();
        //虚拟机关闭时，释放SessionFactory
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("虚拟机关闭，资源释放！");
                sessionFactory.close();

            }
        }));
    }

    public static Session openSession(){

        //3 通过获得操作数据库的session对象

        Session session = sessionFactory.openSession();


        return session;
    }

    public static Session getCurrentSession(){

        //3 通过获得操作数据库的session对象
        Session session = sessionFactory.getCurrentSession  ();

        return session;
    }

    //测试
    public static void main(String[] args) {
        System.out.println(openSession());
    }
}
