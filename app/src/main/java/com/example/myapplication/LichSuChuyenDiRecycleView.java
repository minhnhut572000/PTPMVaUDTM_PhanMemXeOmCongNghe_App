package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LichSuChuyenDiRecycleView extends RecyclerView.Adapter<LichSuChuyenDiViewHolder> {

    Context context;
    ArrayList<LichSuChuyenDi> lstLichSuChuyenDi;

    public LichSuChuyenDiRecycleView(Context context, ArrayList<LichSuChuyenDi> lstLichSuChuyenDi) {
        this.context = context;
        this.lstLichSuChuyenDi = lstLichSuChuyenDi;
    }

    @NonNull
    @Override
    public LichSuChuyenDiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_lichsuchuyendi, null);
        return new LichSuChuyenDiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuChuyenDiViewHolder holder, int position) {
        LichSuChuyenDi lsChuyenDi = lstLichSuChuyenDi.get(position);

        holder.diemDi.setText(lsChuyenDi.getDiemDi());
        holder.diemDen.setText(lsChuyenDi.getDiemDen());
        holder.soKm.setText(String.valueOf(lsChuyenDi.getSoKm()));
        holder.giaTien.setText(String.valueOf(lsChuyenDi.getGiaTien()));
        holder.thoiGian.setText(lsChuyenDi.getThoiGian());
        holder.taiXe.setText(lsChuyenDi.getTenTaiXe());


    }

    @Override
    public int getItemCount() {
        return lstLichSuChuyenDi.size();
    }
}
