package com.raven.Model2;

import java.util.Date;

public class HoaDonViewModel {
    @Override
    public String toString() {
        return "HoaDonViewModel{" +
                "id=" + id +
                ", tenNhanVien='" + tenNhanVien + '\'' +
                ", ngayTao=" + ngayTao +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }

    private int id;
    private int idHDCT;
    private String tenNhanVien;
    private Date ngayTao;
    private String trangThai;

    public HoaDonViewModel(int id, String tenNhanVien, Date ngayTao, String trangThai) {
        this.id = id;
        this.tenNhanVien = tenNhanVien;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    public Object[] toArray(){
        return new Object[]{id,id,tenNhanVien,ngayTao,trangThai};
    }
}
