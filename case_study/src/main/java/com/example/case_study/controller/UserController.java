package com.example.case_study.controller;


import com.example.case_study.exception.MyException;
import com.example.case_study.dto.UserDto;
import com.example.case_study.response.Response;
import com.example.case_study.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/luong")
public class UserController {

    @Autowired
    private UserService userService;
    private String message;


    @GetMapping("/user/{userId}")
    public ResponseEntity<String> findById(@PathVariable("userId") int userId){
        int code;
        Gson gson = new GsonBuilder().create();
        try {

            Response rs = userService.findById(userId);
            String st = gson.toJson(rs, Response.class);
            return new ResponseEntity<>(st, HttpStatus.OK);
        } catch (MyException e) {
            message = e.getMessage();
            code = e.getCode();
            e.printStackTrace();
        }

        return new ResponseEntity<>(gson.toJson(new Response(code, message)), HttpStatus.OK);

    }

    @GetMapping("/users")
    public ResponseEntity<String> findAll(){
        int code;
        Gson gson = new GsonBuilder().create();
        try{
            Response rs = userService.findAll();
            String st = gson.toJson(rs, Response.class);
            return  new ResponseEntity<>(st, HttpStatus.OK);
        } catch (MyException e) {
        message = e.getMessage();
        code = e.getCode();
        e.printStackTrace();
    }
        return new ResponseEntity<>(gson.toJson(new Response(code, message)), HttpStatus.OK);

    }

    @PostMapping("/user/create")

    public ResponseEntity<String> create(@RequestBody UserDto userDto){
        int code;
        Gson gson = new GsonBuilder().create();

        try {
            Response rs = userService.create(userDto);
            String st = gson.toJson(rs, Response.class);
            return  new ResponseEntity<>(st, HttpStatus.OK);
        } catch (MyException e) {
            message = e.getMessage();
            code = e.getCode();
            e.printStackTrace();
        }
        return new ResponseEntity<>(gson.toJson(new Response(code, message)), HttpStatus.OK);
    }

    @PostMapping("/user/update/{userId}")

    public ResponseEntity<String> update(@PathVariable("userId") int userId, @RequestBody UserDto userDto){
        int code;
        Gson gson = new GsonBuilder().create();

        try {
            Response rs = userService.update(userId, userDto);
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
