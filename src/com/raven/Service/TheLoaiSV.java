/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.Model2.TheLoai;
import com.raven.dbConnect.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class TheLoaiSV {
    private Connection conn = DBConnect.getConnection();

    public boolean add(TheLoai tg) {
        String sql = "insert into TheLoai(ten_theloai, mo_ta, trang_thai) values (?,?,?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, tg.getTen());
            ps.setObject(2, tg.getMoTa());
            ps.setObject(3, tg.getTrangThai());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(TheLoai tg) {
        String sql = "update NhaXuatBan set ten_theloai = ?, mo_ta = ?, trang_thai = ? where id = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, tg.getTen());
            ps.setObject(2, tg.getMoTa());
            ps.setObject(3, tg.getTrangThai());
            ps.setObject(4, tg.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(TheLoai tg) {
        String sql = "update TheLoai set trang_thai = ? where id = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, "Đã xóa");
            ps.setObject(2, tg.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
