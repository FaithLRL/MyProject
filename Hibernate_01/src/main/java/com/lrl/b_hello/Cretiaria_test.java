package com.lrl.b_hello;

import com.lrl.a_hello.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;
//详解Cretiaria_对象
import java.util.List;

public class Cretiaria_test  {
    @Test
    //Cretiaria对象 与Query对象功能很像
    //Cretiaria控制查询
    public void fun1(){
        //1 读取配置文件
        Configuration  conf = new Configuration().configure();
        //2 根据配置 创建Factory
        SessionFactory sessionFactory = conf.buildSessionFactory();
        //3 通过获得操作数据库的session对象
        Session session = sessionFactory.openSession();
        //打开事务
        Transaction ts = session.beginTransaction();
        //4 操作数据库
        //Criteria 查询 → Hiberbate独有的面向对象查询（无查询语句）
        Criteria criteria = session.createCriteria(User.class);
        //查找name属性值为tom的记录
        criteria.add(Restrictions.eq("name","jerry"));
        //select * from t_user
        //list()将查询执行并返回结果（多行）
//        List<User> list = criteria.list();
//        System.out.println(list);

        //返回一个结果集
        User u =(User) criteria.uniqueResult();
        System.out.println(u);

        //提交事务
        ts.commit();
        //5 关闭资源
        session.close();
        sessionFactory.close();
    }

    @Test
    //Cretiaria对象 与Query对象功能很像
    //Cretiaria控制查询
    public void fun2(){
        //1 读取配置文件
        Configuration  conf = new Configuration().configure();
        //2 根据配置 创建Factory
        SessionFactory sessionFactory = conf.buildSessionFactory();
        //3 通过获得操作数据库的session对象
        Session session = sessionFactory.openSession();
        //打开事务
        Transaction ts = session.beginTransaction();
        //4 操作数据库
        //Criteria 查询 → Hiberbate独有的面向对象查询（无查询语句）
        Criteria criteria = session.createCriteria(User.class);
        //查找name属性值为tom的记录
//        criteria.add(Restrictions.eq("name","jerry"));
        //查找名字中包含r 的用户
        criteria.add(Restrictions.like("name" ,"%r%"));


        //返回一个结果集
//        User u =(User) criteria.uniqueResult();
//        System.out.println(u);

        //select * from t_user
        //list()将查询执行并返回结果（多行）
        List<User> list = criteria.list();
        System.out.println(list);

        //提交事务
        ts.commit();
        //5 关闭资源
        session.close();
        sessionFactory.close();
    }


    @Test
    /**
     * Cretiaria对象 与Query对象功能很像
     * Cretiaria控制查询
     *
     * gt  >
     * lt  <
     * eq  =
     * ge  >=
     * le  <=
     * like
     * betweem
     */
    public void fun3(){
        //1 读取配置文件
        Configuration  conf = new Configuration().configure();
        //2 根据配置 创建Factory
        SessionFactory sessionFactory = conf.buildSessionFactory();
        //3 通过获得操作数据库的session对象
        Session session = sessionFactory.openSession();
        //打开事务
        Transaction ts = session.beginTransaction();
        //4 操作数据库
        //Criteria 查询 → Hiberbate独有的面向对象查询（无查询语句）
        Criteria criteria = session.createCriteria(User.class);
        //查找name属性值为tom的记录
//        criteria.add(Restrictions.eq("name","jerry"));
        //查找id大于1的用户
        criteria.add(Restrictions.gt("id" ,3));


        //返回一个结果集
//        User u =(User) criteria.uniqueResult();
//        System.out.println(u);

        //select * from t_user
        //list()将查询执行并返回结果（多行）
        List<User> list = criteria.list();
        System.out.println(list);

        //提交事务
        ts.commit();
        //5 关闭资源
        session.close();
        sessionFactory.close();
    }
}
