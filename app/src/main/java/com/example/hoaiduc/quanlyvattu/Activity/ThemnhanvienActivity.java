package com.example.hoaiduc.quanlyvattu.Activity;

import android.app.DatePickerDialog;
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
import com.example.hoaiduc.quanlyvattu.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ThemnhanvienActivity extends AppCompatActivity {
   private Button themnv;
   private TextView txt;
   private DatePickerDialog.OnDateSetListener date;
   private int  success,manhanvien,phainv,loainv;
   private Float luong;
   private String diachi,tennv,honv,ghichu,matkhau,tendangnhap,ngaysinh;
   private EditText ephainv,eloainv,ematkhau,etendangnhap;
   private EditText ediachi,ehonv,eghichu,eluong,etennv,engay;
   private RadioGroup Ggiotinh,Gloainv;
   private RadioButton Ranam,Ranu,Ranvthuong,Ranvadmin;
   private String TAG = ThemnhanvienActivity.class.getSimpleName();
   private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themnhanvien);
         url= Connect.address+"/themnhanvien.php";
         anhxa();
         processdate();
         addevent();
    }
    private void addevent()
    {        themnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Postdata();
            }
        });
    }
    private void anhxa()
    {
        etennv=(EditText)      findViewById(R.id.extthemtennv);
        ehonv=(EditText)       findViewById(R.id.extthemhonv);
        ediachi =(EditText)    findViewById(R.id.extthemdiachi);
        eluong=(EditText)      findViewById(R.id.extthemluong);
        eghichu=(EditText)     findViewById(R.id.extthemghichu);
        etendangnhap=(EditText)findViewById(R.id.extthemtendangnhap);
        ematkhau=(EditText)    findViewById(R.id.extthempass);
        Ggiotinh=(RadioGroup)  findViewById(R.id.Grsex);
        Ranam=(RadioButton)    findViewById(R.id.rnam);
        Ranu=(RadioButton)     findViewById(R.id.rnu);
        Gloainv=(RadioGroup)   findViewById(R.id.Grloai);
        Ranvthuong=(RadioButton)findViewById(R.id.rnhanvienthuong);
        Ranvadmin=(RadioButton)findViewById(R.id.rquantrivien);
        themnv=(Button)        findViewById(R.id.btnthemnv);
        engay=(EditText)       findViewById(R.id.extthemngaysinh);
        Ggiotinh.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                //i trả lại id
                switch (i)
                {
                    case R.id.rnam:
                        phainv=1;
                        break;
                    case R.id.rnu:
                        phainv=0;
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
    private  void Postdata()
{
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request= new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            success=Integer.parseInt(jsonObject.getString("success"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(success==1)
                        {
                            Toast.makeText(ThemnhanvienActivity.this, "Thêm thành viên thành công", Toast.LENGTH_SHORT).show();
                        }
                        else if(success==-1)
                        {
                            Toast.makeText(ThemnhanvienActivity.this, "Bạn vui lòng nhập lại ,tên đăng nhập đạ có người sử dụng ", Toast.LENGTH_SHORT).show();
                        }
                        Log.d(TAG,"success"+success);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "loi dang nhap", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), error+"", Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"link dang nhap"
                                +error);
                    }
                }
        )
        {
            @Override
            //tham số thứ string vì đang sử dụng string quest
            //tham số thứ nhất là tên giá trị
            protected Map<String, String> getParams() throws AuthFailureError {
                //the first parameter is name post-value
                    Map<String,String> param = new HashMap<>();
                    param.put("Ho",ehonv.getText().toString());
                    param.put("Ten",etennv.getText().toString());
                    param.put("Phai",String.valueOf(phainv));
                    param.put("DiaChi",ediachi.getText().toString());
                    param.put("NgaySinh",ngaysinh);
                    param.put("Luong",eluong.getText().toString());
                    param.put("GhiChu",eghichu.getText().toString());
                    param.put("username",etendangnhap.getText().toString());
                    param.put("password",ematkhau.getText().toString());
                    param.put("type",String.valueOf(loainv));
                    return param;
            }
        };
        queue.add(request);
}
    private void processdate()
    {
        engay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar=Calendar.getInstance();
                int years=calendar.get(Calendar.YEAR);//get years present
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                final DatePickerDialog pickerDialog=new DatePickerDialog(ThemnhanvienActivity.this,
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
                        Toast.makeText(ThemnhanvienActivity.this, "ngay sinh"+ngaysinh, Toast.LENGTH_SHORT).show();
                    }
                };
            }
        });
    }

}
