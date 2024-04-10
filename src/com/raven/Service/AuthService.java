package com.raven.Service;

import com.raven.Model2.DangNhap;
import com.raven.dbConnect.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AuthService {
    public DangNhap authenticate(String username, String password) {
        try(Connection conn = DBConnect.getConnection()) {
            String sql = "SELECT * FROM NhanVien WHERE username = ? AND password = ?";
            assert conn != null;
            try(PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setObject(1, username);
                ps.setObject(2, password);
                var rs = ps.executeQuery();
                if (rs.next()) {
                    return new DangNhap(
                            rs.getInt("id"),
                            rs.getString("ten_nv"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("vai_tro"),
                            rs.getString("hinh_anh"),
                            rs.getString("trang_thai")
                    );
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
