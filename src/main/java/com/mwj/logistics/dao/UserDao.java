package com.mwj.logistics.dao;

import com.mwj.logistics.entry.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by MWJ on 2017/12/25.
 */
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    public User findByUserName(String userName);

    public User findByUserNameAndPassword(String userName,String password);
}
