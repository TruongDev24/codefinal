/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Service;

import com.raven.Model2.NXB;
import com.raven.Model2.Sach;
import com.raven.Model2.TacGia;
import com.raven.Model2.TheLoai;
import com.raven.dbConnect.DBConnect;
import java.util.List;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class SachService {

    private Connection conn = DBConnect.getConnection();

    public List<Sach> getAll() {
        String sql = "SELECT \n"
                + "    S.id AS id_sach,\n"
                + "    CT.id,\n"
                + "    S.ten_sach,\n"
                + "    S.so_trang,\n"
                + "    CT.gia_ban,\n"
                + "    CT.so_luong,\n"
                + "    CT.ngay_them,\n"
                + "    NXB.ten_nxb, \n"
                + "    TG.ten_tacgia,\n"
                + "    TL.ten_theloai,\n"
                + "    CT.hinh_anh,\n"
                + "    CT.mota,\n"
                + "    CT.trang_thai\n"
                + "FROM \n"
                + "    Sach S\n"
                + "INNER JOIN \n"
                + "    ChiTietSach CT ON CT.id_sach = S.id\n"
                + "INNER JOIN \n"
                + "    NhaXuatBan NXB ON CT.id_nxb = NXB.id\n"
                + "INNER JOIN \n"
                + "    TheLoai TL ON CT.id_theloai = TL.id\n"
                + "INNER JOIN \n"
                + "    TacGia TG ON CT.id_tacgia = TG.id\n"
                + "WHERE\n"
                + "    CT.trang_thai <> N'Đã xóa';";
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
                        rs.getString(12),
                        rs.getString(13));
                vm.add(vcm);
            }
            return vm;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(Sach sach) {
        String sql = "DECLARE @so_trang INT = ?;\n"
                + "DECLARE @ten_sach NVARCHAR(200) = ?;\n"
                + "DECLARE @gia_ban DECIMAL(10, 2) = ?;\n"
                + "DECLARE @so_luong INT = ?;\n"
                + "DECLARE @ngay_them DATETIME2 = ?;\n"
                + "DECLARE @id_nxb INT = ?; -- ID của nhà xuất bản\n"
                + "DECLARE @id_tacgia INT = ?; -- ID của tác giả\n"
                + "DECLARE @hinh_anh VARCHAR(MAX) = ?;"
                + "DECLARE @id_theloai INT = ?; -- ID của thể loại\n"
                + "DECLARE @mota NVARCHAR(MAX) = ?;"
                + "DECLARE @trang_thai NVARCHAR(50) = ?;\n"
                + "\n"
                + "INSERT INTO [dbo].[Sach] ([so_trang], [ten_sach])\n"
                + "VALUES (@so_trang, @ten_sach);\n"
                + "\n"
                + "DECLARE @id_sach INT = SCOPE_IDENTITY(); -- Lấy ID của sách vừa được thêm vào\n"
                + "\n"
                + "INSERT INTO [dbo].[ChiTietSach] ([id_sach], [gia_ban], [so_luong], [ngay_them], [id_nxb], [id_tacgia], [hinh_anh], [id_theloai], [mota], [trang_thai])\n"
                + "VALUES (@id_sach, @gia_ban, @so_luong, @ngay_them, @id_nxb, @id_tacgia, @hinh_anh, @id_theloai, @mota, @trang_thai);";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, sach.getSoTrang());
            ps.setObject(2, sach.getTenSach());
            ps.setObject(3, sach.getGiaBan());
            ps.setObject(4, 0);
            LocalDateTime currentDateTime = LocalDateTime.now();
            // Định dạng ngày giờ
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // Chuyển đổi ngày giờ thành chuỗi
            String ngayThem = currentDateTime.format(formatter);
            ps.setObject(5, ngayThem);
            ps.setObject(6, Integer.valueOf(sach.getNxb()));
            ps.setObject(7, Integer.valueOf(sach.getTacGia()));
            ps.setObject(8, sach.getHinhAnh());
            ps.setObject(9, Integer.valueOf(sach.getTheLoai()));
            ps.setObject(10, sach.getMoTa());
            ps.setObject(11, sach.getTrangThai());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Sach sach) {
        String sql = "DECLARE @id_sach INT = ?;\n"
                + "DECLARE @id_sach_update INT = ?; -- ID sách cần cập nhật\n"
                + "DECLARE @new_so_trang INT = ?;\n"
                + "DECLARE @new_ten_sach NVARCHAR(200) = ?;\n"
                + "DECLARE @new_gia_ban DECIMAL(10, 2) = ?;\n"
                + "DECLARE @new_id_nxb INT = ?; -- ID của nhà xuất bản mới\n"
                + "DECLARE @new_id_tacgia INT = ?; -- ID của tác giả mới\n"
                + "DECLARE @new_hinh_anh VARCHAR(MAX) = ?;\n"
                + "DECLARE @new_id_theloai INT = ?; -- ID của thể loại mới\n"
                + "DECLARE @new_mota NVARCHAR(MAX) = ?;\n"
                + "DECLARE @new_trang_thai NVARCHAR(50) = ?;\n"
                + "\n"
                + "UPDATE [dbo].[Sach]\n"
                + "SET \n"
                + "    [so_trang] = @new_so_trang,\n"
                + "    [ten_sach] = @new_ten_sach\n"
                + "WHERE\n"
                + "    [id] = @id_sach;\n"
                + "\n"
                + "UPDATE [dbo].[ChiTietSach]\n"
                + "SET \n"
                + "    [gia_ban] = @new_gia_ban,\n"
                + "    [id_nxb] = @new_id_nxb,\n"
                + "    [id_tacgia] = @new_id_tacgia,\n"
                + "    [hinh_anh] = @new_hinh_anh,\n"
                + "    [id_theloai] = @new_id_theloai,\n"
                + "    [mota] = @new_mota,\n"
                + "    [trang_thai] = @new_trang_thai\n"
                + "WHERE\n"
                + "    [id] = @id_sach_update;";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, sach.getId_sach());
            ps.setObject(2, sach.getId());
            ps.setObject(3, sach.getSoTrang());
            ps.setObject(4, sach.getTenSach());
            ps.setObject(5, sach.getGiaBan());
            ps.setObject(6, Integer.valueOf(sach.getNxb()));
            ps.setObject(7, Integer.valueOf(sach.getTacGia()));
            ps.setObject(8, sach.getHinhAnh());
            ps.setObject(9, Integer.valueOf(sach.getTheLoai()));
            ps.setObject(10, sach.getMoTa());
            ps.setObject(11, sach.getTrangThai());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean nhapHang(Sach sach) {
        String sql = "DECLARE @id_sach_update INT = ?; -- ID sách cần cập nhật\n"
                + "\n"
                + "DECLARE @new_so_luong INT = ?; -- Số lượng mới\n"
                + "\n"
                + "UPDATE [dbo].[ChiTietSach]\n"
                + "SET \n"
                + "    [so_luong] = @new_so_luong\n"
                + "WHERE\n"
                + "    [id] = @id_sach_update;";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, sach.getId());
            ps.setObject(2, sach.getSoLuong());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Sach sach) {
        String sql = "DECLARE @id_sach_update INT = ?; -- ID sách cần cập nhật\n"
                + "\n"
                + "DECLARE @new_so_luong INT = ?; -- Số lượng mới\n"
                + "\n"
                + "DECLARE @new_trang_thai NVARCHAR(50); -- Trạng thái mới\n"
                + "\n"
                + "IF @new_so_luong = -1\n"
                + "BEGIN\n"
                + "    SET @new_trang_thai = N'Đã xóa';\n"
                + "END\n"
                + "ELSE\n"
                + "BEGIN\n"
                + "    SET @new_trang_thai = (CASE WHEN @new_so_luong > 0 THEN N'Hiện' ELSE N'Ẩn' END);\n"
                + "END\n"
                + "\n"
                + "UPDATE [dbo].[ChiTietSach]\n"
                + "SET \n"
                + "    [so_luong] = @new_so_luong,\n"
                + "    [trang_thai] = @new_trang_thai\n"
                + "WHERE\n"
                + "    [id] = @id_sach_update;";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, sach.getId());
            ps.setObject(2, -1);

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<TacGia> getAllTG() {
        String sql = "select * from TacGia where trang_thai <> N'Đã xóa'";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            List<TacGia> vm = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TacGia vcm = new TacGia(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                vm.add(vcm);
            }
            return vm;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TheLoai> getAllTL() {
        String sql = "select * from TheLoai where trang_thai <> N'Đã xóa'";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            List<TheLoai> vm = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TheLoai vcm = new TheLoai(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                vm.add(vcm);
            }
            return vm;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NXB> getAllNXB() {
        String sql = "select * from NhaXuatBan where trang_thai <> N'Đã xóa'";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            List<NXB> vm = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NXB vcm = new NXB(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                vm.add(vcm);
            }
            return vm;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Sach> getAllByStatus(String status) {
        String sql = "select \n"
                + "S.id,\n"
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
                + "inner join TacGia TG on CT.id_tacgia = TG.id WHERE CT.trang_thai = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            List<Sach> vm = new ArrayList<>();
            ps.setString(1, status);
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
                        rs.getString(12),
                        rs.getString(13));
                vm.add(vcm);
            }
            return vm;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
