package com.entity;

public class OperationKey {
    private String portfolioName;

    private String securityName;

    private String date;

    public OperationKey(){}

    public OperationKey(String portfolioName, String securityName, String date) {
        this.portfolioName = portfolioName;
        this.securityName = securityName;
        this.date = date;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName == null ? null : portfolioName.trim();
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