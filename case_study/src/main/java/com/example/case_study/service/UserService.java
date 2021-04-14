package com.example.case_study.service;

import com.example.case_study.exception.MyException;
import com.example.case_study.response.Response;
import com.example.case_study.dto.UserDto;


public interface UserService {
    /**
     * tìm user theo id
     * @param userId định danh người dùng
     * @return thông tin user
     * @throws MyException nếu có lỗi xảy ra
     */
    public Response findById(int userId) throws MyException;
    public Response findAll() throws MyException;
    public Response create(UserDto userDto) throws MyException;
    public Response update(int userId, UserDto userDto) throws MyException;


}
