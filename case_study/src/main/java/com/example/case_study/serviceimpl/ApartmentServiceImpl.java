package com.example.case_study.serviceimpl;

import com.example.case_study.dao.ApartmentDao;
import com.example.case_study.dao.UserDao;
import com.example.case_study.entities.Apartment;
import com.example.case_study.entities.User;
import com.example.case_study.exception.MyException;
import com.example.case_study.dto.ApartmentDto;
import com.example.case_study.model.ApartmentModel;
import com.example.case_study.response.ApartmentRp;
import com.example.case_study.response.Response;
import com.example.case_study.service.ApartmentService;

import com.example.case_study.validate.ApartmentValidate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    private ApartmentDao apartmentDao;

    @Autowired
    private UserDao userDao;
    private ModelMapper mapper;

    public ApartmentServiceImpl(ApartmentDao apartmentDao, ModelMapper mapper) {
        this.apartmentDao = apartmentDao;
        this.mapper = mapper;
    }

    private ApartmentDto mapToDto(ApartmentModel apartmentModel) {
        ApartmentDto apartmentDto = mapper.map(apartmentModel, ApartmentDto.class);
        return apartmentDto;
    }

    private ApartmentModel mapToEntity(ApartmentDto apartmentDto) {
        ApartmentModel apartmentModel = mapper.map(apartmentDto, ApartmentModel.class);
        return apartmentModel;
    }


    @Override
    public Response findById(int apartmentId) throws MyException {

        if (apartmentId < 0) {
            return new Response(500, "khong ton tai apartmentId");
        }

        Apartment apartment = apartmentDao.findById(apartmentId);

        if (apartment == null) {
            return new Response(HttpStatus.NOT_FOUND.value(), "khong tim thay apartment");
        }
    //        User user = new User();
    //        int userId = user.getUserId();
    //        apartment.setUser(userDao.findById(userId));
       //User user = userDao.findById(apartment.getUser().getUserId());

        ApartmentRp apartmentRp = new ApartmentRp();
        apartmentRp.setApartmentId(apartment.getApartmentId());
        apartmentRp.setApartmentName(apartment.getApartmentName());
        apartmentRp.setCapacity(apartment.getCapacity());
        apartmentRp.setArea(apartment.getArea());
        apartmentRp.setAddress(apartment.getAddress());
        apartmentRp.setPrice(apartment.getPrice());
        apartmentRp.setMinDay(apartment.getMinDay());
        apartmentRp.setMaxDay(apartment.getMaxDay());
        apartmentRp.setUser(apartment.getUser());

        Response rp = new Response();
        rp.setStatus(1);
        rp.setMessage("tim kiem thanh cong");
        rp.setCode(200);
        rp.setData(apartmentRp);

        return rp;
    }

    ApartmentValidate apartmentValidate = new ApartmentValidate();

    @Override
    public Response create(ApartmentDto apartmentDto) throws MyException {
        ApartmentModel apartmentModel = mapToEntity(apartmentDto);

        if (!apartmentValidate.isValidApartmentName(apartmentDto)) {
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.value(), "apartmentName khong hop le");
        }

        if (!apartmentValidate.check(apartmentDto)) {
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.value(), "capacity, area, address, price nhap vao sai");
        }

        if (!apartmentValidate.check1(apartmentDto)) {
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.value(), "maxDay khong duoc nho hon minDay");
        }

        if (apartmentDto.getApartmentName().isEmpty()) {
            return new Response(500,"Ten can ho khong duoc bo trong");
        }
        if (apartmentDto.getAddress().isEmpty()) {
            return new Response(500, "Dia chi khong duoc bo trong");
        }
        if (apartmentDto.getArea() < 0) {
            return new Response(500, "Area khong nho hon 0");
        }
        if (apartmentDto.getCapacity() < 0) {
            return new Response(500,"Capacity khong nho hon 0");
        }
        if (apartmentDto.getMinDay() > apartmentDto.getMaxDay()) {
            return new Response(500, "maxDay phai lon hon minDay");
        }

        if (!apartmentDao.create(apartmentModel)) {
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.value(), "create khong thanh cong");
        }


        Response rs = new Response();
        rs.setCode(200);
        rs.setStatus(1);
        rs.setMessage("create thanh cong");

        return rs;
    }

    @Override
    public Response update(int apartmentId, ApartmentDto apartmentDto) throws MyException{
        ApartmentModel apartmentModel = mapToEntity(apartmentDto);

        if (apartmentId < 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), "apartmentId khong ton tai");
        }

        if (!apartmentValidate.isValidApartmentName(apartmentDto)) {
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.value(), "apartmentName khong hop le hoac khong duoc bo trong");
        }

        if (!apartmentValidate.check(apartmentDto)) {
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.value(), "capacity, area, address, price nhap vao sai hoac khong duoc bo trong");
        }

        if (apartmentDto.getApartmentName().isEmpty()) {
            return new Response(500,"Ten can ho khong duoc bo trong");
        }
        if (apartmentDto.getAddress().isEmpty()) {
            return new Response(500, "Dia chi khong duoc bo trong");
        }
        if (apartmentDto.getArea() < 0) {
            return new Response(500, "Area khong nho hon 0");
        }
        if (apartmentDto.getCapacity() < 0) {
            return new Response(500,"Capacity khong nho hon 0");
        }
        if (apartmentDto.getMinDay() > apartmentDto.getMaxDay()) {
            return new Response(500, "maxDay phai lon hon minDay");
        }
        if (!apartmentDao.update(apartmentId, apartmentModel)) {
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.value(), "khong tim thay du lieu de update");
        }
        Response rs = new Response();
        rs.setStatus(1);
        rs.setMessage("Update thanh cong");
        rs.setCode(200);

        return rs;
    }

    @Override
    public Response findAll() throws MyException {
        Response rs = new Response();
        rs.setStatus(1);
        rs.setMessage("OK");
        rs.setCode(200);
        List<ApartmentRp> lst = new ArrayList<>();
        for (Apartment apartment : apartmentDao.findAll()) {
            lst.add(new ApartmentRp(apartment.getApartmentId(),
                    apartment.getApartmentName(), apartment.getCapacity(), apartment.getArea(),
                    apartment.getAddress(), apartment.getPrice(), apartment.getMinDay(), apartment.getMaxDay(),
                    apartment.getUser()));

        }
        rs.setData(lst);
        return rs;
    }

    @Override
    public Response findApartmentByUser(int userId) throws MyException {// tim danh sach theo userId
        if (userId < 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), "userId khong ton tai");
        }
        List<Apartment> apartment = apartmentDao.findApartmentByUser(userId);
        if (apartment.isEmpty()){
            return new Response(HttpStatus.NOT_FOUND.value(), "khong tim thay apartment theo user");
        }

        Response rs = new Response();
        rs.setMessage("OK");
        rs.setCode(200);
        rs.setStatus(1);
        List<ApartmentRp> lst = new ArrayList<>();
        for (Apartment a : apartmentDao.findApartmentByUser(userId)) {
            lst.add(new ApartmentRp(a.getApartmentId(), a.getApartmentName(), a.getCapacity(),
                    a.getArea(), a.getAddress(), a.getPrice(), a.getMinDay(),
                    a.getMaxDay(), a.getUser()));
        }
        rs.setData(lst);
        return rs;
    }

    @Override
        public Response apartmentIsBooking(int userId) throws MyException { //list
        if (userId < 0) {
            return new Response(HttpStatus.NOT_FOUND.value(), "userId khong ton tai");
        }
        List<Apartment> apartment = apartmentDao.apartmentIsBooking(userId);
        if (apartment.isEmpty()){
            return new Response(HttpStatus.NOT_FOUND.value(), "khong tim thay apartment dang duoc cho thue");
        }

        Response rs = new Response();
        rs.setMessage("OK");
        rs.setCode(200);
        rs.setStatus(1);
        List<ApartmentRp> lst = new ArrayList<>();
        for (Apartment a : apartmentDao.apartmentIsBooking(userId)) {
            lst.add(new ApartmentRp(a.getApartmentId(), a.getApartmentName(), a.getCapacity(),
                    a.getArea(), a.getAddress(), a.getPrice(), a.getMinDay(),
                    a.getMaxDay(), a.getUser()));
        }
        rs.setData(lst);
        return rs;
    }

    @Override
    public Response search(String area, String capacity, String price1, String price2) throws MyException {//list

        List<Apartment> apartment = apartmentDao.search(area, capacity,price1,price2);

        if(apartment.isEmpty()){
            return new Response(HttpStatus.NOT_FOUND.value(), "khong tim thay ket qua theo yeu cau tim kiem");
        }

        Response rs = new Response();
        rs.setMessage("OK");
        rs.setCode(200);
        rs.setStatus(1);
        List<ApartmentRp> lst = new ArrayList<>();
        for (Apartment a : apartmentDao.search(area, capacity, price1, price2)) {
            lst.add(new ApartmentRp(a.getApartmentId(), a.getApartmentName(), a.getCapacity(),
                    a.getArea(), a.getAddress(), a.getPrice(), a.getMinDay(),
                    a.getMaxDay(), a.getUser()));
        }
        rs.setData(lst);
        return rs;
    }
}
