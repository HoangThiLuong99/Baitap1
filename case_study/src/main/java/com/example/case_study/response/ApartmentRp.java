package com.example.case_study.response;

import com.example.case_study.entities.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApartmentRp {
    @Expose
    @SerializedName("apartment_id")
    private int apartmentId;

    @Expose
    @SerializedName("apartment_name")
    private String apartmentName;

    @Expose
    @SerializedName("capacity")
    private int capacity;

    @Expose
    @SerializedName("area")
    private int area;

    @Expose
    @SerializedName("address")
    private String address;

    @Expose
    @SerializedName("price")
    private double price;

    @Expose
    @SerializedName("min_day")
    private int minDay;

    @Expose
    @SerializedName("max_day")
    private int maxDay;

    @Expose
    @SerializedName("user")
    private User user;

    public ApartmentRp() {
    }

    public ApartmentRp(int apartmentId, String apartmentName, int capacity, int area, String address,
                       double price, int minDay, int maxDay, User user) {
        this.apartmentId = apartmentId;
        this.apartmentName = apartmentName;
        this.capacity = capacity;
        this.area = area;
        this.address = address;
        this.price = price;
        this.minDay = minDay;
        this.maxDay = maxDay;
        this.user = user;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMinDay() {
        return minDay;
    }

    public void setMinDay(int minDay) {
        this.minDay = minDay;
    }

    public int getMaxDay() {
        return maxDay;
    }

    public void setMaxDay(int maxDay) {
        this.maxDay = maxDay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
