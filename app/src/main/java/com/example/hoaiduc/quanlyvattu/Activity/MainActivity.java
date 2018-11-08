package com.example.hoaiduc.quanlyvattu.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hoaiduc.quanlyvattu.ultil.Connect;
import com.example.hoaiduc.quanlyvattu.model.Nhanvien;
import com.example.hoaiduc.quanlyvattu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
   private String url1;
   private EditText tendangnhap,matkhau;
   private Button dongy;
   private int  success,manhanvien,phainv,loainv;
   private String diachi,tennv,honv,ghichu,username,passwork;
   private Float luong;
   private String TAG = MainActivity.class.getSimpleName();
   private CheckBox cbrember;
   private  SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        sharedPreferences=getSharedPreferences("datalogin",MODE_PRIVATE);
        //lấy giá trị SharedPreferences
        tendangnhap.setText(sharedPreferences.getString("taikhoan",""));
        matkhau.setText(sharedPreferences.getString("matkhau",""));
        url1= Connect.address+"/dangnhap.php";
        cbrember.setChecked(sharedPreferences.getBoolean("check",false));
        sukien();
    }
    private void sukien() {

        dongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tendangnhap.getText().toString().isEmpty()||
                   matkhau.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "bạn vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else{
                    postdata();
                }

            }
        });
    }
    private void anhxa() {
        tendangnhap=(EditText) findViewById(R.id.exttendangnhap);
        matkhau=(EditText) findViewById(R.id.extmatkhau);
        dongy=(Button) findViewById(R.id.btndongy);
        cbrember=(CheckBox)  findViewById(R.id.ckremember);
    }

    private void postdata()
    {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request= new StringRequest(Request.Method.POST, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<response.length();i++)
                            {
                                JSONObject object=jsonArray.getJSONObject(i);
                                success=Integer.parseInt(object.getString("success"));
                            }
                            Log.d(TAG,"success"+success);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(success==1){
                            Toast.makeText(MainActivity.this, "đăng nhập thành công"+success, Toast.LENGTH_SHORT).show();
                            try {
                                for(int i=0;i<response.length();i++)
                                {
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    manhanvien = Integer.parseInt(object.getString("manv"));
                                    honv = object.getString("ho");
                                    tennv = object.getString("ten");
                                    phainv = Integer.parseInt(object.getString("phai"));
                                    diachi = object.getString("diachi");
                                    String ngaysinhnv = object.getString("ngaysinh");
                                    //ngaysinh = dateFormat.parse(ngaysinhnv);
                                    luong = Float.parseFloat(object.getString("luong"));
                                    ghichu = object.getString("ghichu");
                                    loainv = Integer.parseInt(object.getString("type"));
                                    username=object.getString("username");
                                    passwork=object.getString("password");
                                    if(cbrember.isChecked())
                                    {
                                        SharedPreferences.Editor editor=sharedPreferences.edit();
                                        editor.putString("taikhoan",username);
                                        editor.putString("matkhau",passwork);
                                        editor.putBoolean("check",true);
                                        editor.commit();
                                    }
                                    if(loainv==1)
                                    {
                                        Intent Sendata=new Intent(MainActivity.this,adminActivity.class);
                                        Nhanvien Sennhanvien=new Nhanvien(manhanvien,phainv,loainv,diachi,tennv,honv,ghichu,ngaysinhnv,luong,username,passwork);
                                        Sendata.putExtra("staffInformation",Sennhanvien);
                                        startActivity(Sendata);
                                    }
                                    else
                                    {
                                        Intent data=new Intent(MainActivity.this,NVthuongActivity.class);
                                        Nhanvien nhanvien=new Nhanvien(manhanvien,phainv,loainv,diachi,tennv,honv,ghichu,ngaysinhnv,luong,username,passwork);
                                        data.putExtra("staffInformation",nhanvien);
                                        startActivity(data);
                                    }
                                }
                            }
                            catch (JSONException e)
                            {
                                Log.d(TAG,"e"+e);
                                e.printStackTrace();
                            }
                        }
                        else{
                            Log.d(TAG,"respon"+response.toString());
                            Toast.makeText(MainActivity.this, "đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "loi dang nhap", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, error+"", Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"link dang nhap"
                                +error);
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("username",tendangnhap.getText().toString());
                param.put("password",matkhau.getText().toString());

                return param;
            }
        };
        queue.add(request);
    }
}
