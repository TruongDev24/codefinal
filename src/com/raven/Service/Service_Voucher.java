/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.Model2.VCmodel;
import com.raven.dbConnect.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class Service_Voucher {

    private Connection conn = DBConnect.getConnection();

    public List<VCmodel> getAll() {
        String sql = "select id, ten_vc,\n"
                + "    CONVERT(VARCHAR(19), ngay_batdau, 120) AS ngay_batdau,\n"
                + "    CONVERT(VARCHAR(19), ngay_ketThuc, 120) AS ngay_ketThuc,\n"
                + "    tien_giam,\n"
                + "    trang_thai from Voucher where trang_thai <> N'Đã xóa'";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            List<VCmodel> vm = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VCmodel vcm = new VCmodel(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
                vm.add(vcm);
            }
            return vm;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(VCmodel voucher) {
        String sql = "insert into Voucher(ten_vc, ngay_batdau, ngay_ketThuc, tien_giam, trang_thai) values (?,?,?,?,?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, voucher.getTen());
            ps.setObject(2, voucher.getNgay_bd());
            ps.setObject(3, voucher.getNgay_kt());
            ps.setObject(4, voucher.getTien_giam());
            ps.setObject(5, "Chưa diễn ra");

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(VCmodel voucher) {
        String sql = "update Voucher set ten_vc = ?, ngay_batdau = ?, ngay_ketThuc = ?, tien_giam = ? where id = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, voucher.getTen());
            ps.setObject(2, voucher.getNgay_bd());
            ps.setObject(3, voucher.getNgay_kt());
            ps.setObject(4, voucher.getTien_giam());
            ps.setInt(5, voucher.getMa());
            
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(VCmodel voucher) {
        String sql = "update Voucher set trang_thai = ? where id = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, "Đã xóa");
            ps.setInt(2, voucher.getMa());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public VCmodel getAvailableVoucher(int id) {
        String sql = "Select top 1 * from Voucher where ngay_batdau <= ? and ngay_ketThuc >= ? and id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, LocalDateTime.now());
            ps.setObject(2, LocalDateTime.now());
            ps.setObject(3, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new VCmodel(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
