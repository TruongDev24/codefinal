/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.raven.Model2.khachHang;
import java.sql.*;
import com.raven.dbConnect.DBConnect;
/**
 *
 * @author PC
 */
public class khachHang_Service {
    Connection con = DBConnect.getConnection();

    public List<khachHang> getAll_KH() {
        String sql = "select*from khachHang";
        try (PreparedStatement prs = con.prepareStatement(sql)) {
            ResultSet rs = prs.executeQuery();
            List<khachHang> ppp = new ArrayList<>();
            while (rs.next()) {
                khachHang kh = new khachHang(
                        rs.getString(1),
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getString(4),
                        rs.getInt(5), 
                        rs.getString(6));
                ppp.add(kh);
            }
            return ppp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean add(khachHang kh) {
        String sql = "insert into KhachHang(ten_khach,sdt,ngay_them,gioi_tinh,trang_thai)Values(?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, kh.getTen_khach());
            ps.setObject(2, kh.getSdt());
            ps.setObject(3, kh.getNgay_them());
            ps.setObject(4, kh.getGioi_tinh());
            ps.setObject(5, kh.getTrang_thai());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateStatus(int Id, String newStatus) {
        int check = 0;
        String sql = "update KhachHang set trang_thai=? where id=?";
        try (PreparedStatement prs = con.prepareStatement(sql)) {
            prs.setObject(1, newStatus);
            prs.setObject(2, Id);
            check = prs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    public boolean update(khachHang kh) {
        int check = 0;
        String sql = "Update KhachHang set ten_khach=?,sdt=?,ngay_them=?,gioi_tinh=?,trang_thai=? where id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, kh.getTen_khach());
            ps.setObject(2, kh.getSdt());
            ps.setObject(3, kh.getNgay_them());
            ps.setObject(4, kh.getGioi_tinh());
            ps.setObject(5, kh.getTrang_thai());
            ps.setObject(6, kh.getId());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean delete(int Id) {
        int check = 0;
        String sql = "delete from KhachHang where id=?";
        try (PreparedStatement prs = con.prepareStatement(sql)) {
            prs.setObject(1, Id);
            check = prs.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return check > 0;
    }
    public List<khachHang> timKiemKhachHang(String keyword) {
    String sql = "SELECT * FROM KhachHang WHERE ten_khach LIKE ? OR sdt LIKE ?";
    List<khachHang> resultList = new ArrayList<>();
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            khachHang kh = new khachHang(
                    rs.getString("id"),
                    rs.getString("ten_khach"),
                    rs.getString("sdt"),
                    rs.getString("ngay_them"),
                    rs.getInt("gioi_tinh"),
                    rs.getString("trang_thai"));
            resultList.add(kh);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return resultList;
}
}
