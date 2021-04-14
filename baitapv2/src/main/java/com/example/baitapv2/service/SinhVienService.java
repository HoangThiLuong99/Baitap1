package com.example.baitapv2.service;

import com.example.baitapv2.model.SinhVienRq;
import com.example.baitapv2.exception.MyException;
import com.example.baitapv2.model.MyResponse;
import com.example.baitapv2.model.SinhVienRp;

public interface SinhVienService {
    public MyResponse insertSv(SinhVienRq sinhVienRq) throws MyException;
    public MyResponse updateSv(int maSv, SinhVienRq sinhVienRq) throws MyException;
    public MyResponse deleteSv(int maSv) throws MyException;
    public MyResponse listSv();
    public MyResponse findById(int maSv) throws MyException;
}
