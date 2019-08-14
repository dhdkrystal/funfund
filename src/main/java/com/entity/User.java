package com.entity;


public class User {
    private Integer userId;

    private String userName;

    private String userPassword;

    private Integer role;

    private String startDate;

    private String position;

    private String description;

    public User(){}

    public User(Integer userId, String userName, String userPassword, Integer role, String startDate, String position, String description) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.role = role;
        this.startDate = startDate;
        this.position = position;
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}