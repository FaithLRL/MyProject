package com.lrl.study.service.imp;

import com.lrl.study.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImpTest {

    @Autowired
    private UserServiceImp userServiceImp;
    @Test
    public void findByUserName() throws Exception {
        List<User> list =this.userServiceImp.findByUserName("吕领领");
        Assert.assertNotNull(list);
        System.out.println(list);
    }

    @Test
    public void saveOrUpdate() throws Exception {
        User user = new User();
        long time =System.currentTimeMillis();
        Date date=new Date(time);
        user.setUserName("dssf");
        user.setNickName("dsfsd");
        user.setPassword("123456789");
        user.setSex(1);
        user.setBirthday(date);
        user.setTelephone("18057726735");
        user.setEmail("lrl9520@163.com");
        user.setAddress("河南信阳");
        user.setDeleteStatus(1);
        user.setLocked(0);
        user.setDescription("会员");
        Assert.assertNotNull(userServiceImp.saveOrUpdate(user));
    }

    @Test
    public void updatePwd() throws Exception {
    }

    @Test
    public void findAllByLike() throws Exception {
    }

}