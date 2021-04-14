package com.example.case_study.dao;

import com.example.case_study.entities.Apartment;
import com.example.case_study.entities.Booking;
import com.example.case_study.exception.MyException;
import com.example.case_study.model.ApartmentModel;


import java.util.List;

public interface ApartmentDao {


    boolean update(int apartmentId, ApartmentModel apartmentModel);

    Apartment findById(int apartmentId) throws MyException;

    List<Apartment> findAll();

    boolean create(ApartmentModel apartmentModel) throws MyException;

    List<Apartment> findApartmentByUser(int userId) throws MyException;

    List<Apartment> apartmentIsBooking(int userId) throws MyException;

    List<Apartment> search(String area, String capacity, String price1, String price2) throws MyException;




}
