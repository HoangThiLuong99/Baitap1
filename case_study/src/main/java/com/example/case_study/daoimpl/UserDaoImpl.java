package com.example.case_study.daoimpl;

import com.example.case_study.dao.AbstractDao;
import com.example.case_study.dao.ApartmentDao;
import com.example.case_study.dao.BookingDao;
import com.example.case_study.dao.UserDao;
import com.example.case_study.entities.User;
import com.example.case_study.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Autowired
    DataSource dataSource;
    @Autowired
    private BookingDao bookingDao;
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
    public User findById(int userId)  {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM user WHERE user_id = ?";
        ResultSet rs = null;
        User user1 = null;
        try {
            conn = dataSource.getConnection();
            ps =conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            User user = new User();
            while (rs.next()){

                String userName = rs.getString("user_name");
                String userPhone = rs.getString("user_phone");
                String email = rs.getString("email");
                String password = rs.getString("password");


                user.setUserId(userId);
                user.setUserName(userName);
                user.setUserPhone(userPhone);
                user.setEmail(email);
                user.setPassword(password);

                return user;

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            closeConn(conn);
            closePs(ps, rs);
        }

        return user1;
    }

    @Override
    public List<User> findAll(){
        List<User> lst = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * From user";

        try{
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int userId = rs.getInt("user_id");
                String userName = rs.getString("user_name");
                String userPhone = rs.getString("user_phone");
                String email = rs.getString("email");
                String password = rs.getString("password");

                User user = new User();

                user.setUserId(userId);
                user.setUserName(userName);
                user.setUserPhone(userPhone);
                user.setEmail(email);
                user.setPassword(password);

                lst.add(user);
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
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean create(User user) {
        return false;
    }




    @Override
    public boolean create(UserModel userModel){
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO user (user_name, user_phone, email, password) VALUE (?,?,?,?)";
        boolean result = false;

        try{
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userModel.getUserName());
            ps.setString(2, userModel.getUserPhone());
            ps.setString(3, userModel.getEmail());
            ps.setString(4, userModel.getPassword());

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
    public boolean update(int userId,  UserModel user){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result =false;
        String sql = "UPDATE user SET user_name = ?, user_phone = ?, email = ?, password = ? WHERE user_id = ?";

        try{
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getUserPhone());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setInt(5, userId);

            int e = ps.executeUpdate();
            if(e > 0) result = true;

        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            closeConn(conn);
            closePs(ps, null);
        }

        return result;
    }

}
