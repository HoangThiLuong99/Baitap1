package com.example.baitapv2.dao;


import com.example.baitapv2.entities.SinhVien;
import com.example.baitapv2.model.SinhVienRq;

import java.util.List;

public interface SinhVienDao {
    public boolean insert(SinhVienRq sinhVienRq);
    public boolean updateSv(int maSv, SinhVienRq sinhVienRq);
    public boolean delete(int maSv);
    public List<SinhVien> list();
    public SinhVien findById(int maSv);

}
