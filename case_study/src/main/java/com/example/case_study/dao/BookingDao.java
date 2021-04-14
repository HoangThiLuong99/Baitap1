package com.example.case_study.dao;

import com.example.case_study.entities.Booking;
import com.example.case_study.exception.MyException;
import com.example.case_study.model.BookingModel;

import java.text.ParseException;
import java.util.List;

public interface BookingDao{
    Booking findById(int bookingId) throws MyException;

    List<Booking> findBookingSuccess(int userId) throws MyException;

    boolean create(BookingModel bookingModel) throws MyException;

    boolean update(int bookingId, BookingModel bookingModel);

    List<Booking> findAll();

    List<Booking> findBookingByApartmentId(int apartmentId) throws MyException;





}
