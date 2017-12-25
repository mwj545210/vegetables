package com.mwj.demo_hibernate_springmvc.service;

import com.mwj.demo_hibernate_springmvc.entry.User;

import java.util.List;

/**
 * Created by MWJ on 2017/12/25.
 */
public interface IUserService {
    List<User> findAllUser();
}
