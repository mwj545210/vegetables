package com.mwj.demo_hibernate_springmvc.dao;

import com.mwj.demo_hibernate_springmvc.entry.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by MWJ on 2017/12/25.
 */
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    public User findByUserName(String userName);
}
