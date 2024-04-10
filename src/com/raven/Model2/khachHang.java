/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Model2;

/**
 *
 * @author PC
 */
    public class khachHang {

    int id;
    String ten_khach;
    String sdt;
    String ngay_them;
    int gioi_tinh;
    String trang_thai;

    public khachHang() {
    }

    public khachHang(int id, String ten_khach, String sdt, String ngay_them, int gioi_tinh, String trang_thai) {
        this.id = id;
        this.ten_khach = ten_khach;
        this.sdt = sdt;
        this.ngay_them = ngay_them;
        this.gioi_tinh = gioi_tinh;
        this.trang_thai = trang_thai;
    }

    public khachHang(String ten_khach, String sdt, String ngay_them, int gioi_tinh, String trang_thai) {
        this.ten_khach = ten_khach;
        this.sdt = sdt;
        this.ngay_them = ngay_them;
        this.gioi_tinh = gioi_tinh;
        this.trang_thai = trang_thai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen_khach() {
        return ten_khach;
    }

    public void setTen_khach(String ten_khach) {
        this.ten_khach = ten_khach;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNgay_them() {
        return ngay_them;
    }

    public void setNgay_them(String ngay_them) {
        this.ngay_them = ngay_them;
    }

    public int getGioi_tinh() {
        return gioi_tinh;
    }

    public void setGioi_tinh(int gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    @Override
    public String toString() {
        return ten_khach;
    }
    
}
