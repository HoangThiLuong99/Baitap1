package com.example.baitapv2.model;

public class MyResponse {
    private int status;
    private int code;
    private String message;
    private Object data;

    public MyResponse() {
    }

    public MyResponse(String message) {
        this.message = message;
    }

    public MyResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public MyResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public MyResponse(int status, int code, String message, Object data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;

    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
