package com.lrl.study.service;

import com.lrl.study.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;


public interface UserService {
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    List<User> findByUserName(String username);

    /**
     *增加或者修改用户
     * @param user
     */
    User saveOrUpdate(User user);

    /**
     *修改用户密码
     * @param user
     * @param oldPassword
     * @param password1
     * @param password2
     */
    void updatePwd(User user, String oldPassword, String password1, String password2);

    /**
     *根据关键字获取分页
     * @param searchText
     * @param pageRequest
     * @return
     */
    Page<User> findAllByLike(String searchText, PageRequest pageRequest);

}
