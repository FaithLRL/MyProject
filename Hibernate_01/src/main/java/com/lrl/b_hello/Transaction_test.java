package com.lrl.b_hello;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 *
 * 详解Transaction事务对象
 *
 * 开启事务：beginTransaction()
 * 获得事务：getTransaction()
 * 提交事务：commit()
 * 回滚事务：rollback()
 */
public class Transaction_test {

    @Test
    //调用Session的save方法保存对象到数据库中
    //3.Criteria 查询所有User
    public void fun1(){
        //1 读取配置文件
        Configuration conf = new Configuration().configure();
        //2 根据配置 创建Factory
        SessionFactory sessionFactory = conf.buildSessionFactory();
        //3 通过获得操作数据库的session对象
        Session session = sessionFactory.openSession();
        //打开事务
        Transaction ts = session.beginTransaction();
        //获得已打开事务对象
        session.getTransaction();
        //提交事务
        ts.commit();
        //回滚事务
        ts.rollback();
        //5 关闭资源
        session.close();
        sessionFactory.close();
    }

    @Test
    //调用Session的save方法保存对象到数据库中
    //3.Criteria 查询所有User
    public void fun2(){
        //1 读取配置文件
        Configuration conf = new Configuration().configure();
        //2 根据配置 创建Factory
        SessionFactory sessionFactory = conf.buildSessionFactory();
        //3 通过获得操作数据库的session对象
        Session session = sessionFactory.getCurrentSession();
        //事务关闭时，会自动把与当前线程关联的session关闭，并删除

        session.getTransaction().commit();

        //再获得当前线程绑定的session时，获得的是新的session
        Session session2 = sessionFactory.getCurrentSession();
        //打印
        System.out.println(session==session2);

        //5 关闭资源
        session.close();
        sessionFactory.close();
    }
}
