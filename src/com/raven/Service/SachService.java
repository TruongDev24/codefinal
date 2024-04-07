/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.Model2.Sach;
import com.raven.dbConnect.DBConnect;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class SachService {

    private Connection conn = DBConnect.getConnection();

    public List<Sach> getAll() {
        String sql = "";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            List<Sach> vm = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach vcm = new Sach(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12));
                vm.add(vcm);
            }
            return vm;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
