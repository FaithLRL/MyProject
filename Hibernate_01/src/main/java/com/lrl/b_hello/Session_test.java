package com.lrl.b_hello;

import com.lrl.a_hello.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import org.hibernate.query.Query;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 增删查改
 */
public class Session_test {

    //增
    @Test
    public void fun1(){
        //1 读取配置文件
        Configuration  conf = new Configuration().configure();
        //2 根据配置 创建Factory
        SessionFactory sessionFactory = conf.buildSessionFactory();
        //3 通过获得操作数据库的session对象
        Session session = sessionFactory.openSession();
        //4 操作数据库
        User u =new User();
        u.setName("Vdsaas");
        u.setPassword("1234");
        //调用Session的save方法保存对象到数据库中
        session.save(u);
        //5 关闭资源
        session.close();
        sessionFactory.close();
    }

    //改
    @Test
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
        //查询出你要操作的对象
        User u = (User)session.get(User.class,1);
        //在查询结果上进行修改
        u.setName("汤姆");

        //调用Session的update方法修改对象
        session.update(u);
        //提交事务
        ts.commit();
        //5 关闭资源
        session.close();
        sessionFactory.close();
    }
    //删
    @Test
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
        //查询出你要操作的对象
//        User u = (User)session.get(User.class,1);
        User u = new User();
        u.setId(2);
        //调用Session的delete方法删除对象，根据ID进行删除
        session.delete(u);
        //提交事务
        ts.commit();
        //5 关闭资源
        session.close();
        sessionFactory.close();
    }

    @Test
    //调用Session的save方法保存对象到数据库中
    //1.查Session.get();
    public void fun4(){
        //1 读取配置文件
        Configuration  conf = new Configuration().configure();
        //2 根据配置 创建Factory
        SessionFactory sessionFactory = conf.buildSessionFactory();
        //3 通过获得操作数据库的session对象
        Session session = sessionFactory.openSession();
        //打开事务
        Transaction ts = session.beginTransaction();
        //4 操作数据库
        //查询出你要操作的对象
        User u = (User)session.get(User.class,1);
        System.out.println(u);
        //提交事务
        ts.commit();
        //5 关闭资源
        session.close();
        sessionFactory.close();
    }

    @Test
    //调用Session的save方法保存对象到数据库中
    //2.查Session.load();
    public void fun5(){
        //1 读取配置文件
        Configuration  conf = new Configuration().configure();
        //2 根据配置 创建Factory
        SessionFactory sessionFactory = conf.buildSessionFactory();
        //3 通过获得操作数据库的session对象
        Session session = sessionFactory.openSession();
        //打开事务
        Transaction ts = session.beginTransaction();
        //4 操作数据库
        //查询出你要操作的对象
        User u = (User)session.get(User.class,1);
        System.out.println(u);
        //提交事务
        ts.commit();
        //5 关闭资源
        session.close();
        sessionFactory.close();
    }

    @Test
    //调用Session的save方法保存对象到数据库中
    //3.cancelQuery();传入HQL语句进行查询，查询所有User
    public void fun6(){
        //1 读取配置文件
        Configuration  conf = new Configuration().configure();
        //2 根据配置 创建Factory
        SessionFactory sessionFactory = conf.buildSessionFactory();
        //3 通过获得操作数据库的session对象
        Session session = sessionFactory.openSession();
        //打开事务
        Transaction ts = session.beginTransaction();
        //4 操作数据库
       //HQL语言 → Hibernate Query Language
        //cancelQuery();传入HQL语句进行查询
       Query query = session.createQuery("from com.lrl.a_hello.User");
       //list 将语句执行，并返回结果
       List<User> list = query.list();

        System.out.println(list);

        //提交事务
        ts.commit();
        //5 关闭资源
        session.close();
        sessionFactory.close();
    }

    @Test
    //调用Session的save方法保存对象到数据库中
    //3.Criteria 查询所有User
    public void fun7(){
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

       List<User> list = criteria.list();

        System.out.println(list);


        //提交事务
        ts.commit();
        //5 关闭资源
        session.close();
        sessionFactory.close();
    }

    @Test
    //调用Session的save方法保存对象到数据库中
    //3.Criteria 查询所有User
    public void fun8(){
        //1 读取配置文件
        Configuration  conf = new Configuration().configure();
        //2 根据配置 创建Factory
        SessionFactory sessionFactory = conf.buildSessionFactory();
        //3 通过获得操作数据库的session对象
        Session session = sessionFactory.openSession();
        //打开事务
        Transaction ts = session.beginTransaction();
        //4 操作数据库
        //原生的SQL语句查询
        SQLQuery sqlQuery = session.createSQLQuery("select * from t_user");

        List<Object[]> list = sqlQuery.list();
        for (Object[] objects:list){
            System.out.println(Arrays.toString(objects));
        }

        //打印
        System.out.println(list);
        //提交事务
        ts.commit();
        //5 关闭资源
        session.close();
        sessionFactory.close();
    }
}