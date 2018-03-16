package com.mwj.vegetable.service.imp;

import com.mwj.vegetable.dao.UserDao;
import com.mwj.vegetable.entry.User;
import com.mwj.vegetable.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by MWJ on 2017/12/25.
 */
@Service
public class UserService implements IUserService{

    @Resource
    private UserDao userDao;



    @Override
    public User login(String userName, String password) {
        User user = userDao.findByUserNameAndPassword(userName, password);
        return user;
    }

    @Override
    public void addUser(User user) throws Exception {
        User dbUser = userDao.findByUserName(user.getUserName());
        if (dbUser != null){
            throw new Exception("用户名已存在");
        }else {
            userDao.save(user);
        }
    }
}
