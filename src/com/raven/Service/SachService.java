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
        String sql = "select \n"
                + "CT.id,\n"
                + "S.ten_sach,\n"
                + "S.so_trang,\n"
                + "S.ten_sach,\n"
                + "CT.gia_ban,\n"
                + "CT.so_luong,\n"
                + "CT.ngay_them,\n"
                + "NXB.ten_nxb, \n"
                + "TG.ten_tacgia,\n"
                + "TL.ten_theloai,\n"
                + "CT.hinh_anh,\n"
                + "Ct.trang_thai\n"
                + "from Sach S\n"
                + "inner join ChiTietSach CT on CT.id_sach = S.id\n"
                + "inner join NhaXuatBan NXB on CT.id_nxb = NXB.id\n"
                + "inner join TheLoai TL on CT.id_theloai = TL.id\n"
                + "inner join TacGia TG on CT.id_tacgia = TG.id";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            List<Sach> vm = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach vcm = new Sach(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
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
