package com.example.baitapv2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SinhVienRq {
    @JsonProperty("ten_sv")
    private String tenSv;
    @JsonProperty("dia_chi")
    private String diaChi;
    @JsonProperty("diem_tong")
    private int diemTong;

    public SinhVienRq() {
    }

    public SinhVienRq(String tenSv, String diaChi, int diemTong) {
        this.tenSv = tenSv;
        this.diaChi = diaChi;
        this.diemTong = diemTong;
    }

    public String getTenSv() {
        return tenSv;
    }

    public void setTenSv(String tenSv) {
        this.tenSv = tenSv;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getDiemTong() {
        return diemTong;
    }

    public void setDiemTong(int diemTong) {
        this.diemTong = diemTong;
    }
}
