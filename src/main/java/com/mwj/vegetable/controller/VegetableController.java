package com.mwj.vegetable.controller;

import com.mwj.vegetable.entry.Vegetable;
import com.mwj.vegetable.entry.meta.DealType;
import com.mwj.vegetable.service.IVegetableService;
import com.mwj.vegetable.vo.ResponseResult;
import com.mwj.vegetable.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/vegetables")
public class VegetableController {

    @Resource
    private IVegetableService vegetableService;
    
    @RequestMapping(value = "addVegetables",method = RequestMethod.GET)
    public String listVegetables(){
        return "/vegetables/addVegetables";
    }

    @RequestMapping(value = "saveVegetables",method = RequestMethod.POST)
    public String addVegetables(@ModelAttribute Vegetable vegetable){
        try {
            vegetableService.addVegetables(vegetable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:../vegetables/listVegetables.do";
    }

    @RequestMapping(value = "editVegetables",method = RequestMethod.GET)
    public String editVegetables(@RequestParam int id, ModelMap modelMap){
        Vegetable vegetable = vegetableService.findVegetableById(id);
        modelMap.addAttribute("vegetable",vegetable);
        modelMap.addAttribute("dealTypes", DealType.values());
        return "/vegetables/editVegetables";
    }

//    @RequestMapping(value = "queryVegetables",method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseResult queryVegetables(@RequestParam("code") String code){
//        List<Vegetable> allByCode = vegetableService.findAllByCode(code);
//        ResponseResult responseResult = new ResponseResult();
//        responseResult.setData(allByCode);
//        return responseResult;
//    }

    @RequestMapping(value = "listVegetables",method = RequestMethod.GET)
    public String viewVegetables(ModelMap modelMap){
        List<Vegetable> vegetables = vegetableService.findAllVegetables();
        modelMap.addAttribute("vegetables",vegetables);
        return "/vegetables/listVegetables";
    }

    @RequestMapping(value = "delVegetables",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult delVegetables(@RequestParam int vegetableId){
        ResponseResult responseResult = new ResponseResult();
        try {
            vegetableService.delVegetablesById(vegetableId);
            responseResult.setResult(Result.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setResult(Result.FAILURE);
        }
        return responseResult;
    }
}
