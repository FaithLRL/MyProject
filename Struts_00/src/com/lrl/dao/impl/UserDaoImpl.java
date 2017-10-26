package com.lrl.dao.impl;

import com.lrl.dao.IUserDao;
import com.lrl.domain.User;
import com.lrl.utils.DBCPUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class UserDaoImpl implements IUserDao {

    private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());

    @Override
    public User findUserByUsername(String username) {
        try {
            return qr.query("select *from user where username=?",new BeanHandler<User>(User.class),username);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
   }

    @Override
    public int addUser(User user) {

        try {
            return qr.update("insert into user(username,password birthday,hobby,married) values(?,?,?,?,?)",
                    user.getUsername(),
                    user.getPassword(),
                    user.getBirthday(),
                    user.getHobby(),
                    user.getMarried()

            );
        }catch (Exception e){
            throw new RuntimeException(e);
        }    }
}
