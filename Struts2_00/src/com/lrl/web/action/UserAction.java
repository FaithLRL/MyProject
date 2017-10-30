package com.lrl.web.action;

import com.lrl.domain.User;
import com.lrl.impl.UserServiceImpl;
import com.lrl.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户操作相关的动作类
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

    private IUserService service = new UserServiceImpl();
    //定义用户的数据模型，且必须实例化
    private User user = new User();

    @Override
    public User getModel() {
        return user;
    }

    public String register(){
        User dbUser=service.findUserByUsername(user.getUsername());
        if(dbUser !=null){
            return "exists";
        }
        int res=service.register(user);
        if(res>0){
            return SUCCESS;
        }
        return null;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
