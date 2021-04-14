package com.example.case_study.serviceimpl;
import com.example.case_study.dao.UserDao;
import com.example.case_study.entities.User;
import com.example.case_study.exception.MyException;
import com.example.case_study.model.UserModel;
import com.example.case_study.response.Response;
import com.example.case_study.dto.UserDto;
import com.example.case_study.response.UserRp;
import com.example.case_study.service.UserService;
import com.example.case_study.validate.UserValidate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    @Lazy
    private UserDao userDao;

    private ModelMapper mapper;

    public UserServiceImpl(UserDao userDao, ModelMapper mapper){
        this.userDao = userDao;
        this.mapper = mapper;
    }

    private UserDto mapToDto(UserModel userModel){
        UserDto userDto = mapper.map(userModel, UserDto.class);
        return userDto;
    }

    private UserModel mapToEntity(UserDto userDto){
        UserModel userModel = mapper.map(userDto, UserModel.class);
        return userModel;
    }

    @Override
    public Response findById(int userId) throws MyException {
        if(userId < 0){
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.value(), "khong ton tai userID");
        }
        User user = userDao.findById(userId);
        if (user == null){
            return new Response(HttpStatus.NOT_FOUND.value(), "khong tim thay user");
        }
        UserRp userRp = new UserRp();

        userRp.setUserId(user.getUserId());
        userRp.setUserName(user.getUserName());
        userRp.setUserPhone(user.getUserPhone());
        userRp.setEmail(user.getEmail());
        userRp.setPassword(user.getPassword());


        Response rp = new Response();
        rp.setStatus(1);
        rp.setMessage("tim kiem thanh cong");
        rp.setCode(200);
        rp.setData(userRp);

        return rp;

    }

    @Override
    public Response findAll() throws MyException{

        Response rs = new Response();
        rs.setStatus(1);
        rs.setMessage("OK");
        rs.setCode(200);
        List<UserRp> lst = new ArrayList<>();
        for (User user : userDao.findAll()) {
            lst.add(new UserRp(user.getUserId(), user.getUserName(),
                    user.getUserPhone(), user.getEmail(), user.getPassword()));

        }
        rs.setData(lst);
        return rs;
    }

    UserValidate userValidate = new UserValidate();
    @Override
    public Response create(UserDto userDto) throws MyException {
        UserModel userModel = mapToEntity(userDto);
        if(!userValidate.isValidUsername(userDto)){
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.value(), "userName nhap khong dung dinh dang");
        }

        if(!userValidate.isValidPhoneNumber(userDto )){
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.value(), "phoneNumber khong dung dinh dang");
        }

        if(!userValidate.isValidEmail(userDto)){
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.value(), "email nhap khong dung dinh dang example@gmail.com");
        }
        if (userDto.getUserName().isEmpty()) {
            return new  Response(500,"userName khong duoc bo trong");
        }
        if (userDto.getEmail().isEmpty()) {
            return new Response(500,"email khong duoc bo trong");
        }
        if (userDto.getPassword().isEmpty()) {
            return new Response(500, "password khong duoc bo trong");
        }
        if (userDto.getUserPhone().isEmpty()) {
            return new Response(500,"userPhone khong duoc bo trong");

        }



        if(!userDao.create(userModel)){
            return new Response(HttpStatus.NOT_FOUND.value(), "create khong thanh cong");
        }


        Response rs = new Response();
        rs.setCode(200);
        rs.setStatus(1);
        rs.setMessage("create thanh cong");

        return rs;
    }

    @Override
    public Response update(int userId, UserDto userDto) throws MyException {

        UserModel userModel = mapToEntity(userDto);
        if(userId < 0 ){
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.value(), "userId khong ton tai");
        }

        if(!userValidate.isValidUsername(userDto)){
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.value(), "userName nhap khong dung dinh dang ");
        }

        if(!userValidate.isValidPhoneNumber(userDto )){
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.value(), "phoneNumber khong dung dinh dang");
        }

        if(!userValidate.isValidEmail(userDto)){
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.value(), "email nhap khong dung dinh dang example@gmail.com");
        }
        if (userDto.getUserName().isEmpty()) {
            return new  Response(500,"userName khong duoc bo trong");
        }
        if (userDto.getEmail().isEmpty()) {
            return new Response(500,"email khong duoc bo trong");
        }
        if (userDto.getPassword().isEmpty()) {
            return new Response(500, "password khong duoc bo trong");
        }
        if (userDto.getUserPhone().isEmpty()) {
            return new Response(500,"userPhone khong duoc bo trong");

        }

        if(!userDao.update(userId, userModel)){
            return new Response(HttpStatus.NOT_FOUND.value(), "khong tim thay du lieu de update");
        }
        Response rs = new Response();
        rs.setStatus(1);
        rs.setMessage("Update thanh cong");
        rs.setCode(200);

        return rs;
    }
}
