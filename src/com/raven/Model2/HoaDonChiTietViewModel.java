package com.raven.Model2;

public class HoaDonChiTietViewModel {
    private int id;
    private int idHoaDonChiTiet;
    private String tenSach;
    private int soLuong;
    private double giaBan;
    private double thanhTien;

    public HoaDonChiTietViewModel(int id, String tenSach, int soLuong, double giaBan, double thanhTien) {
        this.id = id;
        this.tenSach = tenSach;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.thanhTien = thanhTien;
    }

    public int getIdHoaDonChiTiet() {
        return idHoaDonChiTiet;
    }

    public void setIdHoaDonChiTiet(int idHoaDonChiTiet) {
        this.idHoaDonChiTiet = idHoaDonChiTiet;
    }

    public HoaDonChiTietViewModel(int id, int idHoaDonChiTiet, String tenSach, int soLuong, double giaBan, double thanhTien) {
        this.id = id;
        this.idHoaDonChiTiet = idHoaDonChiTiet;
        this.tenSach = tenSach;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.thanhTien = thanhTien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
    public Object[] toObject(){
        return new Object[]{id,tenSach,soLuong,giaBan,thanhTien,idHoaDonChiTiet};
    }
}
