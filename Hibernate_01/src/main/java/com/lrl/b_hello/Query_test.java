package com.lrl.b_hello;

import com.lrl.a_hello.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;
//详解Query对象
import java.util.List;

public class Query_test  {
    @Test
    //Query对象封装HQL语句对象
    //Query中封装查询细节API
    public void fun6(){
        //1 读取配置文件
        Configuration conf = new Configuration().configure();
        //2 根据配置 创建Factory
        SessionFactory sessionFactory = conf.buildSessionFactory();
        //3 通过获得操作数据库的session对象
        Session session = sessionFactory.openSession();
        //打开事务
        Transaction ts = session.beginTransaction();

        Query query = session.createQuery("from com.lrl.a_hello.User");
        //指定结果从第几个开始拿
        query.setFirstResult(0);
        //指定拿几个结果
        query.setMaxResults(2);




        //query.list()将HQL语句执行，并返回结果（多行）
        List<User> list = query.list();
        System.out.println(list);

        //query.uniqueResult()将HQL语句执行，并返回结果（一行）
//        User u =(User) query.uniqueResult();
//        System.out.println(u);
        //提交事务
        ts.commit();
        //5 关闭资源
        session.close();
        sessionFactory.close();
    }
}
