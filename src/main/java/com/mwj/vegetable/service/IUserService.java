package com.mwj.vegetable.service;

import com.mwj.core.paging.Pagination;
import com.mwj.vegetable.entry.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by MWJ on 2017/12/25.
 */
public interface IUserService {
    List<User> findAllUser();

    Page<User> pageUser(Pagination<User> pagination);

    User login(String userName, String password);
}
