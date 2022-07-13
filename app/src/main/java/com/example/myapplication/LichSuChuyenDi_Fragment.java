package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LichSuChuyenDi_Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lich_su_chuyen_di_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("LichSuChuyenDi");
        Query query = myRef.orderByChild("taiKhoanKhachHang").equalTo(TaiKhoanDangNhap.taiKhoan);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    ArrayList<LichSuChuyenDi> lstLichSuChuyenDi = new ArrayList<>();

                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            String diemDi = postSnapshot.child("diemDi").getValue(String.class);
                            String diemDen = postSnapshot.child("diemDen").getValue(String.class);
                            String thoiGian = postSnapshot.child("thoiGian").getValue(String.class);
                            String taiKhoanDoiTac = postSnapshot.child("taiKhoanDoiTac").getValue(String.class);
                            String soKm = postSnapshot.child("soKm").getValue(String.class);
                            String giaTien = postSnapshot.child("giaTien").getValue(String.class);
                            lstLichSuChuyenDi.add(new LichSuChuyenDi(diemDi, diemDen, taiKhoanDoiTac, thoiGian, soKm, giaTien));
                    }
                    LichSuChuyenDiRecycleView lichSuChuyenDiRecycleView = new LichSuChuyenDiRecycleView(getContext(), lstLichSuChuyenDi);

                    RecyclerView recyclerView = view.findViewById(R.id.lichSuRecycleView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    recyclerView.setAdapter(lichSuChuyenDiRecycleView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });




    }
}
