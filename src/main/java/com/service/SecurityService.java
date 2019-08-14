package com.service;

import com.entity.Security;
import com.entity.SecurityKey;
import com.util.Error;

import java.util.List;

public interface SecurityService {
    /**
     *Insert the security
     * @param security  the entity of Security
     */
    Error addSecurity(Security security);

    /**
     *Delete the security
     * @param securityKey  the primary key of security
     */
    Error deleteSecurity(SecurityKey securityKey);


    Error deleteSecurityByName(String securityName);

    /*Update the security
     * @param security  the entity of security
     */
    Error updateSecurity(Security security);

    /*
    *Select security list of stock or bond or futures or etfs
     */
    List<Security> getSecurityList(String type, String date);

    /*
    *Select a security's history
     */
    List<Security> getSecurityHistory(String securityName);

    /*get a security*/
    Security getSecurity(String securityName, String date);

    /*get the security name list*/
    List<String> getSecurityNameList();

    /*upload price*/
    Boolean uploadSecurity(List<List<String>> strs);
}
