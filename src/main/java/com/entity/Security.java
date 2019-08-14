package com.entity;

public class Security extends SecurityKey {
    private Double open;

    private Double high;

    private Double low;

    private Double close;

    private Integer volume;

    private String type;

    private Double changePrice;

    private Double changeRate;

    public Security(){
        super();
    }

    public Security(Double open, Double high, Double low, Double close, Integer volume, String type, Double changePrice, Double changeRate) {
        super();
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.type = type;
        this.changePrice = changePrice;
        this.changeRate = changeRate;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Double getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(Double changePrice) {
        this.changePrice = changePrice;
    }

    public Double getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(Double changeRate) {
        this.changeRate = changeRate;
    }
}