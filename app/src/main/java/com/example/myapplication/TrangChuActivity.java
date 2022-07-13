package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class TrangChuActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Load fragment main
        LoadFragment(new Fragment_TrangChu());

        //Get cuoc xe cua TaiXe
        DatabaseReference doiTacRef = FirebaseDatabase.getInstance().getReference("DoiTac");
        Query query = doiTacRef.orderByChild("maTaiXe").equalTo(TaiKhoanDangNhap.taiKhoan);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    try{
                        String maKhachHang = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("maKhachHang").getValue(String.class);
                        LoadFragment(new FragmentThongTinKhachHangDoi());
                    }
                    catch (Exception e){

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
        //Ánh xạ
        frameLayout = findViewById(R.id.frameLayout);
        bottomNavigationView = findViewById(R.id.menuBottom);



        //Event Selection Item Menu
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.trangChu:
                        currentFragment = new Fragment_TrangChu();
                        break;
                    case R.id.lichSu:
                        currentFragment = new LichSuChuyenDi_Fragment();
                        break;
                    case R.id.taiKhoan:
                        currentFragment = new FragmentThongTinKhachHang();
                        break;
                    case R.id.nganHang:
                        currentFragment = new TaiKhoanNganHangFragment();
                        break;
                    case R.id.datXe:
                        currentFragment = new FragmentDatXe();
                        break;
                }
                LoadFragment(currentFragment);
                return true;
            }
        });
    }

    protected void LoadFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }
}