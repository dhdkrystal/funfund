package com.entity;


public class Portfolio {
    private String portfolioName;

    private String date;

    private String manager;

    private Integer symbols;

    private Double dayChangePrice;

    private Double dayChange;

    private Double totalChangePrice;

    private Double totalChange;

    public Portfolio(){}

    public Portfolio(String portfolioName, String date, String manager, Integer symbols, Double dayChangePrice, Double dayChange, Double totalChangePrice, Double totalChange) {
        this.portfolioName = portfolioName;
        this.date = date;
        this.manager = manager;
        this.symbols = symbols;
        this.dayChangePrice = dayChangePrice;
        this.dayChange = dayChange;
        this.totalChangePrice = totalChangePrice;
        this.totalChange = totalChange;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName == null ? null : portfolioName.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public Integer getSymbols() {
        return symbols;
    }

    public void setSymbols(Integer symbols) {
        this.symbols = symbols;
    }

    public Double getDayChangePrice() {
        return dayChangePrice;
    }

    public void setDayChangePrice(Double dayChangePrice) {
        this.dayChangePrice = dayChangePrice;
    }

    public Double getDayChange() {
        return dayChange;
    }

    public void setDayChange(Double dayChange) {
        this.dayChange = dayChange;
    }

    public Double getTotalChangePrice() {
        return totalChangePrice;
    }

    public void setTotalChangePrice(Double totalChangePrice) {
        this.totalChangePrice = totalChangePrice;
    }

    public Double getTotalChange() {
        return totalChange;
    }

    public void setTotalChange(Double totalChange) {
        this.totalChange = totalChange;
    }
}