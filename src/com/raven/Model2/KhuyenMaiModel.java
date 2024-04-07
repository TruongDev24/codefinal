/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Model2;

/**
 *
 * @author LENOVO
 */
public class KhuyenMaiModel {
    private String id;
    private String ten;
    private String ngaybd;
    private String ngaykt;
    private String kieuGiam;
    private String trangThai;

    public KhuyenMaiModel() {
    }

    public KhuyenMaiModel(String id, String ten, String ngaybd, String ngaykt, String kieuGiam, String trangThai) {
        this.id = id;
        this.ten = ten;
        this.ngaybd = ngaybd;
        this.ngaykt = ngaykt;
        this.kieuGiam = kieuGiam;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgaybd() {
        return ngaybd;
    }

    public void setNgaybd(String ngaybd) {
        this.ngaybd = ngaybd;
    }

    public String getNgaykt() {
        return ngaykt;
    }

    public void setNgaykt(String ngaykt) {
        this.ngaykt = ngaykt;
    }

    public String getKieuGiam() {
        return kieuGiam;
    }

    public void setKieuGiam(String kieuGiam) {
        this.kieuGiam = kieuGiam;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
}
