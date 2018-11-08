package com.example.hoaiduc.quanlyvattu.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CapnhatnhanvienActivity extends AppCompatActivity {
    private Intent getnhanvien;
    private EditText ephainv,eloainv,ematkhau,etendangnhap,engaysinh;
    private EditText ediachi,ehonv,eghichu,eluong,etennv;
    private TextView engay;
    private DatePickerDialog.OnDateSetListener date;
    private Button capnhatnv;
    private RadioGroup Ggiotinh,Gloainv;
    private RadioButton Ranam,Ranu,Ranvthuong,Ranvadmin;
    private DecimalFormat dateFormat;
    private int phai,loainv,success,manv;
    private String url,ngaysinh;
    private Button btnupdate;
    private String TAG=CapnhatnhanvienActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capnhatnhanvien);
        anhxa();
        url= Connect.address+"/SuaNhanVien.php";
        getdata();
        process();
        addevent();
    }
    private void getdata()
    {
        getnhanvien=getIntent();
        if(getnhanvien!=null)
        {
            Nhanvien NV= (Nhanvien) getnhanvien.getSerializableExtra("nhanvien");
            etennv.setText(NV.getTennv());
            manv=NV.getManhanvien();
            Toast.makeText(this, "manv"+manv, Toast.LENGTH_SHORT).show();
            ehonv.setText(NV.getHonv());
            ediachi.setText(NV.getDiachi());
            dateFormat=new DecimalFormat("###,###,###");
            eluong.setText(dateFormat.format(NV.getLuong()));
            eghichu.setText(NV.getGhichu());
            etendangnhap.setText(NV.getTendangnhap());
            ematkhau.setText(NV.getMatkhau());
            engay.setText(NV.getNgaysinhnhanvien());
            phai=NV.getLoainv();
            loainv= NV.getLoainv();
            if(phai==1)
            {
                Ranam.setChecked(true);
            }
            else if(phai==0)
            {
                Ranu.setChecked(true);
            }
            if(loainv==0)
            {
                Ranvthuong.setChecked(true);
            }
            else if(loainv==1)
            {
                Ranvadmin.setChecked(true);
            }
        }
    }
    private void process()
    {
        engay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar=Calendar.getInstance();
                int years=calendar.get(Calendar.YEAR);//get years present
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                final DatePickerDialog pickerDialog=new DatePickerDialog(CapnhatnhanvienActivity.this,
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        date,years,month,day);
                pickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLUE));
                pickerDialog.show();
                date=new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        calendar.set(year,month,day);//get year user choose
                        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
                        engay.setText(format.format(calendar.getTime()));
                        ngaysinh=engay.getText().toString().trim();
                       // Toast.makeText(ThemnhanvienActivity.this, "ngay sinh"+ngaysinh, Toast.LENGTH_SHORT).show();
                    }
                };
            }
        });
}

    private void anhxa()
    {
        etennv=(EditText)      findViewById(R.id.extcapnhattennv);
        ehonv=(EditText)       findViewById(R.id.extcapnhathonv);
        ediachi =(EditText)    findViewById(R.id.extcapnhatdiachi);
        eluong=(EditText)      findViewById(R.id.extcapnhatluong);
        eghichu=(EditText)     findViewById(R.id.extcapnhatghichu);
        etendangnhap=(EditText)findViewById(R.id.extcapnhattendangnhap);
        ematkhau=(EditText)    findViewById(R.id.extcapnhatpass);
        Ggiotinh=(RadioGroup)  findViewById(R.id.Grcapnhatsex);
        Ranam=(RadioButton)    findViewById(R.id.rcapnhatnam);
        Ranu=(RadioButton)     findViewById(R.id.rcapnhatnu);
        Gloainv=(RadioGroup)   findViewById(R.id.Grcapnhatloai);
        Ranvthuong=(RadioButton)findViewById(R.id.rcapnhatnhanvienthuong);
        Ranvadmin=(RadioButton)findViewById(R.id.rcapnhatquantrivien);
        capnhatnv=(Button)     findViewById(R.id.rcapnhatquantrivien);
        engay=(TextView)       findViewById(R.id.extcapnhatngaysinh);
        btnupdate=(Button)     findViewById(R.id.btncapnhatnv);
        Ggiotinh.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                //i trả lại id
                switch (i)
                {
                    case R.id.rnam:
                        phai=1;
                        break;
                    case R.id.rnu:
                        phai=0;
                        break;
                }
            }
        });
        Gloainv.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i)
                {
                    case R.id.rnhanvienthuong:
                        loainv=1;
                        break;
                    case R.id.rquantrivien:
                        loainv=0;
                        break;
                }
            }
        });
    }

    private void sendata()
    {
        RequestQueue updatedata= Volley.newRequestQueue(getApplicationContext());
        StringRequest updateRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try 
                {
                    JSONObject  object=new JSONObject(response);
                    success=Integer.parseInt(object.getString("success"));
                    if(success==1)
                    {
                        Toast.makeText(CapnhatnhanvienActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    }
                    else if(success==0)
                    {
                        Toast.makeText(CapnhatnhanvienActivity.this, "Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                    else if(success==-1)
                    {
                        Toast.makeText(CapnhatnhanvienActivity.this, "Bạn chưa nhập thông tin bắt buộc", Toast.LENGTH_SHORT).show();
                    }
                    else if(success==-2)
                    {
                        Toast.makeText(CapnhatnhanvienActivity.this, "Tên đăng  nhập bị trùng", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(CapnhatnhanvienActivity.this, "lỗi", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CapnhatnhanvienActivity.this, "loi duong dan"+error, Toast.LENGTH_SHORT).show();
                Log.d("hhh",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameter=new HashMap<>();
                parameter.put("MaNV",String.valueOf(manv));
                parameter.put("Ho",ehonv.getText().toString());
                parameter.put("Ten",etennv.getText().toString());
                parameter.put("Phai",String.valueOf(phai));
                parameter.put("type",String.valueOf(loainv));
                parameter.put("NgaySinh",String.valueOf(ngaysinh));
                parameter.put("DiaChi",ediachi.getText().toString());
                parameter.put("Luong",eluong.getText().toString());
                parameter.put("GhiChu",eghichu.getText().toString());
                parameter.put("username",etendangnhap.getText().toString());
                parameter.put("password",ematkhau.getText().toString());
                return  parameter;
            }
        };
        updatedata.add(updateRequest);
    }
    private void addevent()
    {
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendata();
            }
        });
    }
}
