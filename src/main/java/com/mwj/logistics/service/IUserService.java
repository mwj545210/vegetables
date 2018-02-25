package com.mwj.logistics.service;

import com.mwj.logistics.entry.User;

import java.util.List;

/**
 * Created by MWJ on 2017/12/25.
 */
public interface IUserService {
    List<User> findAllUser();
}
