package com.mwj.logistics.controller;

import com.mwj.logistics.entry.Logistic;
import com.mwj.logistics.service.ILogisticService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/logistics")
public class LogisticsController {

    @Resource
    private ILogisticService logisticService;

    @RequestMapping(value = "addLogistics",method = RequestMethod.GET)
    public String listLogistics(){
        return "/logistics/addLogistics";
    }

    @RequestMapping(value = "saveLogistics",method = RequestMethod.POST)
    public String addLogistics(@ModelAttribute Logistic logistic){
        try {
            logisticService.addLogistics(logistic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:../logistics/viewLogistics.do";
    }

    @RequestMapping(value = "listLogistics",method = RequestMethod.GET)
    public String viewLogistics(){
        return "/logistics/listLogistics";
    }
}
