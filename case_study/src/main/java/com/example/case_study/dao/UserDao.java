package com.example.case_study.dao;


import com.example.case_study.entities.User;
import com.example.case_study.exception.MyException;
import com.example.case_study.model.UserModel;

import java.util.List;


public interface UserDao {
    User findById(int userId) throws MyException;

    boolean create(UserModel userModel) throws MyException;

    boolean update(int userId, UserModel user) throws MyException;

    List<User> findAll();

}
