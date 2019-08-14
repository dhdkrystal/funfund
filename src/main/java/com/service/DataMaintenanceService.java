package com.service;

import com.entity.Security;

import java.util.List;

public interface DataMaintenanceService {
    /**
     *do volume filter
     * @param type  the type of Security
     */
    List<Security> volumeFilter(String type, String date);


}
