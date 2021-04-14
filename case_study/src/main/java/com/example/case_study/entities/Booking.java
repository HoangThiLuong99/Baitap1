package com.example.case_study.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Booking {
    private int bookingId;
    private String customerName;
    private String customerPhone;
    private Date checkIn;
    private Date checkOut;
    private int numberOfGuest;
    private Date bookingDate;
    private int status;
    private double totalPrice;
    private int statementPay;
    private User user;
    private Apartment apartment;

    public Booking() {
    }


    public Booking(int bookingId, String customerName, String customerPhone, Date checkIn, Date checkOut,
                   int numberOfGuest, Date bookingDate, int status, double totalPrice,
                   int statementPay, User user, Apartment apartment) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberOfGuest = numberOfGuest;
        this.bookingDate = bookingDate;
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

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
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
