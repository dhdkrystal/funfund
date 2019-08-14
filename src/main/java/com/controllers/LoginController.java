package com.controllers;

import com.entity.User;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.service.LoginService;
import com.util.Error;

import javax.servlet.http.HttpSession;

import static com.util.Constant.LOGIN_SUCCESS;

@RestController
@RequestMapping("/api/user")
public class LoginController {
    @Autowired
    LoginService service;

    /**
     * 返回登录界面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
//        System.out.println("aaaaaaaaaaaaaaaa");
        return "/api/login.html";

    }

    /**
     * 登陆接口  login
     *
     * @return message
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject doLogin(@RequestBody String json) {
        Gson gson = new Gson();
        User user = gson.fromJson(json,User.class);
        System.out.println(user.getUserName()+": request to login!"+user.getUserPassword());
        Error error = service.login(user);
        System.out.println(error);
        JSONObject jsonObject = new JSONObject();
        if (error.getCode() == LOGIN_SUCCESS) {
            System.out.println(user.getUserName()+": login successfully!");
            jsonObject.put("success", true);
            return  jsonObject;
        }else {
            System.out.println(user.getUserName()+": Logon failure! Reason: "+error.getMessage());
        }
        jsonObject.put("success", false);
        return jsonObject;
    }

}
