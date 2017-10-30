package com.lrl.service;

import com.lrl.domain.User;

/**
 * 用户相关操作的业务层接口
 *
 * */
public interface IUserService {
    /**
     * 判断用户是否存在
     * @param username
     * @return
     */
    User findUserByUsername(String username);
    /**
     * 用户注册
     * @param user
     * @return
     */
    int register(User user);
}
