package com.entity;

public class SecurityKey {
    private String securityName;

    private String date;

    public SecurityKey(){ }

    public SecurityKey(String securityName, String date) {
        this.securityName = securityName;
        this.date = date;
    }

    public String getSecurityName() {
        return securityName;
    }


    public void setSecurityName(String securityName) {
        this.securityName = securityName == null ? null : securityName.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }
}