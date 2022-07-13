package com.example.myapplication;

public class LichSuChuyenDi {
    String diemDi, diemDen, tenTaiXe, thoiGian;
    String soKm, giaTien;

    public LichSuChuyenDi(String diemDi, String diemDen, String tenTaiXe, String thoiGian, String soKm, String giaTien) {
        this.diemDi = diemDi;
        this.diemDen = diemDen;
        this.tenTaiXe = tenTaiXe;
        this.thoiGian = thoiGian;
        this.soKm = soKm;
        this.giaTien = giaTien;
    }

    public String getDiemDi() {
        return diemDi;
    }

    public void setDiemDi(String diemDi) {
        this.diemDi = diemDi;
    }

    public String getDiemDen() {
        return diemDen;
    }

    public void setDiemDen(String diemDen) {
        this.diemDen = diemDen;
    }

    public String getTenTaiXe() {
        return tenTaiXe;
    }

    public void setTenTaiXe(String tenTaiXe) {
        this.tenTaiXe = tenTaiXe;
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
}
