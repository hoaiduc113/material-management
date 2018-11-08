package com.example.hoaiduc.quanlyvattu.model;

/**
 * Created by hoai duc on 10/11/2017.
 */

public class HoaDon {
    private int MaVT,SoLuong,ThanhTien;
    private int Dongia;
    private String TenHang;

    public HoaDon(int maVT, int soLuong, int thanhTien, int dongia, String tenHang) {
        MaVT = maVT;
        SoLuong = soLuong;
        ThanhTien = thanhTien;
        Dongia = dongia;
        TenHang = tenHang;
    }

    public int getMaVT() {
        return MaVT;
    }

    public void setMaVT(int maVT) {
        MaVT = maVT;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int thanhTien) {
        ThanhTien = thanhTien;
    }

    public int getDongia() {
        return Dongia;
    }

    public void setDongia(int dongia) {
        Dongia = dongia;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String tenHang) {
        TenHang = tenHang;
    }
}
