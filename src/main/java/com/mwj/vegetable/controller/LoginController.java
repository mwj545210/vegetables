package com.mwj.vegetable.controller;

import com.mwj.vegetable.entry.User;
import com.mwj.vegetable.service.IUserService;
import com.mwj.vegetable.vo.ResponseResult;
import com.mwj.vegetable.vo.Result;
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

    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String register(){
        return "/user/register";
    }
}
