package com.example.case_study.model;



public class UserModel {

    private String userName;

    private String userPhone;

    private String email;

    private String password;

    public UserModel() {
    }

    public UserModel(String userName, String userPhone, String email, String password) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
