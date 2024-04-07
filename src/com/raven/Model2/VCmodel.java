/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Model2;

/**
 *
 * @author LENOVO
 */
public class VCmodel {
    private Integer ma;
    private String ten;
    private String ngay_bd;
    private String ngay_kt;
    private String tien_giam;
    private String status;

    public VCmodel() {
    }

    public VCmodel(Integer ma, String ten, String ngay_bd, String ngay_kt, String tien_giam, String status) {
        this.ma = ma;
        this.ten = ten;
        this.ngay_bd = ngay_bd;
        this.ngay_kt = ngay_kt;
        this.tien_giam = tien_giam;
        this.status = status;
    }

    public Integer getMa() {
        return ma;
    }

    public void setMa(Integer ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgay_bd() {
        return ngay_bd;
    }

    public void setNgay_bd(String ngay_bd) {
        this.ngay_bd = ngay_bd;
    }

    public String getNgay_kt() {
        return ngay_kt;
    }

    public void setNgay_kt(String ngay_kt) {
        this.ngay_kt = ngay_kt;
    }

    public String getTien_giam() {
        return tien_giam;
    }

    public void setTien_giam(String tien_giam) {
        this.tien_giam = tien_giam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
