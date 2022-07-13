package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentThongTinKhachHang#newInstance} factory method to
 * create an instance of this fragment.
 */

public class FragmentThongTinKhachHang extends Fragment {

    public static FragmentThongTinKhachHang newInstance(String param1, String param2) {
        FragmentThongTinKhachHang fragment = new FragmentThongTinKhachHang();

        return fragment;
    }
    TextView txtHoTen, txtDiaChi, txtSoDienThoai, txtLoaiKhach;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Gán layout_ThongTinKhachHang vào fragment
        View view =  inflater.inflate(R.layout.thongtin_khachhang_fragment, container, false);

        //Kết nối và lấy dữ liệu bảng khách hàng
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("KhachHang");

        Query query = myRef.orderByChild("taiKhoan").equalTo(TaiKhoanDangNhap.taiKhoan);

        //Ánh xạ các textBox trên Layout
         txtHoTen = view.findViewById(R.id.tvHoTen);
         txtDiaChi = view.findViewById(R.id.tvDiaChiNha);
         txtSoDienThoai = view.findViewById(R.id.tVSDT);
         txtLoaiKhach = view.findViewById(R.id.tvLoaiKH);

         //Lấy thông tin khách hàng
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //Lấy thông tin khách hàng từ database về
                    String hoTen = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("hoTen").getValue(String.class);
                    String diaChi = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("diaChi").getValue(String.class);
                    String soDienThoai = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("soDienThoai").getValue(String.class);
                    String loaiKhach = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("loaiKhach").getValue(String.class);

                    //Gán dữ liệu lên TextBox
                    txtHoTen.setText(hoTen);
                    txtDiaChi.setText(diaChi);
                    txtSoDienThoai.setText(soDienThoai);
                    txtLoaiKhach.setText(loaiKhach);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        //Nếu tài khoản là tài xế
        DatabaseReference doiTacRef = FirebaseDatabase.getInstance().getReference("DoiTac");
        Query query1 = doiTacRef.orderByChild("maTaiXe").equalTo(TaiKhoanDangNhap.taiKhoan);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //Lấy thông tin tài xế từ database về
                    String hoTen = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("hoTen").getValue(String.class);
                    String diaChi = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("diaChi").getValue(String.class);
                    String soDienThoai = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("soDienThoai").getValue(String.class);

                    //Gán dữ liệu lên TextBox
                    txtHoTen.setText(hoTen);
                    txtDiaChi.setText(diaChi);
                    txtSoDienThoai.setText(soDienThoai);
                    txtLoaiKhach.setText("DoiTac");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        Button btnChinhSua = view.findViewById(R.id.btnChinhSua);
        btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChinhSuaThongTinKhachHangActivity.class);

                startActivityForResult(intent, 999);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 999){
            //Get textBox
            txtHoTen = getView().findViewById(R.id.tvHoTen);
            txtDiaChi = getView().findViewById(R.id.tvDiaChiNha);
            txtSoDienThoai = getView().findViewById(R.id.tVSDT);
            txtLoaiKhach = getView().findViewById(R.id.tvLoaiKH);

            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("KhachHang");
            Query query = myRef.orderByChild("taiKhoan").equalTo(TaiKhoanDangNhap.taiKhoan);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        String hoTen = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("hoTen").getValue(String.class);
                        String diaChi = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("diaChi").getValue(String.class);
                        String soDienThoai = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("soDienThoai").getValue(String.class);
                        String loaiKhach = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("loaiKhach").getValue(String.class);

                        txtHoTen.setText(hoTen);
                        txtDiaChi.setText(diaChi);
                        txtSoDienThoai.setText(soDienThoai);
                        txtLoaiKhach.setText(loaiKhach);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });

            DatabaseReference doiTacRef = FirebaseDatabase.getInstance().getReference("DoiTac");
            Query query1 = doiTacRef.orderByChild("maTaiXe").equalTo(TaiKhoanDangNhap.taiKhoan);
            query1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        String hoTen = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("hoTen").getValue(String.class);
                        String diaChi = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("diaChi").getValue(String.class);
                        String soDienThoai = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("soDienThoai").getValue(String.class);

                        txtHoTen.setText(hoTen);
                        txtDiaChi.setText(diaChi);
                        txtSoDienThoai.setText(soDienThoai);
                        txtLoaiKhach.setText("DoiTac");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });
        }
    }
}
