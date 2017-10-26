package com.lrl.utils;


import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用开源数据源：
 * 	DBCP：DataBase Connection Pool 是apache公司实现的一个开源的数据源。
 * 使用步骤：
 * 	1.拷贝jar包
 *  2.写配置文件
 *  3.使用
 * @author zhy
 *
 */
public class DBCPUtil {
    //定义一个数据源
    private static DataSource ds;

    //使用静态代码块，为数据源赋值
    static {
        try {
            Properties properties = new Properties();
            InputStream in = DBCPUtil.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
            properties.load(in);
            ds=  BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            System.out.println("初始化连接池失败!");
        }
    }
    //提供一个获取数据源的方法
    public static DataSource getDataSource(){
        return ds;
    }
    //提供一个获取链接的方法
    public static Connection getConnection(){
        try {
            return ds.getConnection();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}