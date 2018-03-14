package com.mwj.vegetable.controller;

import com.mwj.vegetable.entry.User;
import com.mwj.vegetable.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by MWJ on 2017/12/25.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping(value = "userLogin", method = RequestMethod.GET)
    public String addUser(@ModelAttribute("user") User user, ModelMap modelMap) {
        return "index";
    }


    @RequestMapping(value = "pageUser", method = RequestMethod.POST)
    public Page<User> pageUser(@ModelAttribute("user") User user, ModelMap modelMap) {
        return null;
    }
}
