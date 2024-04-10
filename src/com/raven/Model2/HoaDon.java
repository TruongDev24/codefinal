package com.raven.Model2;

import java.sql.Timestamp;
import java.util.Date;

public class HoaDon {
    private int idTaiKhoan;
    private int idKhach;
    private Timestamp ngayTao;
    private String ghiChu;
    private double tongTien;
    private int idKhuyenMai;
    private int idVoucher;
    private int thanhToan;
    private int trangThai;

    public HoaDon(int idTaiKhoan, int idKhach, Timestamp ngayTao, String ghiChu, int thanhToan, int trangThai) {
        this.idTaiKhoan = idTaiKhoan;
        this.idKhach = idKhach;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
        this.thanhToan = thanhToan;
        this.trangThai = trangThai;
    }

    public HoaDon(int idTaiKhoan, int idKhach,Timestamp ngayTao, String ghiChu, double tongTien, int idKhuyenMai, int idVoucher, int thanhToan, int trangThai) {
        this.idTaiKhoan = idTaiKhoan;
        this.idKhach = idKhach;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
        this.tongTien = tongTien;
        this.idKhuyenMai = idKhuyenMai;
        this.idVoucher = idVoucher;
        this.thanhToan = thanhToan;
        this.trangThai = trangThai;
    }

    public int getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(int idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public int getIdKhach() {
        return idKhach;
    }

    public void setIdKhach(int idKhach) {
        this.idKhach = idKhach;
    }

    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public int getIdKhuyenMai() {
        return idKhuyenMai;
    }

    public void setIdKhuyenMai(int idKhuyenMai) {
        this.idKhuyenMai = idKhuyenMai;
    }

    public int getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(int idVoucher) {
        this.idVoucher = idVoucher;
    }

    public int getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(int thanhToan) {
        this.thanhToan = thanhToan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
