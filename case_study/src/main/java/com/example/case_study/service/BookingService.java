package com.example.case_study.service;

import com.example.case_study.dto.BookingDto;
import com.example.case_study.exception.MyException;

import com.example.case_study.response.Response;



public interface BookingService {
    public Response findAll() throws MyException;
    public Response findBookingSuccess(int userId) throws MyException;
    public Response update(int bookingId, BookingDto bookingDto) throws MyException;
    public Response create(BookingDto bookingDto) throws MyException;
    public Response findById(int bookingId) throws MyException;
    public Response findBookingByApartmentId(int apartmentId) throws MyException;
}
