package com.example.baitapv2.dao;

import com.example.baitapv2.entities.SinhVien;
import com.example.baitapv2.exception.MyException;
import com.example.baitapv2.model.SinhVienRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SinhVienDaoImpl implements SinhVienDao {
    @Autowired
    DataSource dataSource;

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
    public boolean insert(SinhVienRq sinhVienRq) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO sinh_vien (ten_sv, dia_chi, diem_tong) VALUE (?,?,?)";
        boolean result = false;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, sinhVienRq.getTenSv());
            ps.setString(2, sinhVienRq.getDiaChi());
            ps.setInt(3, sinhVienRq.getDiemTong());
            int ex = ps.executeUpdate();
            if (ex > 0) result = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn(con);
            closePs(ps, null);
        }

        return result;
    }

    @Override
    public boolean updateSv(int maSv, SinhVienRq sinhVienRq) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        String sql = "UPDATE sinh_vien SET ten_sv = ?, dia_chi = ?, diem_tong = ? WHERE ma_sv=?";

        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, sinhVienRq.getTenSv());
            ps.setString(2, sinhVienRq.getDiaChi());
            ps.setInt(3, sinhVienRq.getDiemTong());
            ps.setInt(4, maSv);

            int ex = ps.executeUpdate();
            if (ex > 0) result = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            closeConn(con);
            closePs(ps, null);
        }
        return result;
    }

    @Override
    public boolean delete(int maSv) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        String sql = "DELETE FROM sinh_vien WHERE ma_sv = ?";

        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, maSv);

            int ex = ps.executeUpdate();
            if (ex > 0) result = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            closeConn(con);
            closePs(ps, null);
        }
        return result;
    }

    @Override
    public List<SinhVien> list() {
        List<SinhVien> lst = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM sinh_vien ORDER BY diem_tong DESC LIMIT 3";

        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer maSv = rs.getInt("ma_sv");
                String tenSv = rs.getString("ten_sv");
                String diaChi = rs.getString("dia_chi");
                Integer diemTong = rs.getInt("diem_tong");

                SinhVien sinhVien = new SinhVien();
                sinhVien.setMaSv(maSv);
                sinhVien.setTenSv(tenSv);
                sinhVien.setDiaChi(diaChi);
                sinhVien.setDiemTong(diemTong);

                lst.add(sinhVien);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            closeConn(con);
            closePs(ps, rs);
        }
        return lst;
    }

    @Override
    public SinhVien findById(int maSv) {
        SinhVien sinhVien1 = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM sinh_vien WHERE ma_sv =" + maSv;

        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            SinhVien sinhVien = new SinhVien();
            while (rs.next()) {

                String tenSv = rs.getString("ten_sv");
                String diaChi = rs.getString("dia_chi");
                Integer diemTong = rs.getInt("diem_tong");

                sinhVien.setMaSv(maSv);
                sinhVien.setTenSv(tenSv);
                sinhVien.setDiaChi(diaChi);
                sinhVien.setDiemTong(diemTong);
                return sinhVien;
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }
        finally {
           closeConn(con);
           closePs(ps, rs);
        }
        return sinhVien1;

    }

}
