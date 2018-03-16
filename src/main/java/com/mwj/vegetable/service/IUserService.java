package com.mwj.vegetable.service;

import com.mwj.vegetable.entry.User;

/**
 * Created by MWJ on 2017/12/25.
 */
public interface IUserService {

    User login(String userName, String password);

    void addUser(User user) throws Exception;
}
