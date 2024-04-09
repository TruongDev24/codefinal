/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Model2;

/**
 *
 * @author LENOVO
 */
public class myAccount {
    private String tenNV;
    private String role;
    private String hinhAnh;
    
    public myAccount() {
    }

    public myAccount(String tenNV, String role, String hinhAnh) {
        this.tenNV = tenNV;
        this.role = role;
        this.hinhAnh = hinhAnh;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    
}
