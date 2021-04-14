package com.example.case_study.controller;


import com.example.case_study.exception.MyException;
import com.example.case_study.dto.ApartmentDto;
import com.example.case_study.response.Response;
import com.example.case_study.service.ApartmentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/luong")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    private String message;


    @GetMapping("/apartment/{apartmentId}")
    public ResponseEntity<String> findById(@PathVariable("apartmentId") int apartmentId){
        int code;
        Gson gson = new GsonBuilder().create();
        try{
            Response rs = apartmentService.findById(apartmentId);
            String st = gson.toJson(rs, Response.class);
            return new ResponseEntity<>(st, HttpStatus.OK);

        } catch (MyException e) {
            message = e.getMessage();
            code = e.getCode();
            e.printStackTrace();
        }

        return new ResponseEntity<>(gson.toJson(new Response(code, message)), HttpStatus.OK);
    }

    @PostMapping("/apartment/create")

    public ResponseEntity<String> create(@RequestBody ApartmentDto apartmentDto){
        int code;
        Gson gson = new GsonBuilder().create();

        try {
            Response rs = apartmentService.create(apartmentDto);
            String st = gson.toJson(rs, Response.class);
            return  new ResponseEntity<>(st, HttpStatus.OK);
        } catch (MyException e) {
            message = e.getMessage();
            code = e.getCode();
            e.printStackTrace();
        }
        return new ResponseEntity<>(gson.toJson(new Response(code, message)), HttpStatus.OK);
    }

    @PostMapping("/apartment/update/{apartmentId}")

    public ResponseEntity<String> update(@PathVariable("apartmentId") int apartmentId, @RequestBody ApartmentDto apartmentDto){
        int code;
        Gson gson = new GsonBuilder().create();

        try {
            Response rs = apartmentService.update(apartmentId, apartmentDto);
            String st = gson.toJson(rs, Response.class);
            return new ResponseEntity<>(st, HttpStatus.OK);
        } catch (MyException e) {
            message = e.getMessage();
            code = e.getCode();
            e.printStackTrace();
        }
        return new ResponseEntity<>(gson.toJson(new Response(code, message)), HttpStatus.OK);
    }

    @GetMapping("/apartments")
    public ResponseEntity<String> findAll() throws MyException {
        int code;
        Gson gson = new GsonBuilder().create();
        try {

            Response rs = apartmentService.findAll();
            String st = gson.toJson(rs, Response.class);
            return new ResponseEntity<>(st, HttpStatus.OK);
        } catch (MyException e) {
            message = e.getMessage();
            code = e.getCode();
            e.printStackTrace();
        }

        return new ResponseEntity<>(gson.toJson(new Response(code, message)), HttpStatus.OK);
    }

    @GetMapping("/apartment/findApartmentByUser/{userId}")
    public ResponseEntity<String> findApartmentByUser(@PathVariable int userId){

        int code;
        Gson gson = new GsonBuilder().create();
        try {
            Response rs = apartmentService.findApartmentByUser(userId);
            String st = gson.toJson(rs, Response.class);
            return new ResponseEntity<>(st,HttpStatus.OK);

        } catch (MyException e) {
            message = e.getMessage();
            code = e.getCode();
            e.printStackTrace();
        }
        return new ResponseEntity<>(gson.toJson(new Response(code, message)), HttpStatus.OK);

    }

    @GetMapping(value = "/apartment/apartmentIsBooking/{userId}")
    public ResponseEntity<String> apartmentIsBooking(@PathVariable int userId){

                int code;
               Gson gson = new GsonBuilder().create();
                try {
                    Response rs = apartmentService.apartmentIsBooking(userId);

                    String st = gson.toJson(rs, Response.class);
                    return new ResponseEntity<>(st, HttpStatus.OK);


                } catch (MyException e) {
                    message = e.getMessage();
                    code = e.getCode();
                    e.printStackTrace();
                }
                return new ResponseEntity<>(gson.toJson(new Response(code, message)), HttpStatus.OK);

    }


    @GetMapping(value = "/apartment/search")
    public ResponseEntity<String> search(@RequestParam("area")  String area, @RequestParam("capacity") String capacity,
                                           @RequestParam("price1") String price1, @RequestParam("price2") String price2){

        int code;
        Gson gson = new GsonBuilder().create();

        try {
            Response rs = apartmentService.search(area, capacity, price1, price2);

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
