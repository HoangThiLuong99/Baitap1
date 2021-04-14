package com.example.baitapv2.service;

import com.example.baitapv2.dao.SinhVienDao;
import com.example.baitapv2.entities.SinhVien;
import com.example.baitapv2.model.SinhVienRq;
import com.example.baitapv2.exception.MyException;

import com.example.baitapv2.model.MyResponse;
import com.example.baitapv2.model.SinhVienRp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SinhVienServiceImpl implements SinhVienService {
    @Autowired
    private SinhVienDao sinhVienDao;

    public boolean check(SinhVienRq sv) {
        if (!sv.getTenSv().isEmpty()
                && !sv.getDiaChi().isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean check1(SinhVienRq sv){
        if(sv.getDiemTong() >= 0 &&
                sv.getDiemTong() <= 10){
            return true;
        }
        return false;
    }
    

    @Override
    public MyResponse insertSv(SinhVienRq sinhVienRq) throws MyException {
        MyResponse ms = new MyResponse();

        if (!check(sinhVienRq)) {
            return new MyResponse(500, "Ten hoac dia chi khong duoc bo trong");
        }

        if(!check1(sinhVienRq)){
            return new MyResponse(500, "Diem tong phai nam trong khoang tu 0 den 10");
        }


        if (!sinhVienDao.insert(sinhVienRq)) {
            return new MyResponse("Insert khong thanh cong");
        }
        ms.setStatus(1);
        ms.setMessage("Insert thanh cong");
        ms.setCode(200);

        return ms;
    }

    @Override
    public MyResponse updateSv(int maSv, SinhVienRq sinhVienRq) throws MyException {
        if (maSv < 0 || !check(sinhVienRq)) {
            return new MyResponse("Du lieu khong hop le");
        }
        if (!sinhVienDao.updateSv(maSv, sinhVienRq)) {
            return new MyResponse(500, "Khong tim thay du lieu update");
        }

        MyResponse svm = new MyResponse();
        svm.setStatus(1);
        svm.setMessage("Update thanh cong");
        svm.setCode(200);

        return svm;
    }

    @Override
    public MyResponse deleteSv(int maSv) throws MyException {
        if (maSv < 0) {
            return new MyResponse(500, "Khong ton tai maSv");
        }
        SinhVien sv = sinhVienDao.findById(maSv);
        if (sv == null){
            return new MyResponse("Khong tim thay masv can xoa");
        }
        if (!sinhVienDao.delete(maSv)) {
            return new MyResponse(404, "Xoa khong thanh cong");
        }

        MyResponse rs = new MyResponse();
        rs.setStatus(1);
        rs.setMessage("OK");
        rs.setCode(200);
        return rs;
    }

    @Override
    public MyResponse listSv() {
        MyResponse rs = new MyResponse();
        rs.setStatus(1);
        rs.setMessage("OK");
        rs.setCode(200);
        List<SinhVienRp> lst = new ArrayList<>();
        for (SinhVien sv : sinhVienDao.list()) {
            lst.add(new SinhVienRp(sv.getMaSv(), sv.getTenSv(),
                    sv.getDiaChi(), sv.getDiemTong()));
        }
        rs.setData(lst);
        return rs;
    }

    @Override
    public MyResponse findById(int maSv) throws MyException {
        if (maSv < 0) {
            return new MyResponse(500, "Khong ton tai masv");
        }
        SinhVien s = sinhVienDao.findById(maSv);
        if (s == null) {
            return new MyResponse("Khong tim thay sinh vien");
        }
        SinhVienRp sv = new SinhVienRp();
        sv.setMaSv(s.getMaSv());
        sv.setTenSv(s.getTenSv());
        sv.setDiaChi(s.getDiaChi());
        sv.setDiemTong(s.getDiemTong());

        MyResponse rs = new MyResponse();
        rs.setStatus(1);
        rs.setMessage("Success");
        rs.setCode(200);
        rs.setData(sv);

        return rs;
    }
}
