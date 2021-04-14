package com.example.case_study.dto;

import com.example.case_study.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApartmentDto {

    @JsonProperty("apartment_name")
    private String apartmentName;

    @JsonProperty("capacity")
    private int capacity;

    @JsonProperty("area")
    private int area;

    @JsonProperty("address")
    private String address;

    @JsonProperty("price")
    private double price;

    @JsonProperty("min_day")
    private int minDay;

    @JsonProperty("max_day")
    private int maxDay;

    @JsonProperty("users_id")
    private int userId;

    public ApartmentDto() {
    }

    public ApartmentDto(String apartmentName, int capacity, int area, String address, double price,
                        int minDay, int maxDay, int userId) {
        this.apartmentName = apartmentName;
        this.capacity = capacity;
        this.area = area;
        this.address = address;
        this.price = price;
        this.minDay = minDay;
        this.maxDay = maxDay;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
