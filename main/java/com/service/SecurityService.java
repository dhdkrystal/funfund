package com.service;

import com.entity.Security;
import com.entity.SecurityKey;
import com.entity.User;
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

    /*Update the security
     * @param security  the entity of security
     */
    Error updateSecurity(Security security);

    /*
    *Select security list of stock or bond or futures or etfs
     */
    List<Security> getSecurityList(String type);
}
