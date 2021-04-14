package com.example.case_study.serviceimpl;


import com.example.case_study.common.Const;
import com.example.case_study.dao.ApartmentDao;
import com.example.case_study.dao.BookingDao;
import com.example.case_study.dao.UserDao;
import com.example.case_study.dto.BookingDto;

import com.example.case_study.entities.Apartment;
import com.example.case_study.entities.Booking;
import com.example.case_study.exception.MyException;
import com.example.case_study.model.BookingModel;

import com.example.case_study.response.BookingRp;
import com.example.case_study.response.Response;
import com.example.case_study.service.BookingService;
import com.example.case_study.validate.BookingValidate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private ApartmentDao apartmentDao;

    @Autowired
    private UserDao userDao;

    private ModelMapper mapper;
    public BookingServiceImpl(BookingDao bookingDao, ModelMapper mapper) {
        this.bookingDao = bookingDao;
        this.mapper = mapper;
    }

    private BookingDto mapToDto(BookingModel bookingModel) {
        BookingDto bookingDto = mapper.map(bookingModel, BookingDto.class);
        return bookingDto;
    }

    private BookingModel mapToEntity(BookingDto bookingDto) {
        BookingModel bookingModel = mapper.map(bookingDto, BookingModel.class);
        return bookingModel;
    }
    @Override
    public Response findBookingByApartmentId(int apartmentId)throws MyException {
        if (apartmentId < 0) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "apartmentId khong ton tai");
        }

        Response rs = new Response();
        rs.setMessage("OK");
        rs.setCode(200);
        rs.setStatus(1);
        List<BookingRp> lst = new ArrayList<>();
        for (Booking booking : bookingDao.findBookingByApartmentId(apartmentId)) {
            lst.add(new BookingRp(booking.getBookingId(), booking.getCustomerName(), booking.getCustomerPhone(),
                    booking.getCheckIn(), booking.getCheckOut(), booking.getNumberOfGuest(), booking.getBookingDate(),
                    booking.getStatus(), booking.getTotalPrice(), booking.getStatementPay(),
                    booking.getUser(), booking.getApartment()));
        }
        rs.setData(lst);
        return rs;
    }

    @Override
    public Response findAll() throws MyException{
        Response rs = new Response();
        rs.setStatus(1);
        rs.setMessage("OK");
        rs.setCode(200);
        List<BookingRp> lst = new ArrayList<>();
        for (Booking booking : bookingDao.findAll()) {
            lst.add(new BookingRp(booking.getBookingId(), booking.getCustomerName(), booking.getCustomerPhone(),
                    booking.getCheckIn(), booking.getCheckOut(), booking.getNumberOfGuest(), booking.getBookingDate(),
                    booking.getStatus(), booking.getTotalPrice(), booking.getStatementPay(),
                    booking.getUser(), booking.getApartment()));

        }
        rs.setData(lst);
        return rs;
    }


    @Override
    public Response findBookingSuccess(int userId) throws MyException{
        if(userId < 0){
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "userId sai");
        }
        List<Booking> bookings = bookingDao.findBookingSuccess(userId);
        if(bookings.isEmpty()){
            return new Response(HttpStatus.NOT_FOUND.value(), "khong tim thay cac booking thanh cong");
        }
        Response rs = new Response();
        rs.setStatus(1);
        rs.setMessage("OK");
        rs.setCode(200);
        List<BookingRp> lst = new ArrayList<>();
        for (Booking booking : bookingDao.findBookingSuccess(userId)) {
            lst.add(new BookingRp(booking.getBookingId(), booking.getCustomerName(), booking.getCustomerPhone(),
                    booking.getCheckIn(), booking.getCheckOut(), booking.getNumberOfGuest(), booking.getBookingDate(),
                    booking.getStatus(), booking.getTotalPrice(), booking.getStatementPay(),
                    booking.getUser(), booking.getApartment()));

        }
        rs.setData(lst);
        return rs;
    }
    @Override
    public Response findById(int bookingId) throws MyException{
        if(bookingId <0){
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "khong ton tai bookinhId");
        }

        Booking booking = bookingDao.findById(bookingId);
        if(booking==null){
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "khong tim thay booking theo id vua nhap");
        }
        BookingRp bookingRp = new BookingRp();
        bookingRp.setBookingId(booking.getBookingId());
        bookingRp.setCustomerName(booking.getCustomerName());
        bookingRp.setCustomerPhone(booking.getCustomerPhone());
        bookingRp.setCheckIn(booking.getCheckIn());
        bookingRp.setCheckOut(booking.getCheckOut());
        bookingRp.setNumberOfGuest(booking.getNumberOfGuest());
        bookingRp.setBooingDate(booking.getBookingDate());
        bookingRp.setStatus(booking.getStatus());
        bookingRp.setTotalPrice(booking.getTotalPrice());
        bookingRp.setStatementPay(booking.getStatementPay());
        bookingRp.setApartment(booking.getApartment());
        bookingRp.setUser(booking.getUser());
        Response rp = new Response();
        rp.setStatus(1);
        rp.setMessage("tim kiem thanh cong");
        rp.setCode(200);
        rp.setData(bookingRp);


        return rp;
    }

    public boolean check1(int id){ //check chu can ho
        List<Apartment> lst = apartmentDao.findAll();
        for (Apartment apartment: lst) {
            if(apartment.getUser().getUserId() == id){
                return true;
            }
        }
        return false;
    }

    public boolean check2(BookingDto bookingDto) throws MyException{// kiem tra trung
        List<Booking> lst = bookingDao.findBookingByApartmentId(bookingDto.getApartmentId());
            long date2 = bookingDto.getCheckOut().getTime();
        for (Booking booking : lst) {

            long date1 = bookingDto.getCheckIn().getTime();
            long numberDay = date2 - date1; //h cua ngay checkout -h của ngày checkin >=0 không trùng
            if(numberDay>=0){
                return false;
            }

        }
        return true;
    }


    BookingValidate bookingValidate = new BookingValidate();
    @Override
    public Response create(BookingDto bookingDto) throws MyException {
        BookingModel bookingModel = mapToEntity(bookingDto);
        if(!check1(bookingDto.getUserId())){
            return new Response(500, "chu can ho khong duoc thue can ho cua minh");
        }

        if(!check2(bookingDto)){
            return new Response(500, "da co nguoi dat");
        }

        if(!bookingValidate.isValidUsername(bookingDto)){
            return new Response(500, "customerName khong hop le");
        }
        if(!bookingValidate.isValidPhoneNumber(bookingDto)){
            return new Response(500, "customerPhone khong dung dinh dang");
        }
        int capacity = apartmentDao.findById(bookingModel.getApartment().getApartmentId()).getCapacity();
        if (bookingDto.getNumberOfGuest() > capacity) {
            return new Response(500, "So nguo luu tru phai phu hop voi suc chua");
        }

        if(bookingModel.getStatementPay() == 0){
            bookingModel.setStatus(Const.dang_trong_qua_trinh_cho_thanh_toan); //dang trong qua trinh thanh toan
        }
        if(bookingModel.getStatementPay() == 1){
            bookingModel.setStatus(Const.da_dat_thanh_cong); //da dat thanh cong
        }
        long numberDay = bookingDto.getCheckOut().getTime() - bookingDto.getCheckIn().getTime();
        numberDay = numberDay/(1000*60*60*24);

        double price = apartmentDao.findById(bookingModel.getApartment().getApartmentId()).getPrice();
        if(numberDay == 0){
            bookingModel.setTotalPrice(price);
        }
        bookingModel.setTotalPrice(numberDay*price);

        if(!bookingDao.create(bookingModel)){
            return new Response(500, "create khong thanh cong");
        }
        Response rs = new Response();
        rs.setCode(200);
        rs.setStatus(1);
        rs.setMessage("create thanh cong");

        return rs;

    }

    @Override
    public Response update(int bookingId, BookingDto bookingDto) throws MyException {
        BookingModel bookingModel = mapToEntity(bookingDto);

        if(!check1(bookingDto.getUserId())){
            return new Response(500, "chu can ho khong duoc thue can ho cua minh");
        }

        if(!check2(bookingDto)){
            return new Response(500, "da co nguoi dat");
        }

        if(!bookingValidate.isValidUsername(bookingDto)){
            return new Response(500, "customerName khong hop le hoac khong duoc bo trong");
        }
        if(!bookingValidate.isValidPhoneNumber(bookingDto)){
            return new Response(500, "customerPhone khong dung dinh dang hoac khong duoc bo trong");
        }
        int capacity = apartmentDao.findById(bookingModel.getApartment().getApartmentId()).getCapacity();
        if (bookingDto.getNumberOfGuest() > capacity) {
            return new Response(500, "So nguo luu tru phai phu hop voi suc chua");
        }


        Booking booking = bookingDao.findById(bookingId);

        long date1 = System.currentTimeMillis();
        long numberDay = date1 - booking.getBookingDate().getTime();
        numberDay = numberDay/(1000*60*60*24);
        if(booking.getStatementPay() == 0){
            if(numberDay >= 1){
                booking.setStatus(Const.bi_huy_do_chua_thanh_toan); //bi huy do chua thanh toan
            }else booking.setStatus(Const.dang_trong_qua_trinh_cho_thanh_toan); //dang trong qua trinh cho thanh toan
        }

        if (booking.getStatementPay() == 1){
            booking.setStatus(1); //da dat thanh cong
        }

        if(!bookingDao.update(bookingId, bookingModel)){
            return new Response(500, "update khong thanh cong");
        }

        Response rs = new Response();
        rs.setCode(200);
        rs.setStatus(1);
        rs.setMessage("update thanh cong");

        return rs;
    }



}
