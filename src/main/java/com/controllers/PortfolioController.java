package com.controllers;

import com.entity.Portfolio;
import com.google.gson.Gson;
import com.service.PortfolioService;
import com.util.Error;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.util.Constant.*;


@RestController
@RequestMapping("/api")
public class PortfolioController {
    @Autowired
    PortfolioService service;

    /*
    *Manager operation: Insert portfolio
     */
    @ResponseBody
    @RequestMapping(value = "/manager/add/portfolio", method = RequestMethod.POST)
    public JSONObject addPortfolio(@RequestBody String json) {
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject();
        System.out.println("Controller:Request to insert a portfolio record!");
        Portfolio portfolio = gson.fromJson(json,Portfolio.class);
        Error error = service.addPortfolio(portfolio);
        if(error.getCode()==ADD_SUCCESS){
            System.out.println("Controller:Insert portfolio successfully!");
            jsonObject.put("success", true);
            return jsonObject;
        }else{
            System.out.println("Controller:Failed to insert portfolio!");
        }
        jsonObject.put("success", false);
        return jsonObject;
    }

    /*
    *Manager operation: Delete portfolio
     */
    @ResponseBody
    @RequestMapping(value = "/manager/delete/portfolio", method = RequestMethod.POST)
    public JSONObject deletePortfolio(@Param("portfolioName") String portfolioName) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("Controller:Request to delete a portfolio record!");
        Error error = service.deletePortfolio(portfolioName);
        if(error.getCode()==DELETE_SUCCESS){
            System.out.println("Controller:Delete portfolio successfully!");
            jsonObject.put("success", true);
            return jsonObject;
        }else{
            System.out.println("Controller:Failed to delete portfolio!");
        }
        jsonObject.put("success", false);
        return jsonObject;
    }


    /*
    *Manager operation: Update portfolio
    */
    @ResponseBody
    @RequestMapping(value = "/manager/update/portfolio", method = RequestMethod.POST)
    public JSONObject updatePortfolio(@RequestBody String json, @Param("portfolioName") String portfolioName) {
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject();
        System.out.println("Controller:Request to update a portfolio record!");
        Portfolio portfolio = gson.fromJson(json,Portfolio.class);
        portfolio.setPortfolioName(portfolioName);
        Error error = service.updatePortfolio(portfolio);
        if(error.getCode()==UPDATE_SUCCESS){
            System.out.println("Controller:Update portfolio successfully!");
            jsonObject.put("success", true);
            return jsonObject;
        }else{
            System.out.println("Controller:Failed to update portfolio!");
        }
        jsonObject.put("success", false);
        return jsonObject;
    }

    /*
    *Manager get his own portfolio list
     */
    @ResponseBody
    @RequestMapping(value = "/manager/get/portfoliolist", method = RequestMethod.POST)
    public JSONObject getPortfolioList(@Param("manager") String manager){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        System.out.println("Request to get the portfolio list of manager: "+manager);
        List<Portfolio> portfolioList = service.getPortfolioList(manager);
        if(portfolioList == null){
            System.out.println("Failed to get the portfolio list!");
            jsonObject.put("success", false);
            return jsonObject;
        }
        System.out.println("Get the portfolio list successfully!");
        jsonObject.put("success", true);
        jsonObject1.put("portfolioList", portfolioList);
        jsonObject.put("data",jsonObject1);
        return jsonObject;
    }



    /*
     *Manager get his best 2 portfolio list
     *
     */
    @ResponseBody
    @RequestMapping(value = "/manager/portfolio/top",method = RequestMethod.POST)
    public JSONObject getTopPortfolioList(@Param("manager") String manager){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        System.out.println("Request to get the top portfolio list of manager: "+manager);
        List<Portfolio> portfolioList = service.getTopPortfolioList(manager);
        if(portfolioList == null){
            System.out.println("Failed to get the top portfolio list!");
            jsonObject.put("success", false);
            return jsonObject;
        }
        System.out.println("Get the top portfolio list successfully!");
        jsonObject.put("success", true);
        jsonObject1.put("portfolioList", portfolioList);
        jsonObject.put("data",jsonObject1);
        return jsonObject;
    }

    /*
     *Manager get his worst 2 portfolio list
     *
     */
    @ResponseBody
    @RequestMapping(value = "/manager/portfolio/worst",method = RequestMethod.POST)
    public JSONObject getWorstPortfolioList(@Param("manager") String manager){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        System.out.println("Request to get the worst portfolio list of manager: "+manager);
        List<Portfolio> portfolioList = service.getWorstPortfolioList(manager);
        if(portfolioList == null){
            System.out.println("Failed to get the worst portfolio list!");
            jsonObject.put("success", false);
            return jsonObject;
        }
        System.out.println("Get the worst portfolio list successfully!");
        jsonObject.put("success", true);
        jsonObject1.put("portfolioList", portfolioList);
        jsonObject.put("data",jsonObject1);
        return jsonObject;
    }


    /*
     *Admin get the best 2 portfolio and the their manager
     *
     */
    @ResponseBody
    @RequestMapping(value = "/admin/portfolio/top",method = RequestMethod.POST)
    public JSONObject getTopPortfolioManagerList(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        System.out.println("Request to get the top portfolio and manager list.");
        List<Portfolio> portfolioList = service.getTopPortfolioManagerList();
        if(portfolioList == null){
            System.out.println("Failed to get the top portfolio and manager list!");
            jsonObject.put("success", false);
            return jsonObject;
        }
        System.out.println("Get the top portfolio and manager list successfully!");
        jsonObject.put("success", true);
        jsonObject1.put("portfolioList", portfolioList);
        jsonObject.put("data",jsonObject1);
        return jsonObject;
    }


    /*
     *Admin get the worst 2 portfolio and the their manager
     *
     */
    @ResponseBody
    @RequestMapping(value = "/admin/portfolio/worst",method = RequestMethod.POST)
    public JSONObject getWorstPortfolioManagerList(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        System.out.println("Request to get the worst portfolio and manager list.");
        List<Portfolio> portfolioList = service.getWorstPortfolioManagerList();
        if(portfolioList == null){
            System.out.println("Failed to get the worst portfolio and manager list!");
            jsonObject.put("success", false);
            return jsonObject;
        }
        System.out.println("Get the worst portfolio and manager list successfully!");
        jsonObject.put("success", true);
        jsonObject1.put("portfolioList", portfolioList);
        jsonObject.put("data",jsonObject1);
        return jsonObject;
    }



}
