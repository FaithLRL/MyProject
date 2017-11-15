package com.lrl.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.InvocationHandler;


import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("前方法"+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("后方法"+beanName);
        //生产jdk代理
        return Proxy.newProxyInstance(
                MyBeanPostProcessor.class.getClassLoader(),
                bean.getClass().getInterfaces(),
                new java.lang.reflect.InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        System.out.println("_________________开启事务");
                        Object obj = method.invoke(bean,args);

                        System.out.println("_________________提交事务");

                        return obj;
                    }
                }
        );
    }
}
