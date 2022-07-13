package com.example.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaiKhoanNganHangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaiKhoanNganHangFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TaiKhoanNganHangFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaiKhoanNganHangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaiKhoanNganHangFragment newInstance(String param1, String param2) {
        TaiKhoanNganHangFragment fragment = new TaiKhoanNganHangFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Láy layout Ngân hàng gán vào
        View view =  inflater.inflate(R.layout.fragment_tai_khoan_ngan_hang, container, false);

        //Lấy TextBox để gán dữ liệu
        TextView tenTK = view.findViewById(R.id.tV_TenTK);
        TextView soDu = view.findViewById(R.id.tV_Tien);
        TextView  ngayLap = view.findViewById(R.id.tV_Ngay);

        //Lấy bảng ngân hàng
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("NganHang");

        Query taiKhoanNganHang =  myRef.orderByChild("maTaiKhoan").equalTo(TaiKhoanDangNhap.taiKhoan);
        taiKhoanNganHang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //Lấy dữ liệu thông tin tài khoản ngân hàng để hiển thị
                    String taiKhoanDb = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("taiKhoanKhachHang").getValue(String.class);
                    Float soDuDb = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("soDu").getValue(Float.class);
                    String ngayLapDb = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("ngayLap").getValue(String.class);

                    //Định dạng tiền
                    String pattern = "###,###.###";
                    DecimalFormat decimalFormat = new DecimalFormat(pattern);

                    //Gán dữ liệu lên TextBox
                    String format = decimalFormat.format(soDuDb);
                    tenTK.setText(taiKhoanDb);
                    soDu.setText(format);
                    ngayLap.setText(ngayLapDb);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        return view;
    }
}