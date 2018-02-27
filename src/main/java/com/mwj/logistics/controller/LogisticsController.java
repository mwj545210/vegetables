package com.mwj.logistics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/logistics")
public class LogisticsController {


    @RequestMapping(value = "listLogistics",method = RequestMethod.GET)
    public String listLogistics(){
        return "/logistics/listLogistics";
    }
}
