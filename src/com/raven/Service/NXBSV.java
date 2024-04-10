/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.Model2.NXB;
import com.raven.dbConnect.DBConnect;
import java.sql.*;

/**
 *
 * @author LENOVO
 */
public class NXBSV {

    private Connection conn = DBConnect.getConnection();

    public boolean add(NXB voucher) {
        String sql = "insert into NhaXuatBan(ten_nxb, mo_ta, trang_thai) values (?,?,?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, voucher.getTen());
            ps.setObject(2, voucher.getMoTa());
            ps.setObject(3, voucher.getTrangThai());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(NXB voucher) {
        String sql = "update NhaXuatBan set ten_nxb = ?, mo_ta = ?, trang_thai = ? where id = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, voucher.getTen());
            ps.setObject(2, voucher.getMoTa());
            ps.setObject(3, voucher.getTrangThai());
            ps.setObject(4, voucher.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(NXB voucher) {
        String sql = "update NhaXuatBan set trang_thai = ? where id = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, "Đã xóa");
            ps.setObject(2, voucher.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
