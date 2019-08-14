package com.service;

import com.entity.Security;
import com.entity.SecurityKey;
import com.mapper.SecurityMapper;
import com.util.Error;
import com.util.SetSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.util.Constant.*;


@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    SecurityMapper mapper;

    /*
    Admin add security
     */
    public Error addSecurity(Security security){
        Security security1;
        Error error ;
        SecurityKey securityKey = new SecurityKey();
        securityKey.setSecurityName(security.getSecurityName());
        securityKey.setDate(security.getDate());
//        System.out.println("Date: "+securityKey.getDate());
        try{
            security1=mapper.selectByPrimaryKey(securityKey);
            if(security1 != null)
            {
                error= new Error(DUPLICATE_NAME,"Service:the security name already exists!");
            }else{
                mapper.insertSelective(security);
                error= new Error(ADD_SUCCESS,"Service:add security successfully!");
            }

        }catch (Exception e){
            e.printStackTrace();
            error= new Error(ADD_ERROR,"Service:failed to add security!");
        }
        return error;
    }


    /*
    Admin delete security
     */
    public Error deleteSecurity(SecurityKey securityKey){
        Error error;
        try {
            mapper.deleteByPrimaryKey(securityKey);
            error = new Error(DELETE_SUCCESS,"Service:delete security successfully!");
        }catch (Exception e){
            e.printStackTrace();
            error = new Error(DELETE_ERROR,"Service:Failed to delete security!");
        }
        return error;
    }


    /*
    Admin delete securities
     */
    public Error deleteSecurityByName(String securityName){
        Error error;
        try {
            mapper.deleteByName(securityName);
            error = new Error(DELETE_SUCCESS,"Service:delete security successfully!");
        }catch (Exception e){
            e.printStackTrace();
            error = new Error(DELETE_ERROR,"Service:Failed to delete security!");
        }
        return error;
    }

    /*
    Admin update security
     */
    public Error updateSecurity(Security security){
        Error error;
        try {
            mapper.updateByPrimaryKeySelective(security);
            error = new Error(UPDATE_SUCCESS,"Service:update security successfully!");
        }catch (Exception e){
            e.printStackTrace();
            error = new Error(UPDATE_ERROR,"Service:Failed to update security!");
        }
        return error;
    }


    /*
    *Admin operation: get today security list of stock or bond or futures or etfs
     */
    public List<Security> getSecurityList(String type, String date){
        System.out.println("type: "+type+"date:"+date);
        List<Security> securityList = mapper.selectSecurityByType(type);
        List<Security> targetList = new ArrayList<Security>();
        for(Security security : securityList){
            if(security.getDate().equals(date)){
                targetList.add(security);
            }
        }
        if(targetList == null){
            return null;
        }
       return targetList;
    }

    /*
    *Admin operation: get a security history
     */
    public List<Security> getSecurityHistory(String securityName){
        List<Security> securityList = mapper.selectSecurityByName(securityName);
        if(securityList == null){
            return null;
        }
        return securityList;
    }

    /*
    *get a security
     */
    public Security getSecurity(String securityName, String date){
        SecurityKey securityKey = new SecurityKey();
        securityKey.setSecurityName(securityName);
        securityKey.setDate(date);
        Security security = mapper.selectByPrimaryKey(securityKey);
        if(security == null){
            System.out.println("Service: failed to get the security!");
        }
        return security;
    }


    /*
    *get the security name list
     */
    public List<String> getSecurityNameList(){
        List<Security> securityList = mapper.selectAllSecurity();
        List<String> allNames = new ArrayList<String>();
        for(Security security : securityList){
            allNames.add(security.getSecurityName());
        }
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = allNames.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        allNames.clear();
        allNames.addAll(newList);
        return allNames;
    }


    /*
     *Admin operation: upload security price
     */

    @Override
    public Boolean uploadSecurity(List<List<String>> strs) {

        for(int i=0; i<strs.size(); i++){
            List item = strs.get(i);
            Security security = SetSecurity.getAsecurity(item);
            Error error = addSecurity(security);
            if(error.getCode()==FILTER_ERROR){
                return false;
            }
        }
        return true;
        //return error;



    }

}
