package com.mwj.logistics.controller;

import com.mwj.logistics.entry.User;
import com.mwj.logistics.service.IUserService;
import com.mwj.logistics.service.imp.UserService;
import com.mwj.logistics.vo.ResponseResult;
import com.mwj.logistics.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/")
public class LoginController {

    @Resource
    private IUserService userService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(@ModelAttribute User user, HttpSession session) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setResult(Result.SUCCESS);
        User loginUser = userService.login(user.getUserName(), user.getPassword());
        if (loginUser == null){
            responseResult.setResult(Result.FAILURE);
        }else {
            session.setAttribute("authority",loginUser.isAuthority());
        }
        return responseResult;
    }
}
