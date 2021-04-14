package com.example.case_study.validate;

import com.example.case_study.dto.ApartmentDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApartmentValidate {
    public boolean isValidApartmentName(ApartmentDto apartmentName)
    {
        String regex = "^[A-Za-z\\s\\w]+$";
        Pattern p = Pattern.compile(regex);

        if (apartmentName == null) {
            return false;
        }
        Matcher m = p.matcher(apartmentName.getApartmentName());
        return m.matches();
    }

    public boolean check(ApartmentDto apartmentDto){
        if(apartmentDto.getCapacity() > 0 && apartmentDto.getArea() > 0
        && !apartmentDto.getAddress().isEmpty() && apartmentDto.getPrice()>0
                && apartmentDto.getUserId() > 0){
            return true;
        }
        return false;
    }

    public boolean check1(ApartmentDto apartmentDto){
        if (apartmentDto.getMinDay() >= 0 && apartmentDto.getMaxDay()>= apartmentDto.getMinDay()){
            return true;
        }
        return false;
    }




}
