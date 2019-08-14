package com.controllers;

import com.entity.Position;
import com.entity.PositionKey;
import com.google.gson.Gson;
import com.service.PositionService;
import com.util.Error;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.util.Constant.*;


@RestController
@RequestMapping("/api")
public class PositionController {
    @Autowired
    PositionService service;

    /*
    *Manager operation: Insert position
     */
    @ResponseBody
    @RequestMapping(value = "/manager/add/position", method = RequestMethod.POST)
    public JSONObject addPosition(@RequestBody String json) {
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject();
        System.out.println("Controller:Request to insert a position record!");
        Position position = gson.fromJson(json,Position.class);
        Error error = service.addPosition(position);
        if(error.getCode()==ADD_SUCCESS){
            System.out.println("Controller:Insert position successfully!");
            jsonObject.put("success", true);
            return jsonObject;
        }else{
            System.out.println("Controller:Failed to insert position!");
        }
        jsonObject.put("success", false);
        return jsonObject;
    }

    /*
    *Manager operation: Delete position
     */
    @ResponseBody
    @RequestMapping(value = "/manager/delete/position", method = RequestMethod.POST)
    public JSONObject deletePosition(@Param("portfolioName") String portfolioName, @Param("securityName") String securityName) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("Controller:Request to delete a position record!");
        PositionKey positionKey = new PositionKey();
        positionKey.setPortfolioName(portfolioName);
        positionKey.setSecurityName(securityName);
        Error error = service.deletePosition(positionKey);
        if(error.getCode()==DELETE_SUCCESS){
            System.out.println("Controller:Delete position successfully!");
            jsonObject.put("success", true);
            return jsonObject;
        }else{
            System.out.println("Controller:Failed to delete position!");
        }
        jsonObject.put("success", false);
        return jsonObject;
    }


    /*
    *Manager operation: Update close in position.  when reload the price trigger this function
    */
    @ResponseBody
    @RequestMapping(value = "/update/position", method = RequestMethod.GET)
    public JSONObject updatePosition() {
        JSONObject jsonObject = new JSONObject();
        Error error;
        System.out.println("Request to update the position close price!");
        error = service.updatePositionClose();
        if(error.getCode() == ADD_SUCCESS){
            System.out.println("Get the position list successfully!");
            jsonObject.put("success", true);
            return jsonObject;
        }
        System.out.println("Failed to get the position list!");
        jsonObject.put("success", false);
        return jsonObject;
    }

    /*
    *Manager get the position list of a portfolio
     */
    @ResponseBody
    @RequestMapping(value = "/get/positionlist", method = RequestMethod.POST)
    public JSONObject getPositionList(@Param("portfolioName") String portfolioName){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        System.out.println("Request to get the position list of: "+portfolioName);
        List<Position> positionList = service.getPositionList(portfolioName);
        if(positionList == null){
            System.out.println("Failed to get the position list!");
            jsonObject.put("success", false);
            return jsonObject;
        }
        System.out.println("Get the position list successfully!");
        jsonObject.put("success", true);
        jsonObject1.put("positionList", positionList);
        jsonObject.put("data",jsonObject1);
        return jsonObject;
    }





}
