package com.example.myapplication;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FragmentThongTinKhachHangDoi extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thong_tin_khach_hang_doi_xe, null);
        TextView hoTen;
        TextView viTri;
        hoTen = view.findViewById(R.id.tenKhachHangDoi);
        viTri = view.findViewById(R.id.viTriKhachHangDoi);

        DatabaseReference doiTacRef = FirebaseDatabase.getInstance().getReference("DoiTac");
        Query query = doiTacRef.orderByChild("maTaiXe").equalTo(TaiKhoanDangNhap.taiKhoan);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String maKhachHang = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("maKhachHang").getValue(String.class);
                    DatabaseReference khachHangRef = FirebaseDatabase.getInstance().getReference("KhachHang");
                    Query query1 = khachHangRef.orderByChild("taiKhoan").equalTo(maKhachHang);
                    query1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                String hoTenKhachHang = dataSnapshot.child(maKhachHang).child("hoTen").getValue(String.class);
                                String viTriKhachHang = dataSnapshot.child(maKhachHang).child("diaChi").getValue(String.class);

                                hoTen.setText(hoTenKhachHang);
                                viTri.setText(viTriKhachHang);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {}
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        return view;

    }
}
