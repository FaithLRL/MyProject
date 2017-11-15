package com.lrl.lifecycle;

public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println("lifecycle add User");
    }

    public void myInit(){
        System.out.println("初始化");
    }

    public void myDestory(){
        System.out.println("销毁");
    }
}
