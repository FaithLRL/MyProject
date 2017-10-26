package com.lrl.impl;

import com.lrl.dao.IUserDao;
import com.lrl.dao.impl.UserDaoImpl;
import com.lrl.domain.User;
import com.lrl.service.IUserService;

public class UserServiceImpl implements IUserService {
    private IUserDao dao = new UserDaoImpl();
    public User findUserByUsername(String username) {
        return dao.findUserByUsername(username);
    }

    @Override
    public int register(User user) {
        return dao.addUser(user);
    }
}