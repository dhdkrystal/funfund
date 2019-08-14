package com.controllers;


import com.entity.Operation;
import com.google.gson.Gson;
import com.service.OperationService;
import com.util.Error;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.util.Constant.ADD_SUCCESS;

@RestController
@RequestMapping("/api")
public class OperationController {
    @Autowired
    OperationService service;

     /*
    *Manager: Insert operation
     */
    @ResponseBody
    @RequestMapping(value = "/manager/add/operation", method = RequestMethod.POST)
    public JSONObject addOperation(@RequestBody String json){
        Error error;
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject();
        Operation operation = gson.fromJson(json, Operation.class);
        System.out.println("Controller:Request to insert a operation record!");
        error = service.addOperation(operation);
        if(error.getCode() == ADD_SUCCESS){
            jsonObject.put("success", true);
            return jsonObject;
        }
        jsonObject.put("success", false);
        return jsonObject;
    }

}
