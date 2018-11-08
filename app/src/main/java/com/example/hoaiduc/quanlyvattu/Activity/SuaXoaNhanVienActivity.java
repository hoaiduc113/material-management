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
import com.example.hoaiduc.quanlyvattu.model.Nhanvien;
import com.example.hoaiduc.quanlyvattu.R;
import com.example.hoaiduc.quanlyvattu.adapter.SuaXoaNhanVienAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SuaXoaNhanVienActivity extends AppCompatActivity
{
    private String TAG = DanhSachNhanvienActivity.class.getSimpleName();
    private int  success1,xoamanhanvien,xoaphainv,xoaloainv;
    private String xoadiachi,xoatennv,xoahonv,xoaghichu,xoausername,xoapassword;
    private Float xoaluong;
    ArrayList<Nhanvien> arraysuaxoa;
    private String url;
    private RecyclerView recyclerViewsua;
    private LinearLayoutManager layoutManagersua;
    private SuaXoaNhanVienAdapter suaXoaNhanVienAdapter;
    private SimpleDateFormat dateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_xoa_nhan_vien);
        url= Connect.address+"/DanhSachNhanVien.php/";
        suaXoaNhanVienAdapter=new SuaXoaNhanVienAdapter();
        intitviewsua();


    }
    private void intitviewsua()
    {
        recyclerViewsua=(RecyclerView) findViewById(R.id.suaxoarecyclserview);
        recyclerViewsua.setHasFixedSize(true);
        layoutManagersua=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewsua.setLayoutManager(layoutManagersua);
        arraysuaxoa=new ArrayList<>();
        getdatasua();
        suaXoaNhanVienAdapter.notifyDataSetChanged();
    }
    private void getdatasua()
    {
        RequestQueue myRequestQueuesua= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest arrayRequestsua=new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
              //  Log.d(TAG,"response"+response);
                for(int i=0;i<response.length();i++)
                {
                    try
                    {
                        JSONObject object=response.getJSONObject(i);
                        success1=Integer.parseInt(object.getString("success"));
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                        Log.d(TAG,"loi doc success"+e);

                    }
                }
                if(success1==1)
                {

                    for(int i=0;i<response.length();i++)
                    {
                        try
                        {
                            JSONObject objectxoa=response.getJSONObject(i);
                            xoamanhanvien = Integer.parseInt(objectxoa.getString("MaNV"));
                            xoahonv = objectxoa.getString("Ho");
                            xoatennv = objectxoa.getString("Ten");
                            xoaphainv = Integer.parseInt(objectxoa.getString("Phai"));
                            xoadiachi = objectxoa.getString("DiaChi");
                            String ngaysinhnv = objectxoa.getString("NgaySinh");
                            xoaluong = Float.parseFloat(objectxoa.getString("Luong"));
                            xoaghichu = objectxoa.getString("ChucVu");
                            xoaloainv = Integer.parseInt(objectxoa.getString("type"));
                            xoausername=objectxoa.getString("TaiKhoan");
                            xoapassword=objectxoa.getString("password");
                            arraysuaxoa.add(new Nhanvien(xoamanhanvien,xoaphainv,xoaloainv,xoadiachi,xoatennv,xoahonv,xoaghichu,ngaysinhnv,xoaluong,xoausername,xoapassword));
                            Toast.makeText(SuaXoaNhanVienActivity.this,"ma nhan vien"+xoamanhanvien+"dia chi"+xoadiachi, Toast.LENGTH_SHORT).show();
                            Log.d(TAG,"array"+arraysuaxoa);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    suaXoaNhanVienAdapter=new SuaXoaNhanVienAdapter(arraysuaxoa,SuaXoaNhanVienActivity.this);
                    recyclerViewsua.setAdapter(suaXoaNhanVienAdapter);
                    suaXoaNhanVienAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SuaXoaNhanVienActivity.this, "loi duong link"+error, Toast.LENGTH_SHORT).show();

            }
        });
        myRequestQueuesua.add(arrayRequestsua);
    }
}
