/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.Model2.HoaDon;
import com.raven.Model2.HoaDonChiTietViewModel;
import com.raven.Model2.HoaDonViewModel;
import com.raven.Model2.KhuyenMaiModel;
import com.raven.dbConnect.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BanHangService {

    public void createOrder(HoaDon hoaDon){
        String sql = "INSERT INTO " +
                "HoaDon (id_tk,id_khach," +
                "ngay_tao,ghi_chu," +
                "tong_tien," +
                "thanh_toan,trang_thai) " +
                "VALUES (?,?,?,?,?,?,?)";
        try ( Connection conn = DBConnect.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setObject(1, hoaDon.getIdTaiKhoan());
                ps.setObject(2, hoaDon.getIdKhach());
                ps.setObject(3, hoaDon.getNgayTao());
                ps.setObject(4, hoaDon.getGhiChu());
                ps.setObject(5, hoaDon.getTongTien());
                ps.setObject(6, hoaDon.getThanhToan());
                ps.setObject(7, hoaDon.getTrangThai());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
   public void addProduct(int idHoaDon, int idSanPham, int soLuong){
       System.out.println(idHoaDon);
        String sql = "INSERT INTO " +
                "ChiTietHoaDon (id_hoadon,id_sachct,so_luong) " +
                "VALUES (?,?,?)";
        String sql2 = "SELECT * from ChiTietHoaDon WHERE id_sachct = ? AND id_hoadon = ?";

        try ( Connection conn = DBConnect.getConnection()) {
            assert conn != null;
           try (PreparedStatement ps = conn.prepareStatement(sql2)) {
               ps.setObject(1, idSanPham);
                ps.setObject(2, idHoaDon);
               ResultSet rs = ps.executeQuery();
               if (rs.next()){
                   String sql3 = "UPDATE ChiTietHoaDon SET so_luong = so_luong + ? WHERE id_sachct = ? And id_hoadon = ?";
                   try (PreparedStatement ps2 = conn.prepareStatement(sql3)) {
                       ps2.setObject(1, soLuong);
                       ps2.setObject(2, idSanPham);
                       ps2.setObject(3, idHoaDon);
                       ps2.executeUpdate();
                   }
               }else {
                   try (PreparedStatement ps2 = conn.prepareStatement(sql)) {
                       ps2.setObject(1, idHoaDon);
                       ps2.setObject(2, idSanPham);
                       ps2.setObject(3, soLuong);
                       ps2.executeUpdate();
                   }
               }
              }
            updateQuantity(idSanPham,soLuong);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void updateHoaDonChiTietQuantity(int id, int soLuong,int idSanPham){
        String sql = "UPDATE ChiTietHoaDon SET so_luong = ? WHERE id = ?";
        String sql1 ="Select so_luong from ChiTietHoaDon where id = ?";
        try ( Connection conn = DBConnect.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql1)) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    int soLuongCu = rs.getInt(1);
                    int soLuongMoi = soLuong - soLuongCu;
                    String sql2 = "UPDATE ChiTietSach SET so_luong = so_luong - ? WHERE id = ?";
                    try (PreparedStatement ps2 = conn.prepareStatement(sql2)) {
                        ps2.setObject(1, soLuongMoi);
                        ps2.setObject(2, idSanPham);
                        ps2.executeUpdate();
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setObject(1, soLuong);
                ps.setObject(2, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
   public List<HoaDonViewModel> getAllOrder(){
        List<HoaDonViewModel> list = new ArrayList<>();
        String sql ="select hd.id,NV.ten_nv,hd.ngay_tao,hd.trang_thai from HoaDon hd JOIN dbo.NhanVien NV on NV.id = hd.id_tk where thanh_toan = 0";
        try ( Connection conn = DBConnect.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    HoaDonViewModel hoaDonViewModel = new HoaDonViewModel(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getDate(3),
                            rs.getString(4)
                    );
                    list.add(hoaDonViewModel);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
   public void updateQuantity(int idSanPham, int soLuong){
        String sql = "UPDATE ChiTietSach SET so_luong = so_luong - ? WHERE id = ?";
        try ( Connection conn = DBConnect.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setObject(1, soLuong);
                ps.setObject(2, idSanPham);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteOrder(int id){
        String sql = "DELETE FROM HoaDon WHERE id = ?";
        String sql2 = "DELETE FROM ChiTietHoaDon WHERE id_hoadon = ?";
        try ( Connection conn = DBConnect.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql2)) {
                ps.setObject(1, id);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setObject(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<HoaDonChiTietViewModel> getAllHoaDonChiTiet(int idHoaDon){
        String sql = """
                 select CTS.id,S.ten_sach,CTHD.so_luong,CTS.gia_ban,(CTHD.so_luong * CTS.gia_ban) as ThanhTien,CTHD.id
                                       from ChiTietHoaDon CTHD
                                                JOIN dbo.ChiTietSach CTS on CTS.id = CTHD.id_sachct
                                                JOIN dbo.Sach S on S.id = CTS.id_sach
                                       WHERE CTHD.id_hoadon = ?
                """;
        try( Connection conn = DBConnect.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setObject(1, idHoaDon);
                ResultSet rs = ps.executeQuery();
                List<HoaDonChiTietViewModel> list = new ArrayList<>();
                while (rs.next()) {
                    HoaDonChiTietViewModel hoaDonChiTietViewModel = new HoaDonChiTietViewModel(
                            rs.getInt(1),
                            rs.getInt(6),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getDouble(4),
                            rs.getDouble(5)
                    );
                    list.add(hoaDonChiTietViewModel);
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteChiTietHoaDon(int id){
        String sql = "DELETE FROM ChiTietHoaDon WHERE id = ?";
        String sqlSelect = "SELECT id_sachct,so_luong FROM ChiTietHoaDon WHERE id = ?";
        try ( Connection conn = DBConnect.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sqlSelect)) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    int idSach = rs.getInt(1);
                    int soLuong = rs.getInt(2);
                    String sql2 = "UPDATE ChiTietSach SET so_luong = so_luong + ? WHERE id = ?";
                    try (PreparedStatement ps2 = conn.prepareStatement(sql2)) {
                        ps2.setObject(1, soLuong);
                        ps2.setObject(2, idSach);
                        ps2.executeUpdate();
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setObject(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateOrder(int id, int thanhToan, double tongTien, int idVoucher){
        String sql = "UPDATE HoaDon SET thanh_toan = ?,tong_tien =? , id_voucher =? WHERE id = ?";
        try ( Connection conn = DBConnect.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setObject(1, thanhToan);
                ps.setObject(2, tongTien);
                ps.setObject(3, idVoucher);
                ps.setObject(4, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
