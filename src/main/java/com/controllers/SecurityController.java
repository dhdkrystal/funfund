package com.controllers;

import com.entity.Portfolio;
import com.entity.Security;
import com.entity.SecurityKey;
import com.google.gson.Gson;
import com.service.PortfolioService;
import com.service.PositionService;
import com.service.SecurityService;
import com.util.Error;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.util.Constant.*;


@RestController
@RequestMapping("/api")
public class SecurityController {
    @Autowired
    SecurityService service;
    @Autowired
    PortfolioService portfolioService;
    @Autowired
    PositionService positionService;

    /*
    *Admin operation: Insert security
     */
    @ResponseBody
    @RequestMapping(value = "/admin/add/security", method = RequestMethod.POST)
    public JSONObject addSecurity(@RequestBody String json) {
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject();
        System.out.println("Controller:Request to insert a security record!");
        Security security = gson.fromJson(json,Security.class);
        Error error = service.addSecurity(security);
        if(error.getCode()==ADD_SUCCESS){
            System.out.println("Controller:Insert security successfully!");
            jsonObject.put("success", true);
            return jsonObject;
        }else{
            System.out.println("Controller:Failed to insert security!");
        }
        jsonObject.put("success", false);
        return jsonObject;
    }

    /*
    *Admin operation: Delete security
     */
    @ResponseBody
    @RequestMapping(value = "/admin/delete/security", method = RequestMethod.POST)
    public JSONObject deleteSecurity(@Param("securityName") String securityName, @Param("date") String date) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("Controller:Request to delete a security record!");
        SecurityKey securityKey = new SecurityKey();
        securityKey.setSecurityName(securityName);
        securityKey.setDate(date);
        Error error = service.deleteSecurity(securityKey);
        if(error.getCode()==DELETE_SUCCESS){
            System.out.println("Controller:Delete security successfully!");
            jsonObject.put("success", true);
            return jsonObject;
        }else{
            System.out.println("Controller:Failed to delete security!");
        }
        jsonObject.put("success", false);
        return jsonObject;
    }

    /*
    *Admin operation: Delete security
     */
    @ResponseBody
    @RequestMapping(value = "/admin/delete/securities", method = RequestMethod.POST)
    public JSONObject deleteSecurityByName(@Param("securityName") String securityName) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("Controller:Request to delete securities!");
        Error error = service.deleteSecurityByName(securityName);
        if(error.getCode()==DELETE_SUCCESS){
            System.out.println("Controller:Delete securities successfully!");
            jsonObject.put("success", true);
            return jsonObject;
        }else{
            System.out.println("Controller:Failed to delete securities!");
        }
        jsonObject.put("success", false);
        return jsonObject;
    }


    /*
    *Admin operation: Update security
    */
    @ResponseBody
    @RequestMapping(value = "/admin/update/security", method = RequestMethod.POST)
    public JSONObject updateSecurity(@RequestBody String json, @Param("securityName") String securityName, @Param("date") String date) {
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject();
        System.out.println("Controller:Request to update a security record!");
        Security security = gson.fromJson(json,Security.class);
        security.setSecurityName(securityName);
        security.setDate(date);
        Error error = service.updateSecurity(security);
        if(error.getCode()==UPDATE_SUCCESS){
            System.out.println("Controller:Update security successfully!");
            jsonObject.put("success", true);
            return jsonObject;
        }else{
            System.out.println("Controller:Failed to update security!");
        }
        jsonObject.put("success", false);
        return jsonObject;
    }

    /*
    *Admin and Manager get the security list of stock, bond, futures, etfs
     */
    @ResponseBody
    @RequestMapping(value = "/get/securitylist", method = RequestMethod.GET)
    public JSONObject getSecurityList(@Param("type") String type, @Param("date") String date){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        System.out.println("Request to get today security list of: "+type);
        List<Security> securityList = service.getSecurityList(type,date);
        if(securityList == null){
            System.out.println("Failed to get the security list!");
            jsonObject.put("success", false);
            return jsonObject;
        }
        System.out.println("Get the security list successfully!");
        jsonObject.put("success", true);
        jsonObject1.put("securityList", securityList);
        jsonObject.put("data",jsonObject1);
        return jsonObject;
    }

    /*
    *Admin and Manager get the information of a security
     */
    @ResponseBody
    @RequestMapping(value = "/get/security", method = RequestMethod.GET)
    public JSONObject getSecurity(@Param("securityName") String securityName, @Param("date") String date){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        System.out.println("Request to get security information of: "+securityName);
        Security security = service.getSecurity(securityName,date);
        if(security == null){
            System.out.println("Failed to get the security!");
            jsonObject.put("success", false);
            return jsonObject;
        }
        System.out.println("Get the security successfully!");
        jsonObject.put("success", true);
        jsonObject1.put("security", security);
        jsonObject.put("data",jsonObject1);
        return jsonObject;
    }


    /*
    *Admin and Manager get the history information of a security
     */
    @ResponseBody
    @RequestMapping(value = "/get/securityhistory", method = RequestMethod.GET)
    public JSONObject getSecurityHistory(@Param("securityName") String securityName){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        System.out.println("Request to get a security's history list of: "+securityName);
        List<Security> securityHistory = service.getSecurityHistory(securityName);
        if(securityHistory == null){
            System.out.println("Failed to get the security history!");
            jsonObject.put("success", false);
            return jsonObject;
        }
        System.out.println("Get the security history successfully!");
        jsonObject.put("success", true);
        jsonObject1.put("securityHistory", securityHistory);
        jsonObject.put("data",jsonObject1);
        return jsonObject;
    }


    /*
    *Admin get all portfolios of all managers
     */
    @ResponseBody
    @RequestMapping(value = "/admin/get/portfoliolist", method = RequestMethod.GET)
    public JSONObject getAllPortfolioList(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        System.out.println("Request to get the portfolio list of all managers!");
        List<Portfolio> allPortfolioList = portfolioService.getAllPortfolioList();
        if(allPortfolioList == null){
            System.out.println("Failed to get the portfolio list!");
            jsonObject.put("success", false);
            return jsonObject;
        }
        System.out.println("Get the portfolio list successfully!");
        jsonObject.put("success", true);
        jsonObject1.put("allPortfolioList", allPortfolioList);
        jsonObject.put("data",jsonObject1);
        return jsonObject;
    }



    /*
    *Admin get all portfolios of all managers
     */
    @ResponseBody
    @RequestMapping(value = "/get/securitynamelist", method = RequestMethod.GET)
    public JSONObject getSecurityNameList(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        System.out.println("Request to get a list of security names!");
        List<String> securityNameList = service.getSecurityNameList();
        if(securityNameList == null){
            System.out.println("Failed to get the security name list!");
            jsonObject.put("success", false);
            return jsonObject;
        }
        System.out.println("Get the security name list successfully!");
        jsonObject.put("success", true);
        jsonObject1.put("securityNameList", securityNameList);
        jsonObject.put("data",jsonObject1);
        return jsonObject;
    }






    /*
     *Admin upload security
     *
     *
     * csv附件上传 MultipartHttpServletRequest MultipartFile
     * **/
    @RequestMapping("/security/uploadsecurity")
    @ResponseBody
    public JSONObject uploadSecurity(MultipartHttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        List<MultipartFile> files = request.getFiles("file");
        if (files != null && files.size() > 0) {
            MultipartFile file = null;
            for (int i = 0; i < files.size(); i++) {
                file = files.get(i);
                if (!file.isEmpty()) {
                    InputStreamReader isr = null;
                    BufferedReader br = null;
                    try {
                        isr = new InputStreamReader(file.getInputStream());
                        br = new BufferedReader(isr);
                        String line = null;
                        List<List<String>> strs = new ArrayList<>();
                        while ((line = br.readLine()) != null){
                            strs.add(Arrays.asList(line.split(",")));
                        }
                        Boolean result = service.uploadSecurity(strs);

                        //update close price in position
                        if(result == true){
                            positionService.updatePositionClose();
                        }

                        jsonObject.put("success", result);
                    }catch (Exception e){
                        jsonObject.put("success", false);
                    }finally { //关闭流
                        try {
                            if (br != null){
                                br.close();
                            }
                            if (isr != null){
                                isr.close();
                            }
                        } catch (IOException e) {

                        }
                    }
                }
            }
        }
        return jsonObject;
    }


}
