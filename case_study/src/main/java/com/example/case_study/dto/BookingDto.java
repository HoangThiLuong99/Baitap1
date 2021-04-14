package com.example.case_study.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.Date;

public class BookingDto {
    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("customer_phone")
    private String customerPhone;

    @JsonProperty("check_in")
    private Date checkIn;

    @JsonProperty("check_out")
    private Date checkOut;

    @JsonProperty("number_of_guest")
    private int numberOfGuest;

    @JsonProperty("statement_pay")
    private int statementPay;

    @JsonProperty("users_id")
    private int userId;

    @JsonProperty("apartments_id")
    private int apartmentId;


    public BookingDto() {
    }

    public BookingDto(String customerName, String customerPhone, Date checkIn, Date checkOut,
                      int numberOfGuest, int statementPay, int userId, int apartmentId) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberOfGuest = numberOfGuest;
        this.statementPay = statementPay;
        this.userId = userId;
        this.apartmentId = apartmentId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }


    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public int getNumberOfGuest() {
        return numberOfGuest;
    }

    public void setNumberOfGuest(int numberOfGuest) {
        this.numberOfGuest = numberOfGuest;
    }

    public int getStatementPay() {
        return statementPay;
    }

    public void setStatementPay(int statementPay) {
        this.statementPay = statementPay;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }
}
