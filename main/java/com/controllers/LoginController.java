package com.controllers;

import com.entity.User;
import org.apache.ibatis.annotations.Param;
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
     * 登陆接口
     *
     * @param userName 用户名
     * @param userPwd 密码
     * @param role 权限
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Error doLogin(HttpSession session, @Param("userName") String userName, @Param("userPwd") String userPwd, @Param("role") int role) {
        User userInfo = new User();
        userInfo.setUserName(userName);
        userInfo.setUserPassword(userPwd);
        userInfo.setRole(role);
        System.out.println(userName+": request to login!");
        Error error = service.login(userInfo);
        System.out.println(error);
        if (error.getCode() == LOGIN_SUCCESS) {
            System.out.println(userName+": login successfully!");
            session.setAttribute("username", userName);
        }else {
            System.out.println(userName+": Logon failure! Reason: "+error.getMessage());
        }
        return error;
    }


    /**
     * 管理员登陆接口
     *
     * @param adminName 用户名
     * @param adminPwd 密码
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/views/loginAdmin", method = RequestMethod.POST)
//    public Error doLoginAdmin(HttpSession session, @Param("adminName") String adminName, @Param("adminPwd") String adminPwd) {
//        Admin adminInfo = new Admin();
//        adminInfo.setAdminName(adminName);
//        adminInfo.setAdminPwd(adminPwd);
//        System.out.println("管理员-{}请求登陆"+adminName);
//        Error error = service.loginAdmin(adminInfo);
//        System.out.println(error);
//        if (error.getCode() == LOGIN_SUCCESS) {
//            System.out.println("管理员-{}登陆成功"+ adminName);
//            session.setAttribute("adminName", adminName);
//        }else {
//            System.out.println("管理员-{}登陆失败，{}"+ adminName+  error.getMessage());
//        }
//        return error;
//    }

    /**
     * 备用登陆接口
     *
     * @param userName 用户名
     * @param userPwd 密码
     * @return
     */
//    @RequestMapping(value = "login1", method = RequestMethod.POST)
//    public String doLogin1(HttpSession session, Model model, @Param("userName") String userName, @Param("userPwd") String userPwd) {
//        Token token = new Token();
//        User userInfo = new User();
//        userInfo.setUserName(userName);
//        userInfo.setUserPwd(userPwd);
//        userInfo.setToken(token.getToken());
//        System.out.println("用户-{}请求登陆1"+ userName);
//        Error error = service.login(userInfo);
//        if (error.getCode() == LOGIN_SUCCESS) {
//            System.out.println("用户-{}登陆成功1"+ userName);
//            session.setAttribute("token", token.getToken());
//            session.setAttribute("username", userName);
//            return "redirect:index";
//        } else {
//            System.out.println("用户-{}登陆失败1，{}"+ userName+  error.getMessage());
//            model.addAttribute("error", error.getMessage());
//            return "login.html";
//        }
//    }

    /**
     * 跳转主页
     *
     * @return 跳转主页
     */
//    @RequestMapping(value = "index")
//    public String index() {
//        return "index.html";
//    }


    /**
     * 用户退出 清除session
     *
     * @param session
     * @return
     */
//    @RequestMapping(value = "logout")
//    public String logout(HttpSession session) {
//        System.out.println("用户-{}登陆失败，{}"+ session.getAttribute("userName"));
//        session.invalidate();
//        return "redirect:login";
//    }


    /**
     * 注册的接口
     *
     * @param json
     * @return
     */
//    @RequestMapping(value = "/views/register", method = RequestMethod.POST)
//    @ResponseBody
//    public Error doRegister(@RequestBody String json) {
//        System.out.println(json);
//        Gson gson = new Gson();
//        User userInfo = gson.fromJson(json, User.class);
//        System.out.println("用户-{}请求注册"+ userInfo.getUserName());
//        Error error = userService.insertUser(userInfo);
//        if (error.getCode() == OPERATION_SUCCESS) {
//            System.out.println("用户-{}注册成功"+ userInfo.getUserName());
//        } else {
//            System.out.println("用户-{}注册失败,{}"+ userInfo.getUserName()+ error.getMessage());
//        }
//        return error;
//    }

    /**
     * 返回注册的页面
     *
     * @return
     */

//    @RequestMapping(value = "/views/register", method = RequestMethod.GET)
//    public String register() {
//        return "/views/register.jsp";
//    }

}
