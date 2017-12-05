package com.lrl.study.service.imp;

import com.lrl.study.domain.User;
import com.lrl.study.repository.UserRepository;
import com.lrl.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findByUserName(String username) {
        return this.userRepository.findByUserName(username);
    }

    @Override
    public User saveOrUpdate(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void updatePwd(User user, String oldPassword, String password1, String password2) {

    }

    @Override
    public Page<User> findAllByLike(String searchText, PageRequest pageRequest) {
        return this.userRepository.findAllByNickNameContaining(searchText,pageRequest);
    }
}
