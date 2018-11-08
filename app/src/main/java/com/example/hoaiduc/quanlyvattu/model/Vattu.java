package com.example.hoaiduc.quanlyvattu.model;

/**
 * Created by hoai duc on 9/30/2017.
 */

public class Vattu {
    private int     Mavattu,soluong,Dongia;
    private String  Tenvattu,ghichu;


    public Vattu(int mavattu, int soluong, String tenvattu, String ghichu, int dongia) {
        Mavattu = mavattu;
        this.soluong = soluong;
        Tenvattu = tenvattu;
        this.ghichu = ghichu;
        Dongia = dongia;
    }

    public int getMavattu() {
        return Mavattu;
    }

    public void setMavattu(int mavattu) {
        Mavattu = mavattu;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getTenvattu() {
        return Tenvattu;
    }

    public void setTenvattu(String tenvattu) {
        Tenvattu = tenvattu;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public Integer getDongia() {
        return Dongia;
    }

    public void setDongia(int dongia) {
        Dongia = dongia;
    }
}
