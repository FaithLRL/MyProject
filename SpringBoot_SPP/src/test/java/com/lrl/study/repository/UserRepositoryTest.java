package com.lrl.study.repository;

import com.lrl.study.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save(){
        User user = new User();
        long time =System.currentTimeMillis();
        Date date=new Date(time);

        user.setUserName("吕荣领");
        user.setNickName("领领");
        user.setPassword("123456789");
        user.setSex(1);
        user.setBirthday(date);
        user.setTelephone("18057726735");
        user.setEmail("lrl9520@163.com");
        user.setAddress("浙江温州");
        user.setDeleteStatus(1);
        user.setLocked(0);
        user.setDescription("普通会员");
        Assert.assertNotNull(this.userRepository.save(user));

    }

    @Test
    public void findOne(){
//        Assert.assertNotNull(this.userRepository.findOne(1));
        System.out.println(this.userRepository.findOne(1));
    }

    @Test
    public void findAll(){
//        Assert.assertNotNull(this.userRepository.findOne(1));
        System.out.println(this.userRepository.findAll());
    }

    @Test
    public void update(){
        User user = new User();
        long time =System.currentTimeMillis();
        Date date=new Date(time);
        user.setId(1);
        user.setUserName("吕领领");
        user.setNickName("驴子");
        user.setPassword("123456789");
        user.setSex(1);
        user.setBirthday(date);
        user.setTelephone("18057726735");
        user.setEmail("lrl9520@163.com");
        user.setAddress("河南信阳");
        user.setDeleteStatus(1);
        user.setLocked(0);
        user.setDescription("超级会员");
        Assert.assertNotNull(this.userRepository.save(user));
    }

    @Test
    public void findByUserName() throws Exception {
        List<User> list =this.userRepository.findByUserName("吕领领");
        Assert.assertNotNull(list);
        System.out.println(list);
    }

}