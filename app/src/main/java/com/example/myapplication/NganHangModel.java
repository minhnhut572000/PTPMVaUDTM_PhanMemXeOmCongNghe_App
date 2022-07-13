package com.example.myapplication;

public class NganHangModel {
    private String maTaiKhoan;
    private String matKhau;
    private float soDu;
    private String ngayLap;
    private String taiKhoanKhachHang;

    public NganHangModel(String maTaiKhoan, String matKhau, float soDu, String taiKhoanKhachHang) {
        this.maTaiKhoan = maTaiKhoan;
        this.matKhau = matKhau;
        this.soDu = soDu;
        this.ngayLap = "01/01/2021";
        this.taiKhoanKhachHang = taiKhoanKhachHang;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public float getSoDu() {
        return soDu;
    }

    public void setSoDu(float soDu) {
        this.soDu = soDu;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getTaiKhoanKhachHang() {
        return taiKhoanKhachHang;
    }

    public void setTaiKhoanKhachHang(String taiKhoanKhachHang) {
        this.taiKhoanKhachHang = taiKhoanKhachHang;
    }
}
