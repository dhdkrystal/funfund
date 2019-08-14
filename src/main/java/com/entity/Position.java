package com.entity;

public class Position extends PositionKey {
    private String type;

    private Integer amount;

    private Double close;

    private Double money;

    public Position(){
        super();
    }

    public Position(String type, Integer amount, Double close, Double money) {
        super();
        this.type = type;
        this.amount = amount;
        this.close = close;
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}