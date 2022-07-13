package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText txtTaiKhoan;
    EditText txtMatKhau;
    Button btnDangNhap, btnDangKy;

    FirebaseDatabase database;
    DatabaseReference myRef, doiTac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);

        //Ánh xạ
        txtTaiKhoan = findViewById(R.id.txtTaikhoanDangNhap);
        txtMatKhau = findViewById(R.id.txtMatKhauDangNhap);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btnDangKy);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lay TaiKhoan, MatKhau tu layout ve
                String taiKhoan = txtTaiKhoan.getText().toString();
                String matKhau = txtMatKhau.getText().toString();

                //Khong cho phep nhap rong
                if(taiKhoan.isEmpty() || matKhau.isEmpty()){
                    MessageToastService.MessageToast(getApplicationContext(), getResources().getString(R.string.loi_rong));
                    return;
                }

                //Goi xuong DB. Bang KhachHang
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("KhachHang");

                //Kiem tra taiKhoan có phải là khách hàng hoặc là đối tác.
                //Nếu là khách hàng. Lấy thông tin bảng khách hàng
                //Nếu là đối tác. Lấy thông tin bảng đối tác
                Query query = myRef.orderByChild("taiKhoan").equalTo(taiKhoan);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Nếu là tài khoản khách hàng
                        if(dataSnapshot.exists()){
                            //Lấy mất khẩu của tài khoản để kiểm tra xem có bằng với mật khẩu từ Layout không
                            String matKhauDB = dataSnapshot.child(taiKhoan).child("matKhau").getValue(String.class);

                            //Mật khẩu của tài khoản bằng mật khẩu Layout -> Đăng nhập thành công.
                            // Ngược lại -> Đăng nhập thất bại
                            if(matKhauDB.equals(matKhau)) {

                                //Gán dữ liệu toàn cục để kiểm tra Tài Khoản đã đăng nhập chưa trên App
                                TaiKhoanDangNhap.taiKhoan = taiKhoan;
                                TaiKhoanDangNhap.matKhau = matKhau;

                                //Thông báo thành công. Chuyển Layout thành Layout Main
                                MessageToastService.MessageToast(getApplicationContext(), getResources().getString(R.string.dang_nhap_thanh_cong));
                                Intent intent = new Intent(MainActivity.this, TrangChuActivity.class);
                                startActivity(intent);

                                return;
                            }else{
                                //Thông báo thất bại
                                MessageToastService.MessageToast(getApplicationContext(), getResources().getString(R.string.loi_dang_nhap));
                                return;
                            }
                        }

                        //Kiểm tra tài khoản có phải là đối tác
                        else{

                            //Lấy dữ liệu bảng đối tác
                            doiTac = database.getReference("DoiTac");
                            //Check taiKhoan co phai la doi tac
                            Query query1 = doiTac.orderByChild("maTaiXe").equalTo(taiKhoan);
                            query1.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    //Nếu tài khoản tài xế tồn tại
                                    if(dataSnapshot.exists()){
                                        //Kiểm tra mật khẩu
                                        String matKhauDB = dataSnapshot.child(taiKhoan).child("matKhau").getValue(String.class);
                                        if(matKhauDB.equals(matKhau)) {
                                            TaiKhoanDangNhap.taiKhoan = taiKhoan;
                                            TaiKhoanDangNhap.matKhau = matKhau;

                                            MessageToastService.MessageToast(getApplicationContext(), getResources().getString(R.string.dang_nhap_thanh_cong));
                                            Intent intent = new Intent(MainActivity.this, TrangChuActivity.class);
                                            startActivity(intent);

                                            return;
                                        }else{
                                            MessageToastService.MessageToast(getApplicationContext(), getResources().getString(R.string.loi_dang_nhap));
                                            return;
                                        }
                                    }else{
                                        MessageToastService.MessageToast(getApplicationContext(), getResources().getString(R.string.loi_dang_nhap));
                                        return;
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



            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DangKyActivity.class);

                startActivity(intent);
            }
        });

    }


}