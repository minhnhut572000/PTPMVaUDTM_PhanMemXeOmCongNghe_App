package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class KhachHangBatXe extends AppCompatActivity {

    TextView tvHoten, tvSoDienThoai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bat_xe_doi_tac);

        tvHoten = findViewById(R.id.tvTaiXe);
        tvSoDienThoai = findViewById(R.id.tvSoDienThoaiTaiXe);

        Intent intent = getIntent();
        tvHoten.setText(intent.getStringExtra("tenTaiXe"));
        tvSoDienThoai.setText(intent.getStringExtra("soDienThoai"));
    }
}