package com.controllers;

import com.entity.Position;
import com.entity.PositionKey;
import com.entity.Security;
import com.entity.SecurityKey;
import com.google.gson.Gson;
import com.service.PositionService;
import com.service.SecurityService;
import com.util.Error;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.util.Constant.*;


@RestController
@RequestMapping("/api/manager")
public class PositionController {
    @Autowired
    PositionService service;

    /*
    *Manager operation: Insert position
     */
    @ResponseBody
    @RequestMapping(value = "/add/position", method = RequestMethod.POST)
    public Error addPosition(@RequestBody String json) {
        Gson gson = new Gson();
        System.out.println("Controller:Request to insert a position record!");
        Position position = gson.fromJson(json,Position.class);
        Error error = service.addPosition(position);
        if(error.getCode()==ADD_SUCCESS){
            System.out.println("Controller:Insert position successfully!");
        }else{
            System.out.println("Controller:Failed to insert position!");
        }
        return error;
    }

    /*
    *Manager operation: Delete position
     */
    @ResponseBody
    @RequestMapping(value = "/delete/position", method = RequestMethod.POST)
    public Error deletePosition(@Param("portfolioName") String portfolioName, @Param("securityName") String securityName) {
        System.out.println("Controller:Request to delete a position record!");
        PositionKey positionKey = new PositionKey();
        positionKey.setPortfolioName(portfolioName);
        positionKey.setSecurityName(securityName);
        Error error = service.deletePosition(positionKey);
        if(error.getCode()==DELETE_SUCCESS){
            System.out.println("Controller:Delete position successfully!");
        }else{
            System.out.println("Controller:Failed to delete position!");
        }
        return error;
    }


    /*
    *Manager operation: Update position
    */
//    @ResponseBody
//    @RequestMapping(value = "/update/position", method = RequestMethod.POST)
//    public Error updatePosition(@RequestBody String json, @Param("portfolioName") String portfolioName, @Param("securityName") String securityName) {

//    }

    /*
    *Manager get the position list of a portfolio
     */
    @ResponseBody
    @RequestMapping(value = "/get/positionlist", method = RequestMethod.POST)
    public List<Position> getPositionList(@Param("portfolioName") String portfolioName){
        System.out.println("Request to get the position list of: "+portfolioName);
        List<Position> positionList = service.getPositionList(portfolioName);
        if(positionList == null){
            System.out.println("Failed to get the position list!");
        }
        System.out.println("Get the position list successfully!");
        return positionList;
    }


}
