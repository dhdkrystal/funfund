package com.service;

import com.entity.Security;
import com.entity.SecurityKey;
import com.mapper.SecurityMapper;
import com.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    *Admin operation: get ecurity list of stock or bond or futures or etfs
     */
    public List<Security> getSecurityList(String type){
        List<Security> securityList = mapper.selectSecurityByType(type);
        if(securityList == null){
            return null;
        }
       return securityList;
    }

}
