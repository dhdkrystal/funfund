package com.controllers;

import com.entity.Security;
import com.entity.SecurityKey;
import com.entity.User;
import com.google.gson.Gson;
import com.service.SecurityService;
import com.service.UserService;
import com.util.Error;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.util.Constant.*;


@RestController
@RequestMapping("/api/admin")
public class SecurityController {
    @Autowired
    SecurityService service;

    /*
    *Admin operation: Insert security
     */
    @ResponseBody
    @RequestMapping(value = "/add/security", method = RequestMethod.POST)
    public Error addSecurity(@RequestBody String json) {
        Gson gson = new Gson();
        System.out.println("Controller:Request to insert a security record!");
        Security security = gson.fromJson(json,Security.class);
        Error error = service.addSecurity(security);
        if(error.getCode()==ADD_SUCCESS){
            System.out.println("Controller:Insert security successfully!");
        }else{
            System.out.println("Controller:Failed to insert security!");
        }
        return error;
    }

    /*
    *Admin operation: Delete security
     */
    @ResponseBody
    @RequestMapping(value = "/delete/security", method = RequestMethod.POST)
    public Error deleteSecurity(@Param("securityName") String securityName, @Param("date") String date) {
        System.out.println("Controller:Request to delete a security record!");
        SecurityKey securityKey = new SecurityKey();
        securityKey.setSecurityName(securityName);
        securityKey.setDate(date);
        Error error = service.deleteSecurity(securityKey);
        if(error.getCode()==DELETE_SUCCESS){
            System.out.println("Controller:Delete security successfully!");
        }else{
            System.out.println("Controller:Failed to delete security!");
        }
        return error;
    }


    /*
    *Admin operation: Update security
    */
    @ResponseBody
    @RequestMapping(value = "/update/security", method = RequestMethod.POST)
    public Error updateSecurity(@RequestBody String json, @Param("securityName") String securityName, @Param("date") String date) {
        Gson gson = new Gson();
        System.out.println("Controller:Request to update a security record!");
        Security security = gson.fromJson(json,Security.class);
        security.setSecurityName(securityName);
        security.setDate(date);
        Error error = service.updateSecurity(security);
        if(error.getCode()==UPDATE_SUCCESS){
            System.out.println("Controller:Insert security successfully!");
        }else{
            System.out.println("Controller:Failed to insert security!");
        }
        return error;
    }

    /*
    *Admin get the security list of stock, bond, futures, etfs
     */
    @ResponseBody
    @RequestMapping(value = "/get/securitylist", method = RequestMethod.GET)
    public List<Security> getSecurityList(@Param("type") String type, @Param("date") String date){
        System.out.println("Request to get today security list of: "+type);
        List<Security> securityList = service.getSecurityList(type,date);
        if(securityList == null){
            System.out.println("Failed to get the security list!");
        }
        System.out.println("Get the security list successfully!");
        return securityList;
    }


    /*
    *Admin get the history information of a security
     */
    @ResponseBody
    @RequestMapping(value = "/get/securityhistory", method = RequestMethod.GET)
    public List<Security> getSecurityHistory(@Param("securityName") String securityName){
        System.out.println("Request to get a security's history list of: "+securityName);
        List<Security> securityList = service.getSecurityHistory(securityName);
        if(securityList == null){
            System.out.println("Failed to get the security history!");
        }
        System.out.println("Get the security history successfully!");
        return securityList;
    }


}
