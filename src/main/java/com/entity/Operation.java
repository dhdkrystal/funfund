package com.entity;

public class Operation extends OperationKey {
    private Integer operation;

    private Integer amount;

    private Double price;

    public Operation(){
        super();
    }

    public Operation(Integer operation, Integer amount, Double price) {
        super();
        this.operation = operation;
        this.amount = amount;
        this.price = price;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}