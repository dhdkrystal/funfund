package com.controllers;

import com.entity.Portfolio;
import com.entity.Security;
import com.google.gson.Gson;
import com.service.DataMaintenanceService;
import com.service.PortfolioService;
import com.service.SecurityService;
import com.util.Error;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.util.Constant.*;


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
    public List<Security> volumeFilter(@Param("type") String type, @Param("date") String date) {
        System.out.println("Controller:Request to filter volume!");
        List<Security> securityList = service.volumeFilter(type, date);
        if(securityList == null){
            System.out.println("Controller:Failed to do volume filter!");
        }else{
            System.out.println("Controller:Do volume filter successfully!");
        }
        return securityList;
    }

    /*
    *Manager operation: Delete portfolio
     */
//    @ResponseBody
//    @RequestMapping(value = "/delete/portfolio", method = RequestMethod.POST)
//    public Error deletePortfolio(@Param("portfolioName") String portfolioName) {
//        System.out.println("Controller:Request to delete a portfolio record!");
//        Error error = service.deletePortfolio(portfolioName);
//        if(error.getCode()==DELETE_SUCCESS){
//            System.out.println("Controller:Delete portfolio successfully!");
//        }else{
//            System.out.println("Controller:Failed to delete portfolio!");
//        }
//        return error;
//    }


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
