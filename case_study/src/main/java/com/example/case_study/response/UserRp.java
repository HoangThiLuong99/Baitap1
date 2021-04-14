package com.example.case_study.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserRp {
    @Expose
    @SerializedName("user_id")
    private int userId;
    @Expose
    @SerializedName("user_name")
    private String userName;
    @Expose
    @SerializedName("user_phone")
    private String userPhone;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("password")
    private String password;


    public UserRp() {
    }

    public UserRp(int userId, String userName, String userPhone, String email, String password) {
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.email = email;
        this.password = password;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
