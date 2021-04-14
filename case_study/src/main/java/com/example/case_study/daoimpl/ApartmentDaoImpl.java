package com.example.case_study.daoimpl;

import com.example.case_study.dao.AbstractDao;
import com.example.case_study.dao.ApartmentDao;
import com.example.case_study.dao.UserDao;
import com.example.case_study.entities.Apartment;


import com.example.case_study.entities.Booking;
import com.example.case_study.exception.MyException;
import com.example.case_study.model.ApartmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ApartmentDaoImpl extends AbstractDao<Apartment> implements ApartmentDao {
    @Autowired
    DataSource dataSource;
    @Autowired
    @Lazy
    private UserDao userDao;

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
    public boolean update(int apartmentId, ApartmentModel apartmentModel) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        String sql = "UPDATE apartment SET users_id =?, apartment_name = ?, capacity = ?, area = ?,address = ?, price = ?, " +
                "min_day = ?, max_day = ? WHERE apartment_id = ?";

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, apartmentModel.getUserId());
            ps.setString(2, apartmentModel.getApartmentName());
            ps.setInt(3, apartmentModel.getCapacity());
            ps.setInt(4, apartmentModel.getArea());
            ps.setString(5, apartmentModel.getAddress());
            ps.setDouble(6, apartmentModel.getPrice());
            ps.setInt(7, apartmentModel.getMinDay());
            ps.setInt(8, apartmentModel.getMaxDay());
            ps.setInt(9, apartmentId);


            int e = ps.executeUpdate();
            if (e > 0) result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConn(conn);
            closePs(ps, null);
        }

        return result;
    }

    public Apartment findById(int apartmentId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM apartment WHERE apartment_id = ?";
        Apartment apartment1 = null;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, apartmentId);
            rs = ps.executeQuery();
            Apartment apartment = new Apartment();

            while (rs.next()) {
                int userId = rs.getInt("users_id");
                String apartmentName = rs.getString("apartment_name");
                int capacity = rs.getInt("capacity");
                int area = rs.getInt("area");
                String address = rs.getString("address");
                double price = rs.getDouble("price");
                int minDay = rs.getInt("min_day");
                int maxDay = rs.getInt("max_day");

                apartment.setApartmentId(apartmentId);
                apartment.setUser(userDao.findById(userId));
                apartment.setApartmentName(apartmentName);
                apartment.setCapacity(capacity);
                apartment.setArea(area);
                apartment.setAddress(address);
                apartment.setPrice(price);
                apartment.setMinDay(minDay);
                apartment.setMaxDay(maxDay);

                return apartment;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConn(conn);
            closePs(ps, rs);
        }

        return apartment1;
    }


    @Override
    public List<Apartment> findAll() {

        List<Apartment> lst = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * From apartment";

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int apartmentId = rs.getInt("apartment_id");
                int userId = rs.getInt("users_id");
                String apartmentName = rs.getString("apartment_name");
                int capacity = rs.getInt("capacity");
                int area = rs.getInt("area");
                String address = rs.getString("address");
                double price = rs.getDouble("price");
                int minDay = rs.getInt("min_day");
                int maxDay = rs.getInt("max_day");

                Apartment apartment = new Apartment();

                apartment.setApartmentId(apartmentId);
                apartment.setUser(userDao.findById(userId));
                apartment.setApartmentName(apartmentName);
                apartment.setCapacity(capacity);
                apartment.setArea(area);
                apartment.setAddress(address);
                apartment.setPrice(price);
                apartment.setMinDay(minDay);
                apartment.setMaxDay(maxDay);

                lst.add(apartment);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConn(conn);
            closePs(ps, rs);
        }
        return lst;
    }

    @Override
    public boolean update(Apartment apartment) {
        return false;
    }

    @Override
    public boolean create(ApartmentModel apartmentModel) throws MyException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO apartment (apartment_name, capacity, area," +
                "address, price, min_day, max_day, users_id) VALUE (?,?,?,?,?,?,?,?)";
        boolean result = false;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, apartmentModel.getApartmentName());
            ps.setInt(2, apartmentModel.getCapacity());
            ps.setInt(3, apartmentModel.getArea());
            ps.setString(4, apartmentModel.getAddress());
            ps.setDouble(5, apartmentModel.getPrice());
            ps.setInt(6, apartmentModel.getMinDay());
            ps.setInt(7, apartmentModel.getMaxDay());
            ps.setInt(8, apartmentModel.getUserId());


            int e = ps.executeUpdate();
            if (e > 0) result = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConn(conn);
            closePs(ps, null);
        }

        return result;
    }


    @Override
    public List<Apartment> findApartmentByUser(int userId) throws MyException {
        Connection conn = null;
        PreparedStatement ps = null;
        List<Apartment> lst = new ArrayList<>();

        ResultSet rs = null;
        String sql = "SELECT * FROM apartment WHERE users_id =?";
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {

                int apartmentId = rs.getInt("apartment_id");
                String apartmentName = rs.getString("apartment_name");
                int capacity = rs.getInt("capacity");
                int area = rs.getInt("area");
                String address = rs.getString("address");
                double price = rs.getDouble("price");
                int minDay = rs.getInt("min_day");
                int maxDay = rs.getInt("max_day");

                Apartment apartment = new Apartment();
                apartment.setApartmentId(apartmentId);
                apartment.setUser(userDao.findById(userId));
                apartment.setApartmentName(apartmentName);
                apartment.setCapacity(capacity);
                apartment.setArea(area);
                apartment.setAddress(address);
                apartment.setPrice(price);
                apartment.setMinDay(minDay);
                apartment.setMaxDay(maxDay);

                lst.add(apartment);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConn(conn);
            closePs(ps, rs);
        }
        return lst;
    }

    @Override
    public List<Apartment> apartmentIsBooking(int userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        List<Apartment> lst = new ArrayList<>();

        ResultSet rs = null;
        String sql = "SELECT distinct a.*" +
                " FROM apartment as a, booking as b" +
                " WHERE a.apartment_id = b.apartments_id " +
                " AND status = 1 " +
                " AND a.users_id = ?;";

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                int apartmentId = rs.getInt("apartment_id");
                String apartmentName = rs.getString("apartment_name");
                int capacity = rs.getInt("capacity");
                int area = rs.getInt("area");
                String address = rs.getString("address");
                double price = rs.getDouble("price");
                int minDay = rs.getInt("min_day");
                int maxDay = rs.getInt("max_day");

                Apartment apartment = new Apartment();

                apartment.setApartmentId(apartmentId);
                apartment.setUser(userDao.findById(userId));
                apartment.setApartmentName(apartmentName);
                apartment.setCapacity(capacity);
                apartment.setArea(area);
                apartment.setAddress(address);
                apartment.setPrice(price);
                apartment.setMinDay(minDay);
                apartment.setMaxDay(maxDay);

                lst.add(apartment);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lst;
    }

    @Override
    public List<Apartment> search(String area, String capacity, String price1, String price2) {
        Connection conn = null;
        PreparedStatement ps = null;
        List<Apartment> lst = new ArrayList<>();

        ResultSet rs = null;
        String sql = "SELECT * FROM apartment WHERE 1=1";

        if (!area.isEmpty()) {
            sql += " and area = ?";
        }
        if (!capacity.isEmpty()) {
            sql += " and capacity =?";
        }
        if (!price1.isEmpty() && !price2.isEmpty()) {
            sql += " and price >= ? and price <= ?";
        }

        try {

            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);

            if (!area.isEmpty()&& !capacity.isEmpty()&& !price1.isEmpty() && !price2.isEmpty()) {
                ps.setInt(1, Integer.parseInt(area));
                ps.setInt(2, Integer.parseInt(capacity));
                ps.setDouble(3, Double.parseDouble(price1));
                ps.setDouble(4, Double.parseDouble(price2));
            }
            if (!area.isEmpty()&& !capacity.isEmpty()&& price1.isEmpty() && price2.isEmpty()) {
                ps.setInt(1, Integer.parseInt(area));
                ps.setInt(2, Integer.parseInt(capacity));

            }
            if (!area.isEmpty()&& capacity.isEmpty()&& !price1.isEmpty() && !price2.isEmpty()) {
                ps.setInt(1, Integer.parseInt(area));

                ps.setDouble(2, Double.parseDouble(price1));
                ps.setDouble(3, Double.parseDouble(price2));
            }
            if (!area.isEmpty()&& capacity.isEmpty()&& price1.isEmpty() && price2.isEmpty()) {
                ps.setInt(1, Integer.parseInt(area));

            }
            if (area.isEmpty()&& !capacity.isEmpty()&& !price1.isEmpty() && !price2.isEmpty()) {
                ps.setInt(1, Integer.parseInt(capacity));
                ps.setDouble(2, Double.parseDouble(price1));
                ps.setDouble(3, Double.parseDouble(price2));
            }

            if (area.isEmpty()&& !capacity.isEmpty()&& price1.isEmpty() && price2.isEmpty()) {
                ps.setInt(1, Integer.parseInt(capacity));
            }

            if (area.isEmpty()&& capacity.isEmpty()&& !price1.isEmpty() && !price2.isEmpty()) {
                ps.setDouble(1, Double.parseDouble(price1));
                ps.setDouble(2, Double.parseDouble(price2));
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                int apartmentId = rs.getInt("apartment_id");
                int userId = rs.getInt("users_id");
                String apartmentName = rs.getString("apartment_name");
                int capacity1 = rs.getInt("capacity");
                int area1 = rs.getInt("area");
                String address = rs.getString("address");
                double price = rs.getDouble("price");
                int minDay = rs.getInt("min_day");
                int maxDay = rs.getInt("max_day");

                Apartment apartment = new Apartment();
                apartment.setApartmentId(apartmentId);
                apartment.setUser(userDao.findById(userId));
                apartment.setApartmentName(apartmentName);
                apartment.setCapacity(capacity1);
                apartment.setArea(area1);
                apartment.setAddress(address);
                apartment.setPrice(price);
                apartment.setMinDay(minDay);
                apartment.setMaxDay(maxDay);

                lst.add(apartment);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConn(conn);
            closePs(ps, rs);
        }
        return lst;
    }

    @Override
    public boolean create(Apartment apartment) {
        return false;
    }


}
