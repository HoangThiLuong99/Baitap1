package com.example.case_study.controller;


import com.example.case_study.dto.ApartmentDto;
import com.example.case_study.dto.BookingDto;
import com.example.case_study.exception.MyException;
import com.example.case_study.response.Response;
import com.example.case_study.service.BookingService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
@RequestMapping("/luong")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/bookings")
    public ResponseEntity<String> findAll(){
        int code;
        Gson gson = new GsonBuilder().create();
        try {

            Response rs = bookingService.findAll();
            String st = gson.toJson(rs, Response.class);
            return new ResponseEntity<>(st, HttpStatus.OK);
        } catch (MyException e) {
            message = e.getMessage();
            code = e.getCode();
            e.printStackTrace();
        }

        return new ResponseEntity<>(gson.toJson(new Response(code, message)), HttpStatus.OK);
    }

    private String message;


    @GetMapping("/booking/findBookingSuccess/{userId}")

    private ResponseEntity<String> findBookingSuccess(@PathVariable int userId){
        int code;
        Gson gson = new GsonBuilder().create();
        try {
            Response rs = bookingService.findBookingSuccess(userId);

            String st = gson.toJson(rs, Response.class);
            return new ResponseEntity<>(st, HttpStatus.OK);


        } catch (MyException e) {
            message = e.getMessage();
            code = e.getCode();
            e.printStackTrace();
        }
        return new ResponseEntity<>(gson.toJson(new Response(code, message)), HttpStatus.OK);

    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<String> findById(@PathVariable("bookingId") int bookingId){
        int code;
        Gson gson = new GsonBuilder().create();
        try{
            Response rs = bookingService.findById(bookingId);
            String st = gson.toJson(rs, Response.class);
            return new ResponseEntity<>(st, HttpStatus.OK);

        } catch (MyException e) {
            message = e.getMessage();
            code = e.getCode();
            e.printStackTrace();
        }

        return new ResponseEntity<>(gson.toJson(new Response(code, message)), HttpStatus.OK);
    }

    @PostMapping("/booking/create")

    public ResponseEntity<String> create(@RequestBody BookingDto bookingDto){
        int code;
        Gson gson = new GsonBuilder().create();

        try {
            Response rs = bookingService.create(bookingDto);
            String st = gson.toJson(rs, Response.class);
            return  new ResponseEntity<>(st, HttpStatus.OK);
        } catch (MyException  e) {
            message = e.getMessage();
            code = e.getCode();
            e.printStackTrace();
        }
        return new ResponseEntity<>(gson.toJson(new Response(code, message)), HttpStatus.OK);

    }

    @PostMapping("/booking/update/{bookingId}")

    public ResponseEntity<String> update(@PathVariable("bookingId") int bookingId, @RequestBody BookingDto bookingDto){
        int code;
        Gson gson = new GsonBuilder().create();

        try {
            Response rs = bookingService.update(bookingId, bookingDto);
            String st = gson.toJson(rs, Response.class);
            return new ResponseEntity<>(st, HttpStatus.OK);
        } catch (MyException e) {
            message = e.getMessage();
            code = e.getCode();
            e.printStackTrace();
        }
         return new ResponseEntity<>(gson.toJson(new Response(code, message)), HttpStatus.OK);

    }
}
