package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDatXe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDatXe extends Fragment {

    private Context context;
    private FragmentMap fragmentMap;
    private ImageView btnSetStartingPoint, btnSetDestinationPoint;
    private EditText etCurrentPos, etDestPos;
    private TextView tvKilometer, tvPrice;
    //    SharedPreferences preferences= getActivity().getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentDatXe() {
        // Required empty public constructor
        fragmentMap =new FragmentMap();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDatXe.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDatXe newInstance(String param1, String param2) {
        FragmentDatXe fragment = new FragmentDatXe();
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
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_dat_xe, container, false);
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.DatXe_mapPlacer,(Fragment) fragmentMap)
                .commit();
        context=view.getContext();
        btnSetDestinationPoint = view.findViewById(R.id.DatXe_btnSetDestinationPoint);
        btnSetStartingPoint = view.findViewById(R.id.DatXe_btnSetStartingPoint);
        etCurrentPos = view.findViewById(R.id.DatXe_etCurrentPos);
        etDestPos = view.findViewById(R.id.DatXe_etDestPos);
        tvKilometer = view.findViewById(R.id.DatXe_tvKilometer);
        tvPrice = view.findViewById(R.id.DatXe_tvPrice);
        MapHelper mapHelper=fragmentMap.getMapHelper();
        try{
            mapHelper.setKilometersDisplayingControl(tvKilometer);
        } catch(Exception e)
        {
            e.printStackTrace();
        }
//        fragmentMap.enableKmDisplaying();
        mapHelper.setPricesDisplayingControl(tvPrice);
//        fragmentMap.enablePriceDisplaying();
        View.OnClickListener listener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=v.getId();
                switch(id) {
                    case R.id.DatXe_btnSetStartingPoint:
                        if(mapHelper.hasPlacedMarker()) {
                            etCurrentPos.setText(String.valueOf(mapHelper.getCurrentLatitude()) + "," + String.valueOf(mapHelper.getCurrentLongitude()));
                            mapHelper.setStartMarker();
                        }
                        break;
                    case R.id.DatXe_btnSetDestinationPoint:
                        if(mapHelper.hasPlacedMarker()){
                            etDestPos.setText(String.valueOf(mapHelper.getCurrentLatitude()) + "," + String.valueOf(mapHelper.getCurrentLongitude()));
                            mapHelper.setDestinationMarker();
                        }
                        break;
                }
            }
        };
        btnSetStartingPoint.setOnClickListener(listener);
        btnSetDestinationPoint.setOnClickListener(listener);

        //DatXe
        Button btnDatXe = view.findViewById(R.id.btnDatXe);
        btnDatXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference myRef = database.getReference("KhachHang");
                Query query = myRef.orderByChild("taiKhoan").equalTo(TaiKhoanDangNhap.taiKhoan);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
//                            if(tvPrice.getText().length() == 1){
//                                MessageToastService.MessageToast(getContext(), getResources().getString(R.string.loi_dat_xe));
//                                return;
//                            }
                            String priceSplit = tvPrice.getText().toString().substring(0, tvPrice.getText().toString().indexOf(" "));
                            Float price = Float.parseFloat(priceSplit);
//                            Float price = 12000f;

                            //Get reference NganHang
                            DatabaseReference myRefNganHang = database.getReference("NganHang");
                            Query query1 = myRefNganHang.orderByChild("maTaiKhoan").equalTo(TaiKhoanDangNhap.taiKhoan);
                            query1.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                                    if(dataSnapshot1.exists()){
                                        Float soDu = dataSnapshot1.child(TaiKhoanDangNhap.taiKhoan).child("soDu").getValue(Float.class);
                                        if( soDu < price){
                                            MessageToastService.MessageToast(getContext(), getResources().getString(R.string.loi_so_du));
                                            return;
                                        }

                                        myRefNganHang.child(TaiKhoanDangNhap.taiKhoan).child("soDu").setValue(soDu - price);
                                        MessageToastService.MessageToast(getContext(), getResources().getString(R.string.dat_xe_thanh_cong));
                                        DatabaseReference myRefDoiTac = database.getReference("DoiTac");
                                        Query query2 = myRefDoiTac.orderByChild("maTaiXe").equalTo("nguyenvana");
                                        query2.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                                                if(dataSnapshot2.exists()){
                                                    //Cong tien cho DoiTac
                                                    Query query3 = myRefNganHang.orderByChild("maTaiKhoan").equalTo("nguyenvana");
                                                    query3.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot4) {
                                                            if(dataSnapshot4.exists()){
                                                                Float soDu = dataSnapshot4.child("nguyenvana").child("soDu").getValue(Float.class);
                                                                myRefNganHang.child("nguyenvana").child("soDu").setValue(soDu + price);
//                                                                MessageToastService.MessageToast(getContext(), "ABC");
//
//                                                                //Chuyển khách hàng cho đối tác
                                                                String soDienThoaiKh = dataSnapshot.child(TaiKhoanDangNhap.taiKhoan).child("soDienThoai").getValue(String.class);
                                                                myRefDoiTac.child("nguyenvana").child("maKhachHang").setValue(TaiKhoanDangNhap.taiKhoan);
                                                                myRefDoiTac.child("nguyenvana").child("dangBan").setValue(true);

                                                                //Luu lich su KhachHang, DoiTac
                                                                String timeStamp = new SimpleDateFormat("yyyyMMdd_HH:mm:ss").format(Calendar.getInstance().getTime());
//                                                                LichSuKhachHangDoiTacModel lichSuKhachHangDoiTacModel = new LichSuKhachHangDoiTacModel(etCurrentPos.getText().toString(), etDestPos.getText().toString(), timeStamp, tvKilometer.getText().toString(), tvPrice.getText().toString(), TaiKhoanDangNhap.taiKhoan, "nguyenvana");
                                                                LichSuKhachHangDoiTacModel lichSuKhachHangDoiTacModel = new LichSuKhachHangDoiTacModel("Tân Bình", "Gò vấp", timeStamp, "1", price.toString(), TaiKhoanDangNhap.taiKhoan, "nguyenvana");

                                                                DatabaseReference myRefLichSu = database.getReference("LichSuChuyenDi");
                                                                myRefLichSu.child(lichSuKhachHangDoiTacModel.getTaiKhoanKhachHang()).child(lichSuKhachHangDoiTacModel.getThoiGian()).setValue(lichSuKhachHangDoiTacModel);

                                                                //Chuyen khach hang qua màn hình đợi đối tác
                                                                Intent intent = new Intent(getContext(), KhachHangBatXe.class);
                                                                String tenTaiXe = dataSnapshot2.child("nguyenvana").child("hoTen").getValue(String.class);
                                                                String soDienThoai = dataSnapshot2.child("nguyenvana").child("soDienThoai").getValue(String.class);
                                                                intent.putExtra("tenTaiXe", tenTaiXe);
                                                                intent.putExtra("soDienThoai", soDienThoai);
////
                                                                startActivity(intent);
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {}
                                                    });
//                                                    Intent intent = new Intent(getContext(), KhachHangBatXe.class);
//                                                    String tenTaiXe = dataSnapshot2.child("nguyenvana").child("hoTen").getValue(String.class);
//                                                    String soDienThoai = dataSnapshot2.child("nguyenvana").child("soDienThoai").getValue(String.class);
//
//                                                    intent.putExtra("tenTaiXe", tenTaiXe);
//                                                    intent.putExtra("soDienThoai", soDienThoai);
//                                                    startActivity(intent);
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
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });


            }
        });

        return view;
    }
}