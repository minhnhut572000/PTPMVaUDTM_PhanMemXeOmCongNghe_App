package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DangKyActivity extends AppCompatActivity {


    EditText txtTaiKhoan, txtMatKhau, txtSoDT, txtHoTen, txtHinhDaiDien, txtDiaChi;
    Button  btnDangKy;
    FirebaseDatabase db;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);

        //Anh xa
        txtTaiKhoan = findViewById(R.id.txtTaiKhoan);
        txtMatKhau = findViewById(R.id.txtMatKhau);
        txtSoDT = findViewById(R.id.txtSoDT);
        txtHoTen = findViewById(R.id.txtHoTen);
        txtDiaChi = findViewById(R.id.txtDiaChi);

        btnDangKy = findViewById(R.id.btnDangKy);

        //Connect DB
        db = FirebaseDatabase.getInstance();
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lay du lieu tu Layout xuong
                String taiKhoan = txtTaiKhoan.getText().toString();
                String matKhau = txtMatKhau.getText().toString();
                String soDT = txtSoDT.getText().toString();
                String hoTen = txtHoTen.getText().toString();
//                String hinhDaiDien = txtHinhDaiDien.getText().toString();
                String diaChi = txtDiaChi.getText().toString();


                //Kiem tra rong. Neu co 1 field nao rong. Thong bao loi
                if(taiKhoan.isEmpty() || matKhau.isEmpty() || soDT.isEmpty() || hoTen.isEmpty() ||  diaChi.isEmpty()){
                    MessageToastService.MessageToast(getApplicationContext(), getResources().getString(R.string.loi_rong));
                    return;
                }

                //Kiem tra tai khoan co phai la duy nhat. Neu phai -> Tra ve loi
                db = FirebaseDatabase.getInstance();
                myRef = db.getReference("KhachHang");

                //Kiem tra tai khoan da ton tai chua?
                Query checkTaiKhoan = myRef.orderByChild("taiKhoan").equalTo(taiKhoan);
                checkTaiKhoan.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Neu Tai Khoan da ton tai -> dataShopshot.exists = true
                        if(dataSnapshot.exists() == true){
                            MessageToastService.MessageToast(getApplicationContext(), getResources().getString(R.string.loi_taikhoan_ton_tai));
                            return;
                        }
                        else{
                            //Dang ky tai khoan

                            //Khoi tao object KhachHang de them vao database
                            KhachHangModel khachHangModel = new KhachHangModel(taiKhoan, matKhau, hoTen, soDT, diaChi, "Thuong");

                            //Them KhachHang vao bang KhachHang. Voi dinh danh la TaiKhoanKhachHang
                            myRef.child(taiKhoan).setValue(khachHangModel);

                            //Thong bao dang ky thanh cong
                            String message =  getResources().getString(R.string.dang_ky_thanh_cong);
                            MessageToastService.MessageToast(getApplicationContext(), message);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });



            }
        });
    }

}