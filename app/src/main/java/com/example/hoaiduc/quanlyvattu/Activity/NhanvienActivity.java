package com.example.hoaiduc.quanlyvattu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.hoaiduc.quanlyvattu.model.Nhanvien;
import com.example.hoaiduc.quanlyvattu.adapter.NhanvienAdapter;
import com.example.hoaiduc.quanlyvattu.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NhanvienActivity extends AppCompatActivity {
        private NhanvienAdapter nhanvienAdapter;
        private ListView listViewthongtinNV;
        private ArrayList<Nhanvien> arrayListnhanvien;
        private Intent getdata;
        private String ngaysinhnv,username,password;
        private String ngaysinh;
        private String diachi,tennv,honv,ghichu;
        private Float luong;
        private int  manhanvien,phainv,loainv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien);
        anhxa();
        getdata(getdata);
        adddata();

    }
    private void getdata(Intent getdata){
        getdata=getIntent();
        if(getdata!=null)
        {
            Nhanvien Nvienthongtin= (Nhanvien) getdata.getSerializableExtra("datafromadmin");
            manhanvien=Nvienthongtin.getManhanvien();
            loainv=Nvienthongtin.getLoainv();
            honv=Nvienthongtin.getHonv() ;
            tennv=Nvienthongtin.getTennv();
            phainv=Nvienthongtin.getPhainv();
            diachi=Nvienthongtin.getDiachi();
            ngaysinhnv=Nvienthongtin.getNgaysinhnhanvien();
            luong=Nvienthongtin.getLuong() ;
            ghichu=Nvienthongtin.getGhichu();
            username=Nvienthongtin.getTendangnhap();
            password=Nvienthongtin.getMatkhau();
        }

    }
    private void adddata() {
        SimpleDateFormat dinhdangngay=new SimpleDateFormat("yyyy,MM,dd");
        //ngaysinh=dinhdangngay.parse(ngaysinhnv);
        arrayListnhanvien=new ArrayList<>();
        arrayListnhanvien.add(new Nhanvien(manhanvien,phainv,loainv,diachi,tennv,honv,ghichu,ngaysinhnv,luong,username,password));
        nhanvienAdapter=new NhanvienAdapter(getApplicationContext(),R.layout.dong_nhanvien,arrayListnhanvien);
        listViewthongtinNV.setAdapter(nhanvienAdapter);
    }

    private void anhxa()
    {
        listViewthongtinNV=(ListView) findViewById(R.id.lisviewthongtinnhanvien);
    }
}
