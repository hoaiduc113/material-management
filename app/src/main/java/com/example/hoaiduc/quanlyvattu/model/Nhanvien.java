package com.example.hoaiduc.quanlyvattu.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hoai duc on 9/15/2017.
 */

public class Nhanvien  implements Serializable{
  private int manhanvien,phainv,loainv;
  private String diachi,tennv,honv,ghichu;
  private String ngaysinhnhanvien;
  private Float luong;
  private String Tendangnhap,Matkhau;
  public Nhanvien(int manhanvien, int phainv, int loainv, String diachi, String tennv, String honv, String ghichu, String ngaysinhnhanvien, Float luong, String tendangnhap, String matkhau) {
        this.manhanvien = manhanvien;
        this.phainv = phainv;
        this.loainv = loainv;
        this.diachi = diachi;
        this.tennv = tennv;
        this.honv = honv;
        this.ghichu = ghichu;
        this.ngaysinhnhanvien = ngaysinhnhanvien;
        this.luong = luong;
        this.Tendangnhap = tendangnhap;
        this.Matkhau = matkhau;
    }

    public int getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(int manhanvien) {
        this.manhanvien = manhanvien;
    }

    public int getPhainv() {
        return phainv;
    }

    public void setPhainv(int phainv) {
        this.phainv = phainv;
    }

    public int getLoainv() {
        return loainv;
    }

    public void setLoainv(int loainv) {
        this.loainv = loainv;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getHonv() {
        return honv;
    }

    public void setHonv(String honv) {
        this.honv = honv;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getNgaysinhnhanvien() {
        return ngaysinhnhanvien;
    }

    public void setNgaysinhnhanvien(String ngaysinhnhanvien) {
        this.ngaysinhnhanvien = ngaysinhnhanvien;
    }

    public Float getLuong() {
        return luong;
    }

    public void setLuong(Float luong) {
        this.luong = luong;
    }

    public String getTendangnhap() {
        return Tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        Tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return Matkhau;
    }

    public void setMatkhau(String matkhau) {
        Matkhau = matkhau;
    }
}
