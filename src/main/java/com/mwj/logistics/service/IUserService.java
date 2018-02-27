package com.mwj.logistics.service;

import com.mwj.core.paging.Pagination;
import com.mwj.logistics.entry.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by MWJ on 2017/12/25.
 */
public interface IUserService {
    List<User> findAllUser();

    Page<User> pageUser(Pagination<User> pagination);

    boolean login(String userName, String password);
}
