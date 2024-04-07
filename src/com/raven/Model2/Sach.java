/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Model2;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class Sach {
    private String id;
    private String tenSach;
    private String soTrang;
    private String giaBan;
    private String soLuong;
    private String ngayThem;
    private String nxb;
    private String tacGia;
    private String theLoai;
    private String hinhAnh;
    private String moTa;
    private String trangThai;

    public Sach() {
    }

    public Sach(String id, String tenSach, String soTrang, String giaBan, String soLuong, String ngayThem, String nxb, String tacGia, String theLoai, String hinhAnh, String moTa, String trangThai) {
        this.id = id;
        this.tenSach = tenSach;
        this.soTrang = soTrang;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.ngayThem = ngayThem;
        this.nxb = nxb;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(String soTrang) {
        this.soTrang = soTrang;
    }

    public String getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(String giaBan) {
        this.giaBan = giaBan;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(String ngayThem) {
        this.ngayThem = ngayThem;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    
}
