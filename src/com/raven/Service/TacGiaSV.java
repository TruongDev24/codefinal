/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.Model2.TacGia;
import com.raven.dbConnect.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class TacGiaSV {
    private Connection conn = DBConnect.getConnection();

    public boolean add(TacGia tg) {
        String sql = "insert into TacGia(ten_nxb, mo_ta, trang_thai) values (?,?,?)";
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

    public boolean update(TacGia tg) {
        String sql = "update NhaXuatBan set ten_nxb = ?, mo_ta = ?, trang_thai = ? where id = ?";
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

    public boolean delete(TacGia tg) {
        String sql = "update TacGia set trang_thai = ? where id = ?";
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
