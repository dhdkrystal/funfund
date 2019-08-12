package com.service;

import com.entity.Portfolio;
import com.entity.Security;
import com.mapper.PortfolioMapper;
import com.mapper.SecurityMapper;
import com.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.util.Constant.*;


@Service
public class DataMaintenanceServiceImpl implements DataMaintenanceService {

    @Autowired
    SecurityMapper mapper;
    SecurityService service;

    /*
    *Data operation: filter the volume
     */
    public List<Security> volumeFilter(String type, String date){
        List<Security> securityListToday = service.getSecurityList(type, date);

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
