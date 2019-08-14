package com.controllers;

import com.entity.User;
import com.google.gson.Gson;
import com.service.UserService;
import com.util.Error;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    public JSONObject addManager(@RequestBody String json) {
        JSONObject jsonObject = new JSONObject();
        Gson gson = new Gson();
        System.out.println("Controller:Request to insert a manager record!");
        User manager = gson.fromJson(json,User.class);
        manager.setRole(0);
        Error error = service.addManager(manager);
        if(error.getCode()==ADD_SUCCESS){
            System.out.println("Controller:Insert manager successfully!");
            jsonObject.put("success", true);
            return jsonObject;
        }else{
            System.out.println("Controller:Failed to insert manager!");
        }
        jsonObject.put("success", false);
        return jsonObject;
    }

    /*
    *Admin operation: Delete fund manager
     */
    @ResponseBody
    @RequestMapping(value = "/delete/manager", method = RequestMethod.POST)
    public JSONObject deleteManager(@RequestBody String json) {
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject();
        User user = gson.fromJson(json, User.class);
        String userName = user.getUserName();
        System.out.println("Controller:Request to delete a manager record!");
        Error error = service.deleteManager(userName);
        if(error.getCode()==DELETE_SUCCESS){
            System.out.println("Controller:Delete manager successfully!");
            jsonObject.put("success", true);
            return jsonObject;
        }else{
            System.out.println("Controller:Failed to delete manager!");
        }
        jsonObject.put("success", false);
        return jsonObject;
    }


    /*
    *Admin operation: Update fund manager
    */
    @ResponseBody
    @RequestMapping(value = "/update/manager", method = RequestMethod.POST)
    public JSONObject updateManager(@RequestBody String json) {
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject();
        System.out.println("Controller:Request to update a manager record!");
        User manager = gson.fromJson(json,User.class);
        Error error = service.updateManager(manager);
        if(error.getCode()==UPDATE_SUCCESS){
            System.out.println("Controller:Update manager successfully!");
            jsonObject.put("success", true);
            return jsonObject;
        }else{
            System.out.println("Controller:Failed to update manager!");
        }
        jsonObject.put("success", false);
        return jsonObject;
    }

    /*
    *Admin get the manager list
     */
    @ResponseBody
    @RequestMapping(value = "/get/managerlist", method = RequestMethod.GET)
    public JSONObject getManagerList(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        System.out.println("Request to get the manager list!");
        List<User> managerList = service.getAllManager();
        if(managerList == null){
            System.out.println("Failed to get the manager list!");
            jsonObject.put("success", false);
            return jsonObject;
        }
        System.out.println("Get the manager list successfully!");
        jsonObject.put("success", true);
        jsonObject1.put("managerList", managerList);
        jsonObject.put("data",jsonObject1);
        return jsonObject;
    }


}
