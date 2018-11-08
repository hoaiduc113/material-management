package com.example.hoaiduc.quanlyvattu.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.example.hoaiduc.quanlyvattu.model.Nhanvien;
import com.example.hoaiduc.quanlyvattu.R;

public class NVthuongActivity extends AppCompatActivity {
    private int  success,manhanvien,phainv,loainv;
    private String diachi,tennv,honv,ghichu,ngaysinhnv;
    private Float luong;
    private Toolbar MYToolbar;
    private Intent getdata;
    private DrawerLayout mydrawerLayout;
    private NavigationView myNavigationView;
    private Intent getintent;
    private Nhanvien nhanvienthongtin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvthuong);
        setSupportActionBar(MYToolbar);
        anhxa();
        setSupportActionBar(MYToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//tạo nút mũi tên
        MYToolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        myNavigationView.setItemIconTintList(null);//set icon have color
        sukien();
    }
    private void sukien() {
        MYToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydrawerLayout.openDrawer(Gravity.START);
            }
        });
        //bắt sự kiên cho menu
        myNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuthongtinnv:
                        getintent=getIntent();
                        nhanvienthongtin= (Nhanvien) getintent.getSerializableExtra("staffInformation");
                        Intent changescreen=new Intent(getApplicationContext(),NhanvienActivity.class);
                        changescreen.putExtra("datafromadmin",nhanvienthongtin);
                        startActivity(changescreen);
                        break;
                    case R.id.menudanhsachnhanviennv:
                        Intent intent=new Intent(getApplicationContext(),DanhSachNhanvienActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.menudanhsachhoadonnv:
                        Intent intenvattu=new Intent(getApplicationContext(),DanhsachvattuActivity.class);
                        startActivity(intenvattu);
                        break;
                    case R.id.menuxuathangnv:
                        Intent shipment=new Intent(getApplicationContext(),XuatHangActivity.class);
                        getdata=getIntent();
                        Nhanvien Nvienthongtin= (Nhanvien) getdata.getSerializableExtra("staffInformation");
                        manhanvien=Nvienthongtin.getManhanvien();
                        shipment.putExtra("manhanvienHD",manhanvien);
                        startActivity(shipment);
                    case R.id.menunhaphangnv:
                        Intent goods=new Intent(getApplicationContext(),NhapHangActivity.class);
                        getdata=getIntent();
                        Nhanvien vienthongtin= (Nhanvien) getdata.getSerializableExtra("staffInformation");
                        manhanvien=vienthongtin.getManhanvien();
                        goods.putExtra("manhanvienHD",manhanvien);
                        startActivity(goods);
                }
                return false;
            }
        });
    }

    private void anhxa()
    {
        MYToolbar=(Toolbar) findViewById(R.id.toolbar);
        mydrawerLayout=(DrawerLayout) findViewById(R.id.mydrable);
        myNavigationView=(NavigationView) findViewById(R.id.mynavi);
    }
}
