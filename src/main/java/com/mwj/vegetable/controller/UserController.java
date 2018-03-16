package com.mwj.vegetable.controller;

import com.mwj.vegetable.entry.User;
import com.mwj.vegetable.service.IUserService;
import com.mwj.vegetable.vo.ResponseResult;
import com.mwj.vegetable.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by MWJ on 2017/12/25.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult addUser(@ModelAttribute User user) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setResult(Result.SUCCESS);
        try {
            userService.addUser(user);
        } catch (Exception e) {
            responseResult.setResult(Result.FAILURE);
            responseResult.setMessage(e.getMessage());
        }
        return responseResult;
    }
}
