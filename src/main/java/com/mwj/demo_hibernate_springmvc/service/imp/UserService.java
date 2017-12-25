package com.mwj.demo_hibernate_springmvc.service.imp;

import com.mwj.demo_hibernate_springmvc.dao.UserDao;
import com.mwj.demo_hibernate_springmvc.entry.User;
import com.mwj.demo_hibernate_springmvc.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by MWJ on 2017/12/25.
 */
@Service
public class UserService implements IUserService{

    @Resource
    private UserDao userDao;

    @Override
    public List<User> findAllUser() {
        return userDao.findAll();
    }
}
