package com.example.case_study.daoimpl;

import com.example.case_study.dao.AbstractDao;
import com.example.case_study.dao.ApartmentDao;
import com.example.case_study.dao.BookingDao;
import com.example.case_study.dao.UserDao;
import com.example.case_study.entities.Apartment;
import com.example.case_study.entities.Booking;
import com.example.case_study.entities.User;
import com.example.case_study.exception.MyException;
import com.example.case_study.model.BookingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class BookingDaoImpl extends AbstractDao<Booking> implements BookingDao {

    @Autowired
    DataSource dataSource;
    @Autowired
    @Lazy
    private UserDao userDao;
    @Autowired

    private ApartmentDao apartmentDao;

    public void closeConn(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closePs(PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public List<Booking> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        List<Booking> lst = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM booking";

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                int bookingId = rs.getInt("booking_id");
                int apartmentId = rs.getInt("apartments_id");
                int userId = rs.getInt("users_id");
                String customerName = rs.getString("customer_name");
                String customerPhone = rs.getString("customer_phone");
                Date checkIn = rs.getDate("check_in");
                Date checkOut = rs.getDate("check_out");
                int numberOfGuest = rs.getInt("number_of_guest");
                Date bookingDate = rs.getDate("booking_date");
                int status = rs.getInt("status");
                double totalPrice = rs.getDouble("total_price");
                int statementPay = rs.getInt("statement_pay");

                Booking booking = new Booking();

                booking.setBookingId(bookingId);
                booking.setApartment(apartmentDao.findById(apartmentId));
                booking.setUser(userDao.findById(userId));
                booking.setCustomerName(customerName);
                booking.setCustomerPhone(customerPhone);
                booking.setCheckIn((java.sql.Date) checkIn);
                booking.setCheckOut((java.sql.Date) checkOut);
                booking.setNumberOfGuest(numberOfGuest);
                booking.setBookingDate((java.sql.Date) bookingDate);
                booking.setStatus(status);
                booking.setTotalPrice(totalPrice);
                booking.setStatementPay(statementPay);

                lst.add(booking);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            closeConn(conn);
            closePs(ps, rs);
        }
        return lst;
    }


    @Override
    public List<Booking> findBookingSuccess(int userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        List<Booking> lst = new ArrayList<>();

        ResultSet rs = null;
        String sql = "select b.* from booking as b, apartment as a where not b.users_id = a.users_id " +
                "and b.apartments_id = a.apartment_id and b.status = 1 and b.users_id = ?;" ;


        try{
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()){
                int bookingId = rs.getInt("booking_id");
                int apartmentId = rs.getInt("apartments_id");
                String customerName = rs.getString("customer_name");
                String customerPhone = rs.getString("customer_phone");
                Date checkIn = rs.getDate("check_in");
                Date checkOut = rs.getDate("check_out");
                int numberOfGuest = rs.getInt("number_of_guest");
                Date bookingDate = rs.getDate("booking_date");
                int status = rs.getInt("status");
                double totalPrice = rs.getDouble("total_price");
                int statementPay = rs.getInt("statement_pay");

                Booking booking = new Booking();

                booking.setBookingId(bookingId);
                booking.setApartment(apartmentDao.findById(apartmentId));
                booking.setUser(userDao.findById(userId));
                booking.setCustomerName(customerName);
                booking.setCustomerPhone(customerPhone);
                booking.setCheckIn((java.sql.Date) checkIn);
                booking.setCheckOut((java.sql.Date) checkOut);
                booking.setNumberOfGuest(numberOfGuest);
                booking.setBookingDate((java.sql.Date) bookingDate);
                booking.setStatus(status);
                booking.setTotalPrice(totalPrice);
                booking.setStatementPay(statementPay);

                lst.add(booking);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConn(conn);
            closePs(ps,rs);
        }

        return lst;
    }

    @Override
    public boolean create(BookingModel bookingModel) throws MyException{
        Connection conn = null;
        PreparedStatement ps = null;


        String sql = "INSERT INTO booking (apartments_id, users_id, customer_name, customer_phone, check_in, check_out," +
                "number_of_guest, status, total_price, statement_pay) VALUE (?,?,?,?,?,?,?,?,?,?);";
        boolean result = false;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, bookingModel.getApartment().getApartmentId());
            ps.setInt(2, bookingModel.getUser().getUserId());
            ps.setString(3,bookingModel.getCustomerName());
            ps.setString(4,bookingModel.getCustomerPhone());
            ps.setDate(5, bookingModel.getCheckIn());
            ps.setDate(6, bookingModel.getCheckOut());
            ps.setInt(7,bookingModel.getNumberOfGuest());
            ps.setInt(8,bookingModel.getStatus());
            ps.setDouble(9, bookingModel.getTotalPrice());
            ps.setInt(10, bookingModel.getStatementPay());


            int e  = ps.executeUpdate();
            if(e > 0 ) result = true;

        } catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            closeConn(conn);
            closePs(ps, null);
        }

        return result;
    }

    @Override
    public boolean update(int bookingId, BookingModel bookingModel) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result =false;
        String sql = "UPDATE booking SET apartments_id = ?, users_id = ?, customer_name = ?, customer_phone = ?, check_in = ?," +
                "check_out = ?, number_of_guest = ?, status = ?, total_price = ?, statement_pay = ? WHERE booking_id = ?" ;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, bookingModel.getApartment().getApartmentId());
            ps.setInt(2, bookingModel.getUser().getUserId());
            ps.setString(3,bookingModel.getCustomerName());
            ps.setString(4,bookingModel.getCustomerPhone());
            ps.setDate(5, bookingModel.getCheckIn());
            ps.setDate(6, bookingModel.getCheckOut());
            ps.setInt(7,bookingModel.getNumberOfGuest());
            ps.setInt(8,bookingModel.getStatus());
            ps.setDouble(9, bookingModel.getTotalPrice());
            ps.setInt(10, bookingModel.getStatementPay());
            ps.setInt(11, bookingId);

            int e = ps.executeUpdate();
            if(e > 0) result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            closeConn(conn);
            closePs(ps, null);
        }

        return result;
    }

    @Override
    public Booking findById(int bookingId) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM booking WHERE booking_id = ?";
        ResultSet rs = null;
        Booking booking1 = null;

        try {
            conn = dataSource.getConnection();
            ps =conn.prepareStatement(sql);
            ps.setInt(1, bookingId);
            rs = ps.executeQuery();
            Booking booking = new Booking();
            while (rs.next()){


                int apartmentId = rs.getInt("apartments_id");
                int userId = rs.getInt("users_id");
                String customerName = rs.getString("customer_name");
                String customerPhone = rs.getString("customer_phone");
                Date checkIn = rs.getDate("check_in");
                Date checkOut = rs.getDate("check_out");
                int numberOfGuest = rs.getInt("number_of_guest");
                Date bookingDate = rs.getDate("booking_date");
                int status = rs.getInt("status");
                double totalPrice = rs.getDouble("total_price");
                int statementPay = rs.getInt("statement_pay");


                booking.setBookingId(bookingId);
                booking.setApartment(apartmentDao.findById(apartmentId));
                booking.setUser(userDao.findById(userId));
                booking.setCustomerName(customerName);
                booking.setCustomerPhone(customerPhone);
                booking.setCheckIn((java.sql.Date) checkIn);
                booking.setCheckOut((java.sql.Date) checkOut);
                booking.setNumberOfGuest(numberOfGuest);
                booking.setBookingDate((java.sql.Date) bookingDate);
                booking.setStatus(status);
                booking.setTotalPrice(totalPrice);
                booking.setStatementPay(statementPay);

                return booking;

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            closeConn(conn);
            closePs(ps, rs);
        }

        return booking1;
    }

    @Override
    public List<Booking> findBookingByApartmentId(int apartmentId) {
        Connection conn = null;
        PreparedStatement ps = null;
        List<Booking> lst = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM booking WHERE apartments_id =? ";

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, apartmentId);
            rs = ps.executeQuery();

            while (rs.next()){
                int bookingId = rs.getInt("booking_id");
                int userId = rs.getInt("users_id");
                String customerName = rs.getString("customer_name");
                String customerPhone = rs.getString("customer_phone");
                Date checkIn = rs.getDate("check_in");
                Date checkOut = rs.getDate("check_out");
                int numberOfGuest = rs.getInt("number_of_guest");
                Date bookingDate = rs.getDate("booking_date");
                int status = rs.getInt("status");
                double totalPrice = rs.getDouble("total_price");
                int statementPay = rs.getInt("statement_pay");


                Booking booking = new Booking();

                booking.setBookingId(bookingId);
                booking.setApartment(apartmentDao.findById(userId));
                booking.setUser(userDao.findById(userId));
                booking.setCustomerName(customerName);
                booking.setCustomerPhone(customerPhone);
                booking.setCheckIn((java.sql.Date) checkIn);
                booking.setCheckOut((java.sql.Date) checkOut);
                booking.setNumberOfGuest(numberOfGuest);
                booking.setBookingDate((java.sql.Date) bookingDate);
                booking.setStatus(status);
                booking.setTotalPrice(totalPrice);
                booking.setStatementPay(statementPay);

                lst.add(booking);

            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            closeConn(conn);
            closePs(ps, rs);
        }

        return lst;
    }


    @Override
    public boolean create(Booking booking) {
        return false;
    }


    @Override
    public boolean update(Booking booking) {
        return false;
    }
}
