/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.Model2.SachBanChay;
import com.raven.dbConnect.DBConnect;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class SachBanChaySV {

    private Connection conn = DBConnect.getConnection();

    public List<SachBanChay> getAll() {
        String sql = "SELECT CS.id_sach AS id_san_pham,\n"
                + "       S.ten_sach AS ten_sach,\n"
                + "       TG.ten_tacgia AS tac_gia,\n"
                + "       TL.ten_theloai AS the_loai,\n"
                + "       SUM(CTHD.so_luong) AS so_luong_ban_ra\n"
                + "FROM ChiTietHoaDon CTHD\n"
                + "INNER JOIN ChiTietSach CS ON CTHD.id_sachct = CS.id\n"
                + "INNER JOIN Sach S ON CS.id_sach = S.id\n"
                + "INNER JOIN TacGia TG ON CS.id_tacgia = TG.id\n"
                + "INNER JOIN TheLoai TL ON CS.id_theloai = TL.id\n"
                + "GROUP BY CS.id_sach, S.ten_sach, TG.ten_tacgia, TL.ten_theloai\n"
                + "ORDER BY so_luong_ban_ra DESC;";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            List<SachBanChay> vm = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SachBanChay vcm = new SachBanChay(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                vm.add(vcm);
            }
            return vm;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
