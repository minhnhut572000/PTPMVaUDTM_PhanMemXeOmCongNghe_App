package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ChinhSuaThongTinKhachHangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chinhsua_thongtin_khachhang_layout);

        //Ánh xạ
        TextView txtHoTen = findViewById(R.id.editTenTK);
        TextView txtDiaChi = findViewById(R.id.editDiaChi);
        TextView txtSoDienThoai = findViewById(R.id.editSDT);
        Button btnLuu = findViewById(R.id.btnLuu);

        //Lấy bảng khách hàng
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("KhachHang");
        Query query = myRef.orderByChild("taiKhoan").equalTo(TaiKhoanDangNhap.taiKhoan);

        //Lấy thông tin nếu là tài khoản khách hàng
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //Lấy dữ liệu từ Database về để hiển thị lên TextBox
                    String hoTen = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("hoTen").getValue(String.class);
                    String diaChi = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("diaChi").getValue(String.class);
                    String soDienThoai = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("soDienThoai").getValue(String.class);

                    //Gán dữ liệu lên TextBox
                    txtHoTen.setText(hoTen);
                    txtDiaChi.setText(diaChi);
                    txtSoDienThoai.setText(soDienThoai);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        //Lấy thông đối tác để hiển thị
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
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        //Thay đổi thông tin
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Kiểm tra rỗng
                if(txtHoTen.getText().length() <= 0 || txtDiaChi.getText().length() <= 0 || txtDiaChi.getText().length() <= 0){
                    MessageToastService.MessageToast(getApplicationContext(), getResources().getString(R.string.loi_rong));
                    return;
                }

                //Doi tài khoản là khách hàng -> Thay đổi thông tin khách hàng
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            //THay đổi thông tin
                            myRef.child(TaiKhoanDangNhap.taiKhoan).child("hoTen").setValue(txtHoTen.getText().toString());
                            myRef.child(TaiKhoanDangNhap.taiKhoan).child("diaChi").setValue(txtDiaChi.getText().toString());
                            myRef.child(TaiKhoanDangNhap.taiKhoan).child("soDienThoai").setValue(txtSoDienThoai.getText().toString());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });

                //Doi thong tin DoiTac
                query1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            doiTacRef.child(TaiKhoanDangNhap.taiKhoan).child("hoTen").setValue(txtHoTen.getText().toString());
                            doiTacRef.child(TaiKhoanDangNhap.taiKhoan).child("diaChi").setValue(txtDiaChi.getText().toString());
                            doiTacRef.child(TaiKhoanDangNhap.taiKhoan).child("soDienThoai").setValue(txtSoDienThoai.getText().toString());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });



                MessageToastService.MessageToast(getApplicationContext(), getResources().getString(R.string.doi_mat_khau_thanh_cong));
            }
        });
    }
}