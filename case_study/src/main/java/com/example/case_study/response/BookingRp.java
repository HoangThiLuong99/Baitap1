package com.example.case_study.response;

import com.example.case_study.entities.Apartment;
import com.example.case_study.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.Date;

public class BookingRp {
    @Expose
    @SerializedName("booking_id")
    private int bookingId;

    @Expose
    @SerializedName("customer_name")
    private String customerName;

    @Expose
    @SerializedName("customer_phone")
    private String customerPhone;

    @Expose
    @SerializedName("check_in")
    private Date checkIn;

    @Expose
    @SerializedName("check_out")
    private Date checkOut;

    @Expose
    @SerializedName("number_of_guest")
    private int numberOfGuest;

    @Expose
    @SerializedName("booking_date")
    private Date booingDate;

    @Expose
    @SerializedName("status")
    private int status;

    @Expose
    @SerializedName("total_price")
    private double totalPrice;

    @Expose
    @SerializedName("statement_pay")
    private int statementPay;

    @Expose
    @SerializedName("user")
    private User user;

    @Expose
    @SerializedName("apartment")
    private Apartment apartment;

    public BookingRp() {
    }

    public BookingRp(int bookingId, String customerName, String customerPhone, Date checkIn, Date checkOut,
                     int numberOfGuest, Date booingDate, int status, double totalPrice,
                     int statementPay, User user, Apartment apartment) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberOfGuest = numberOfGuest;
        this.booingDate = booingDate;
        this.status = status;
        this.totalPrice = totalPrice;
        this.statementPay = statementPay;
        this.user = user;
        this.apartment = apartment;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
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

    public Date getBooingDate() {
        return booingDate;
    }

    public void setBooingDate(Date booingDate) {
        this.booingDate = booingDate;
    }

    public int getNumberOfGuest() {
        return numberOfGuest;
    }

    public void setNumberOfGuest(int numberOfGuest) {
        this.numberOfGuest = numberOfGuest;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatementPay() {
        return statementPay;
    }

    public void setStatementPay(int statementPay) {
        this.statementPay = statementPay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}
