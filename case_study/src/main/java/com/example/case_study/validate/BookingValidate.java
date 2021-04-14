package com.example.case_study.validate;

import com.example.case_study.dto.BookingDto;
import com.example.case_study.dto.UserDto;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookingValidate {

    public boolean isValidUsername(BookingDto customerName)
    {
        String regex = "^[A-Za-z\\s]+$";
        Pattern p = Pattern.compile(regex);

        if (customerName == null) {
            return false;
        }
        Matcher m = p.matcher(customerName.getCustomerName());
        return m.matches();
    }

    public boolean isValidPhoneNumber(BookingDto customerPhone) {
        String regex = "(0[9|7|3|5][0-9])+([0-9]{7})\\b";
        Pattern p = Pattern.compile(regex);
        if (customerPhone == null) {
            return false;
        }
        Matcher m = p.matcher(customerPhone.getCustomerPhone());
        return m.matches();
    }



}
