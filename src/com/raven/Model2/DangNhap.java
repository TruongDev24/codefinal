/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.Model2;

/**
 *
 * @author LENOVO
 */
public class DangNhap {
    private String tenNV;
    private String user;
    private String pass;
    private String role;

    public DangNhap() {
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public DangNhap(String tenNV, String user, String pass, String role) {
        this.tenNV = tenNV;
        this.user = user;
        this.pass = pass;
        this.role = role;
    }
    
    
}
