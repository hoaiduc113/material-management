package com.example.hoaiduc.quanlyvattu.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hoaiduc.quanlyvattu.ultil.Connect;
import com.example.hoaiduc.quanlyvattu.adapter.DanhSachNhanVienAdapter;
import com.example.hoaiduc.quanlyvattu.model.Nhanvien;
import com.example.hoaiduc.quanlyvattu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DanhSachNhanvienActivity extends AppCompatActivity {
    private String url;
    private String TAG = DanhSachNhanvienActivity.class.getSimpleName();
    private int  success,manhanvien,phainv,loainv;
    private String diachi,tennv,honv,ghichu,username,password;
    private Float luong;
    private ArrayList<Nhanvien> arrayListdsnhanvien2;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    DanhSachNhanVienAdapter nhanVienAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_nhanvien);
        url= Connect.address+"/danhsachnhanvien.php/";
        nhanVienAdapter=new DanhSachNhanVienAdapter();
        intitview();

    }
    private void intitview()
    {
        recyclerView=(RecyclerView) findViewById(R.id.recylerview1);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        arrayListdsnhanvien2=new ArrayList<>();
        getdata();
        nhanVienAdapter.notifyDataSetChanged();
    }
    private void getdata()
    {
        RequestQueue myRequestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG,"response"+response);
                for(int i=0;i<response.length();i++)
                {
                    try
                    {
                        JSONObject object=response.getJSONObject(i);
                        success=Integer.parseInt(object.getString("success"));
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                        Log.d(TAG,"loi doc success"+e);
                    }
                }
                if(success==1)
                {
                    Toast.makeText(DanhSachNhanvienActivity.this, "thanh cong"+success, Toast.LENGTH_SHORT).show();
                    for(int i=0;i<response.length();i++)
                    {
                        try
                        {
                            JSONObject object1=response.getJSONObject(i);
                            manhanvien = Integer.parseInt(object1.getString("MaNV"));
                            honv = object1.getString("Ho");
                            tennv = object1.getString("Ten");
                            phainv = Integer.parseInt(object1.getString("Phai"));
                            diachi = object1.getString("DiaChi");
                            String ngaysinhnv = object1.getString("NgaySinh");
                            luong = Float.parseFloat(object1.getString("Luong"));
                            ghichu = object1.getString("ChucVu");
                            loainv = Integer.parseInt(object1.getString("type"));
                            username=object1.getString("TaiKhoan");
                            password=object1.getString("password");
                            arrayListdsnhanvien2.add(new Nhanvien(manhanvien,phainv,loainv,diachi,tennv,honv,ghichu,ngaysinhnv,luong,username,password));
                            Toast.makeText(DanhSachNhanvienActivity.this,"ma nhan vien"+manhanvien+"dia chi"+diachi, Toast.LENGTH_SHORT).show();
                            Log.d(TAG,"array"+arrayListdsnhanvien2);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    nhanVienAdapter =new DanhSachNhanVienAdapter(arrayListdsnhanvien2,getApplicationContext());
                    recyclerView.setAdapter(nhanVienAdapter);
                    nhanVienAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DanhSachNhanvienActivity.this, "loi duong link"+error, Toast.LENGTH_SHORT).show();

            }
        });
        myRequestQueue.add(arrayRequest);
    }


}
