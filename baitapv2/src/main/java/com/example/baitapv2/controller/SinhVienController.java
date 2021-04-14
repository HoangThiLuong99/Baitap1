package com.example.baitapv2.controller;


import com.example.baitapv2.model.SinhVienRq;
import com.example.baitapv2.exception.MyException;
import com.example.baitapv2.model.MyResponse;
import com.example.baitapv2.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/luong")
public class SinhVienController {
    @Autowired
    private SinhVienService sinhVienService;
    private String message;
    @PostMapping("/sinhvien")
    public ResponseEntity<String> insertSv(@RequestBody SinhVienRq sinhVienRq) {

        int code;
        Gson gson = new GsonBuilder().create();
        try {

            MyResponse ms = sinhVienService.insertSv(sinhVienRq);
            String st = gson.toJson(ms, MyResponse.class);
            return new ResponseEntity<>(st, HttpStatus.OK);
        } catch (MyException e) {
            message = e.getMessage();
            code = e.getCode();
            e.printStackTrace();
        }

        return new ResponseEntity<>(gson.toJson(new MyResponse(code, message)), HttpStatus.OK);
    }

    @PostMapping("/sinhvien/update/{maSv}")
    public ResponseEntity<MyResponse> updateSv(@PathVariable("maSv") int maSv, @RequestBody SinhVienRq sinhVienRq) {
        int code;
        String message;
        Gson gson = new GsonBuilder().create();

        try {
           MyResponse rs =  sinhVienService.updateSv(maSv, sinhVienRq);
//            MyResponse ms = sinhVienService.updateSv(maSv, sinhVienRq);
//            String st = gson.toJson(ms, MyResponse.class);
//            return new ResponseEntity<>(st, HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(rs);
        } catch (MyException e) {
            message = e.getMessage();
            code = e.getCode();
            e.printStackTrace();
        }
        //return new ResponseEntity<>(gson.toJson(new MyResponse(code, message)), HttpStatus.OK);
        return new ResponseEntity<>(new MyResponse(code, message), HttpStatus.OK);
    }

    @GetMapping("/sinhvien/del/{maSv}")
    public ResponseEntity<String> delete(@PathVariable("maSv") int maSv) {
        int code;
        String message;
        Gson gson = new GsonBuilder().create();
        try {
            MyResponse ms = sinhVienService.deleteSv(maSv);
            String st = gson.toJson(ms, MyResponse.class);
            return new ResponseEntity<>(st, HttpStatus.OK);

        } catch (MyException e) {
            message = e.getMessage();
            code = e.getCode();
            e.printStackTrace();
        }
        return new ResponseEntity<>(gson.toJson(new MyResponse(code, message)), HttpStatus.OK);

    }

    @GetMapping("/sinhvien/top3")
    public ResponseEntity<MyResponse> getSinhVien() {
        return ResponseEntity.status(HttpStatus.OK).body(sinhVienService.listSv());
    }

    @GetMapping("/sinhvien/{maSv}")
    public ResponseEntity<String> findById(@PathVariable("maSv") int maSv) {
        Gson gson = new GsonBuilder().create();
        String message;
        int code;
        try {
            MyResponse ms = sinhVienService.findById(maSv);
            String st = gson.toJson(ms, MyResponse.class);
            return new ResponseEntity<>(st, HttpStatus.OK);

        } catch (MyException e) {
            message = e.getMessage();
            code = e.getCode();
            e.printStackTrace();
        }
        return new ResponseEntity<>(gson.toJson(new MyResponse(code, message)), HttpStatus.OK);
    }

}
