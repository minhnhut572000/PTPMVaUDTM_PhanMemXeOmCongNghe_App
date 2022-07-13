package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LichSuChuyenDiViewHolder extends RecyclerView.ViewHolder {
    TextView diemDi, diemDen, soKm, giaTien, thoiGian, taiXe;

    public TextView getDiemDi() {
        return diemDi;
    }

    public void setDiemDi(TextView diemDi) {
        this.diemDi = diemDi;
    }

    public TextView getDiemDen() {
        return diemDen;
    }

    public void setDiemDen(TextView diemDen) {
        this.diemDen = diemDen;
    }

    public TextView getSoKm() {
        return soKm;
    }

    public void setSoKm(TextView soKm) {
        this.soKm = soKm;
    }

    public TextView getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(TextView giaTien) {
        this.giaTien = giaTien;
    }

    public TextView getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(TextView thoiGian) {
        this.thoiGian = thoiGian;
    }

    public TextView getTaiXe() {
        return taiXe;
    }

    public void setTaiXe(TextView taiXe) {
        this.taiXe = taiXe;
    }

    public LichSuChuyenDiViewHolder(@NonNull View itemView) {
        super(itemView);
        this.diemDi = itemView.findViewById(R.id.txtDiemDi);
        this.diemDen = itemView.findViewById(R.id.txtDiemDen);
        this.soKm = itemView.findViewById(R.id.txtSoKm);
        this.giaTien = itemView.findViewById(R.id.txtGiaTien);
        this.thoiGian = itemView.findViewById(R.id.txtThoiGian);
        this.taiXe = itemView.findViewById(R.id.txtTaiXe);
    }
}
