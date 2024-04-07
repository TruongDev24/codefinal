/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.Model2.KhuyenMaiModel;
import com.raven.dbConnect.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class KhuyenMaiService {

    private Connection conn = DBConnect.getConnection();

    public List<KhuyenMaiModel> getAll() {
        String sql = "select * from KhuyenMai";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            List<KhuyenMaiModel> vm = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMaiModel vcm = new KhuyenMaiModel(
                        rs.getString(1),
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
}
