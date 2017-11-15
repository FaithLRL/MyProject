package com.lrl.inject.factory;

public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println("factory add User");
    }
}
