package com.controllers;

import com.entity.Security;
import com.service.DataMaintenanceService;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class DataMaintenanceController {
    @Autowired
    DataMaintenanceService service;

    /*
    *Operation: volume filter
     */
    @ResponseBody
    @RequestMapping(value = "/volume/filter", method = RequestMethod.POST)
    public JSONObject volumeFilter(@Param("type") String type, @Param("date") String date) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        System.out.println("Controller:Request to filter volume!");
        List<Security> volumeFilterList = service.volumeFilter(type, date);
        if(volumeFilterList == null){
            System.out.println("Controller:Failed to do volume filter!");
            jsonObject.put("success",false);
            return jsonObject;
        }else{
            System.out.println("Controller:Do volume filter successfully!");
        }
        jsonObject.put("success", true);
        jsonObject1.put("volumeFilterList", volumeFilterList);
        jsonObject.put("data",jsonObject1);
        return jsonObject;
    }


    /*
    *Manager operation: Update portfolio
    */
//    @ResponseBody
//    @RequestMapping(value = "/update/portfolio", method = RequestMethod.POST)
//    public Error updatePortfolio(@RequestBody String json, @Param("portfolioName") String portfolioName) {
//        Gson gson = new Gson();
//        System.out.println("Controller:Request to update a portfolio record!");
//        Portfolio portfolio = gson.fromJson(json,Portfolio.class);
//        portfolio.setPortfolioName(portfolioName);
//        Error error = service.updatePortfolio(portfolio);
//        if(error.getCode()==UPDATE_SUCCESS){
//            System.out.println("Controller:Insert portfolio successfully!");
//        }else{
//            System.out.println("Controller:Failed to insert portfolio!");
//        }
//        return error;
//    }

    /*
    *Manager get his own portfolio list
     */
//    @ResponseBody
//    @RequestMapping(value = "/get/portfoliolist", method = RequestMethod.POST)
//    public List<Portfolio> getPortfolioList(@Param("manager") String manager){
//        System.out.println("Request to get the portfolio list of manager: "+manager);
//        List<Portfolio> portfolioList = service.getPortfolioList(manager);
//        if(portfolioList == null){
//            System.out.println("Failed to get the portfolio list!");
//        }
//        System.out.println("Get the portfolio list successfully!");
//        return portfolioList;
//    }


}
