/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Model2;

/**
 *
 * @author LENOVO
 */
public class SachBanChay {
    private String ma;
    private String ten;
    private String tacGia;
    private String theLoai;
    private String soLuongBan;

    public SachBanChay() {
    }

    public SachBanChay(String ma, String ten, String tacGia, String theLoai, String soLuongBan) {
        this.ma = ma;
        this.ten = ten;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.soLuongBan = soLuongBan;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
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

    public String getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(String soLuongBan) {
        this.soLuongBan = soLuongBan;
    }
    
}
