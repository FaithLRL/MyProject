package com.lrl.inject.static_factory;

public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println("static_factory add User");
    }
}
