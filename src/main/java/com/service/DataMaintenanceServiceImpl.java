package com.service;

import com.entity.Security;
import com.mapper.SecurityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DataMaintenanceServiceImpl implements DataMaintenanceService {

    @Autowired
    SecurityMapper mapper;

    /*
    *Data operation: filter the volume
     */
    public List<Security> volumeFilter(String type, String date){
        System.out.println("type: "+type+"date:"+date);
        List<Security> securityList = mapper.selectSecurityByType(type);
        List<Security> securityListToday = new ArrayList<Security>();
        for(Security security : securityList){
            if(security.getDate().equals(date)){
                securityListToday.add(security);
            }
        }

        Security security = new Security();
        for(int i = 0; i < securityListToday.size(); i++){
            security = securityListToday.get(i);
            int j;
            for(j = i - 1; j >= 0; j--){
                if(securityListToday.get(j).getVolume() < security.getVolume()){
                    securityListToday.set(j+1, securityListToday.get(j));
                }else{
                    break;
                }
            }
            securityListToday.set(j+1, security);
        }

        if(securityListToday.size() > 30){
            return securityListToday.subList(0,30);
        }
        return securityListToday;
    }



}
