package com.controllers;

import com.entity.Portfolio;
import com.entity.Security;
import com.entity.SecurityKey;
import com.google.gson.Gson;
import com.service.PortfolioService;
import com.service.SecurityService;
import com.util.Error;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.util.Constant.*;


@RestController
@RequestMapping("/api/manager")
public class PortfilioController {
    @Autowired
    PortfolioService service;

    /*
    *Manager operation: Insert portfolio
     */
    @ResponseBody
    @RequestMapping(value = "/add/portfolio", method = RequestMethod.POST)
    public Error addPortfolio(@RequestBody String json) {
        Gson gson = new Gson();
        System.out.println("Controller:Request to insert a portfolio record!");
        Portfolio portfolio = gson.fromJson(json,Portfolio.class);
        Error error = service.addPortfolio(portfolio);
        if(error.getCode()==ADD_SUCCESS){
            System.out.println("Controller:Insert portfolio successfully!");
        }else{
            System.out.println("Controller:Failed to insert portfolio!");
        }
        return error;
    }

    /*
    *Manager operation: Delete portfolio
     */
    @ResponseBody
    @RequestMapping(value = "/delete/portfolio", method = RequestMethod.POST)
    public Error deletePortfolio(@Param("portfolioName") String portfolioName) {
        System.out.println("Controller:Request to delete a portfolio record!");
        Error error = service.deletePortfolio(portfolioName);
        if(error.getCode()==DELETE_SUCCESS){
            System.out.println("Controller:Delete portfolio successfully!");
        }else{
            System.out.println("Controller:Failed to delete portfolio!");
        }
        return error;
    }


    /*
    *Manager operation: Update portfolio
    */
    @ResponseBody
    @RequestMapping(value = "/update/portfolio", method = RequestMethod.POST)
    public Error updatePortfolio(@RequestBody String json, @Param("portfolioName") String portfolioName) {
        Gson gson = new Gson();
        System.out.println("Controller:Request to update a portfolio record!");
        Portfolio portfolio = gson.fromJson(json,Portfolio.class);
        portfolio.setPortfolioName(portfolioName);
        Error error = service.updatePortfolio(portfolio);
        if(error.getCode()==UPDATE_SUCCESS){
            System.out.println("Controller:Insert portfolio successfully!");
        }else{
            System.out.println("Controller:Failed to insert portfolio!");
        }
        return error;
    }

    /*
    *Manager get his own portfolio list
     */
    @ResponseBody
    @RequestMapping(value = "/get/portfoliolist", method = RequestMethod.POST)
    public List<Portfolio> getPortfolioList(@Param("manager") String manager){
        System.out.println("Request to get the portfolio list of manager: "+manager);
        List<Portfolio> portfolioList = service.getPortfolioList(manager);
        if(portfolioList == null){
            System.out.println("Failed to get the portfolio list!");
        }
        System.out.println("Get the portfolio list successfully!");
        return portfolioList;
    }


}
