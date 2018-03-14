package com.mwj.vegetable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/logistics")
public class LogisticsController {

//    @Resource
//    private IVegetableService logisticService;
//
//    @RequestMapping(value = "addLogistics",method = RequestMethod.GET)
//    public String listLogistics(){
//        return "/vegetable/addLogistics";
//    }
//
//    @RequestMapping(value = "saveLogistics",method = RequestMethod.POST)
//    public String addLogistics(@ModelAttribute Logistic logistic){
//        try {
//            logisticService.addLogistics(logistic);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:../vegetable/listLogistics.do";
//    }
//
//    @RequestMapping(value = "editLogistics",method = RequestMethod.GET)
//    public String editLogistics(@RequestParam int id,ModelMap modelMap){
//        Logistic logistic = logisticService.findLogisticById(id);
//        modelMap.addAttribute("logistic",logistic);
//        return "/vegetable/editLogistics";
//    }
//
//    @RequestMapping(value = "queryLogistics",method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseResult queryLogistics(@RequestParam("code") String code){
//        List<Logistic> allByCode = logisticService.findAllByCode(code);
//        ResponseResult responseResult = new ResponseResult();
//        responseResult.setData(allByCode);
//        return responseResult;
//    }
//
//    @RequestMapping(value = "listLogistics",method = RequestMethod.GET)
//    public String viewLogistics(ModelMap modelMap){
//        List<Logistic> logistics = logisticService.findAllLogistics();
//        modelMap.addAttribute("logistics",logistics);
//        return "vegetable/listLogistics";
//    }
//
//    @RequestMapping(value = "delLogistics",method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseResult delLogistics(@RequestParam String ids){
//        List<Logistic> logistics = Arrays.stream(ids.split(",")).map(id -> new Logistic(Integer.parseInt(id))).collect(Collectors.toList());
//        ResponseResult responseResult = new ResponseResult();
//        try {
//            logisticService.delLogisticsByIds(logistics);
//            responseResult.setResult(Result.SUCCESS);
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseResult.setResult(Result.FAILURE);
//        }
//        return responseResult;
//    }
}
