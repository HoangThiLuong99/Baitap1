package com.example.case_study.validate;

import com.example.case_study.dto.UserDto;
import com.example.case_study.model.UserModel;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidate {

    public boolean isValidUsername(UserDto userName)
    {
        String regex = "^[A-Za-z\\s]+$";
        Pattern p = Pattern.compile(regex);

        if (userName == null) {
            return false;
        }
        Matcher m = p.matcher(userName.getUserName());
        return m.matches();
    }

    public boolean isValidEmail(UserDto email) {
        String regex = "^[\\w_\\.+]*[\\w_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        Pattern p = Pattern.compile(regex);
        if (email == null) {
            return false;
        }
        Matcher m = p.matcher(email.getEmail());
        return m.matches();
    }

     public boolean isValidPhoneNumber(UserDto userPhone) {
        String regex = "(0[9|7|3|5][0-9])+([0-9]{7})\\b";
         Pattern p = Pattern.compile(regex);
         if (userPhone == null) {
             return false;
         }
         Matcher m = p.matcher(userPhone.getUserPhone());
         return m.matches();
    }





}
