package com.example.myapplication;

public class KhachHangModel {
    private String taiKhoan;
    private  String matKhau;
    private  String hoTen;
    private  String diaChi;
    private String soDienThoai;
//    private String hinhDaiDien;
    private String loaiKhach;

    public KhachHangModel(String taiKhoan, String matKhau, String hoTen, String soDienThoai, String diaChi, String loaiKhach) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
//        this.hinhDaiDien = hinhDaiDien;
        this.diaChi = diaChi;
        this.loaiKhach = loaiKhach;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

//    public String getHinhDaiDien() {
//        return hinhDaiDien;
//    }

//    public void setHinhDaiDien(String hinhDaiDien) {
//        this.hinhDaiDien = hinhDaiDien;
//    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getLoaiKhach() {
        return loaiKhach;
    }

    public void setLoaiKhach(String loaiKhach) {
        this.loaiKhach = loaiKhach;
    }
}


