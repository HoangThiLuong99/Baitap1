package com.example.case_study.service;

import com.example.case_study.exception.MyException;
import com.example.case_study.dto.ApartmentDto;
import com.example.case_study.response.Response;



public interface ApartmentService {
    public Response findById(int apartmentId) throws MyException;
    public Response create(ApartmentDto apartmentDto) throws MyException;
    public Response update(int apartmentId, ApartmentDto apartmentDto) throws MyException;
    public Response findAll() throws MyException;
    public Response findApartmentByUser(int userId) throws MyException;
    public Response apartmentIsBooking(int userId) throws MyException;
    public Response search(String area, String capacity, String price1, String price2) throws MyException;
}
