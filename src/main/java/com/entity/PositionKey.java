package com.entity;

public class PositionKey {
    private String portfolioName;

    private String securityName;

    public PositionKey(){}

    public PositionKey(String portfolioName, String securityName) {
        this.portfolioName = portfolioName;
        this.securityName = securityName;
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
}