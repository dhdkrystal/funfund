package com.controllers;

import com.entity.User;
import com.google.gson.Gson;
import com.service.UserService;
import com.util.Error;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;

import static com.util.Constant.ADD_SUCCESS;
import static com.util.Constant.DELETE_SUCCESS;
import static com.util.Constant.UPDATE_SUCCESS;


@RestController
@RequestMapping("/api/admin")
public class UserController {
    @Autowired
    UserService service;

    /*
    *Admin operation: Insert fund manager
     */
    @ResponseBody
    @RequestMapping(value = "/add/manager", method = RequestMethod.POST)
    public Error addManager(@RequestBody String json) {
        Gson gson = new Gson();
        System.out.println("Controller:Request to insert a manager record!");
        User manager = gson.fromJson(json,User.class);
        manager.setRole(0);
        Error error = service.addManager(manager);
        if(error.getCode()==ADD_SUCCESS){
            System.out.println("Controller:Insert manager successfully!");
        }else{
            System.out.println("Controller:Failed to insert manager!");
        }
        return error;
    }

    /*
    *Admin operation: Delete fund manager
     */
    @ResponseBody
    @RequestMapping(value = "/delete/manager", method = RequestMethod.POST)
    public Error deleteManager(@Param("userName") String userName) {
        System.out.println("Controller:Request to delete a manager record!");
        Error error = service.deleteManager(userName);
        if(error.getCode()==DELETE_SUCCESS){
            System.out.println("Controller:Delete manager successfully!");
        }else{
            System.out.println("Controller:Failed to delete manager!");
        }
        return error;
    }


    /*
    *Admin operation: Update fund manager
    */
    @ResponseBody
    @RequestMapping(value = "/update/manager", method = RequestMethod.POST)
    public Error updateManager(@RequestBody String json, @Param("userName") String userName) {
        Gson gson = new Gson();
        System.out.println("Controller:Request to update a manager record!");
        User manager = gson.fromJson(json,User.class);
        manager.setUserName(userName);
        Error error = service.updateManager(manager);
        if(error.getCode()==UPDATE_SUCCESS){
            System.out.println("Controller:Insert manager successfully!");
        }else{
            System.out.println("Controller:Failed to insert manager!");
        }
        return error;
    }

    /*
    *Admin get the manager list
     */
    @ResponseBody
    @RequestMapping(value = "/get/managerlist", method = RequestMethod.GET)
    public List<User> getManagerList(){
        System.out.println("Request to get the manager list!");
        List<User> managerList = service.getAllManager();
        if(managerList == null){
            System.out.println("Failed to get the manager list!");
        }
        System.out.println("Get the manager list successfully!");
        return managerList;
    }


}
