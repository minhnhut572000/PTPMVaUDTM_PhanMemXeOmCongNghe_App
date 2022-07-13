package com.example.myapplication;

import java.util.Date;

public class LichSuKhachHangDoiTacModel {
    private String diemDi;
    private String diemDen;
    private String thoiGian;
    private String soKm, giaTien;
    private String taiKhoanKhachHang, taiKhoanDoiTac;

    public LichSuKhachHangDoiTacModel(String diemDi, String diemDen, String thoiGian, String soKm, String giaTien, String taiKhoanKhachHang, String taiKhoanDoiTac) {
        this.diemDi = diemDi;
        this.diemDen = diemDen;
        this.thoiGian = thoiGian;
        this.soKm = soKm;
        this.giaTien = giaTien;
        this.taiKhoanKhachHang = taiKhoanKhachHang;
        this.taiKhoanDoiTac = taiKhoanDoiTac;
    }

    public String getDiemDi() {
        return diemDi;
    }

    public void setDiemDi(String diemDi) {
        this.diemDi = diemDi;
    }

    public String getDiemDien() {
        return diemDen;
    }

    public void setDiemDien(String diemDien) {
        this.diemDen = diemDien;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getSoKm() {
        return soKm;
    }

    public void setSoKm(String soKm) {
        this.soKm = soKm;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public String getTaiKhoanKhachHang() {
        return taiKhoanKhachHang;
    }

    public void setTaiKhoanKhachHang(String taiKhoanKhachHang) {
        this.taiKhoanKhachHang = taiKhoanKhachHang;
    }

    public String getTaiKhoanDoiTac() {
        return taiKhoanDoiTac;
    }

    public void setTaiKhoanDoiTac(String taiKhoanDoiTac) {
        this.taiKhoanDoiTac = taiKhoanDoiTac;
    }
}
