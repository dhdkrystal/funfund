package com.util;

import com.entity.Security;
import java.util.List;

public class SetSecurity{
    private List item;

    public SetSecurity(){

    }

    public SetSecurity(List item){
        this.item = item;
    }


    public static Security getAsecurity(List item){
        Security security = new Security();
        security.setSecurityName((String) item.get(0));
        security.setDate((String) item.get(1));
        security.setOpen(Double.parseDouble(item.get(2).toString()));
        security.setHigh(Double.parseDouble(item.get(3).toString()));
        security.setLow(Double.parseDouble(item.get(4).toString()));
        security.setClose(Double.parseDouble(item.get(2).toString()));
        security.setType((String) item.get(6));
        security.setChangePrice(Double.parseDouble(item.get(2).toString()));
        security.setChangeRate(Double.parseDouble(item.get(2).toString()));
        return security;
    }


}
