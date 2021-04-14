package com.example.baitapv2.model;

public class SinhVienRp {
    private int maSv;
    private String tenSv;
    private String diaChi;
    private int diemTong;

    public SinhVienRp() {
    }

    public SinhVienRp(int maSv, String tenSv, String diaChi, int diemTong) {
        this.maSv = maSv;
        this.tenSv = tenSv;
        this.diaChi = diaChi;
        this.diemTong = diemTong;
    }

    public SinhVienRp(String tenSv, String diaChi, int diemTong) {
        this.tenSv = tenSv;
        this.diaChi = diaChi;
        this.diemTong = diemTong;
    }

    public int getMaSv() {
        return maSv;
    }

    public void setMaSv(int maSv) {
        this.maSv = maSv;
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
