package com.example.hoaiduc.quanlyvattu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.example.hoaiduc.quanlyvattu.model.HoaDon;
import com.example.hoaiduc.quanlyvattu.R;
import com.example.hoaiduc.quanlyvattu.adapter.HoaDonAdapTer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class XuatHangActivity extends AppCompatActivity {
    private int codebill,thanhtien;
    private String urlpost;
    private ArrayList<HoaDon> arrayListhoadon;
    private RecyclerView recyclerView;
    private String TAG = XuatHangActivity.class.getSimpleName();
    private LinearLayoutManager layoutManager;
    private HoaDonAdapTer hoaDonAdapTer;
    private int  success,Mavattu,soluong,MaNV,MavattuHD,soluongHD,Dongia;
    private String Tenvattu,ghichu,urlsp,url,urlgetcode,urldetailbill,urldisplay,urlupdate;
    private Float DongiaHD;
    private Intent getIntent;
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arraytenloai;
    private ArrayList<Integer> arraysoluong,arraymasp;
    private ArrayList<Integer> arraydongia;
    private TextView txtsoluong,txtdongia,txtmavt,ngayban,txttenKH,txtmahd,txtthanhtien,txtsoluongkho;
    private DecimalFormat decimalFormat;
    private String TAGG = XuatHangActivity.class.getSimpleName();
    private Button btnadd;
    private EditText extsoluonghd,exttenkh;
    private Button luu,thoat;
    private TextView thanhtienhd;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuat_hang);
        urlpost= Connect.address+"/themhoadon.php/";
        urlsp=Connect.address+"/DanhSachVatTu.php/";
        url=Connect.address+"/HienThiHoaDon.php";
        urlgetcode=Connect.address+"/LayMaHoaDon.php";
        urldetailbill=Connect.address+"/KiemTraVattu.php";
        urldisplay=Connect.address+"/cthd_ds_vattu.php";
        urlupdate=Connect.address+"/TongTien.php";
        getIntent=getIntent();
        anhxa();
        arraytenloai=new ArrayList<>();
        arraysoluong=new ArrayList<>();
        arraymasp=new ArrayList<>();
        arraydongia=new ArrayList<>();
        hoaDonAdapTer=new HoaDonAdapTer();
        Createbill();//post employee code and type bill code
        Getdata();//get data display on spinner
        Eventadd();//event button display on recyclerview
        Eventbutton();
    }
    private void anhxa()
    {
        mySpinner=(Spinner)     findViewById(R.id.spintensp);
        txtsoluong=(TextView)   findViewById(R.id.txtsoluongkho);
        txtmavt=(TextView)      findViewById(R.id.mavattuHD);
        txtdongia=(TextView)    findViewById(R.id.txtdongiahd);
        recyclerView=(RecyclerView) findViewById(R.id.recylerviewhd);
        ngayban=(TextView)      findViewById(R.id.ngaybanhd);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        ngayban.setText(currentDateTimeString);
        btnadd=(Button)         findViewById(R.id.btnthemvt);
        extsoluonghd=(EditText) findViewById(R.id.extsoluonghd);
        exttenkh=(EditText)     findViewById(R.id.txttenkhhd);
        txtmahd=(TextView)      findViewById(R.id.extmacthd);
        txtthanhtien=(TextView) findViewById(R.id.txtthanhtienHD);
        luu=(Button)            findViewById(R.id.luuhd);
        thoat=(Button)          findViewById(R.id.thoat);
        thanhtienhd=(TextView)  findViewById(R.id.thanhtien);
    }
    private void Getdata()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET,urlsp, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);
                        Tenvattu=object.getString("TenVT");
                        soluong=Integer.parseInt(object.getString("SoLuong"));
                        Mavattu=Integer.parseInt(object.getString("MaVT"));
                        Dongia=Integer.parseInt(object.getString("DonGia"));
                        arraytenloai.add(Tenvattu);
                        arraysoluong.add(soluong);
                        arraymasp.add(Mavattu);
                        arraydongia.add(Dongia);
                    }
                    mySpinner.setAdapter(new ArrayAdapter<String>(XuatHangActivity.this,android.R.layout.simple_spinner_dropdown_item,arraytenloai));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(XuatHangActivity.this, "loi tạo hóa đơn"+error, Toast.LENGTH_SHORT).show();
                        Log.d("aaa",error.toString());
                    }
                });
        requestQueue.add(stringRequest);
    }
    private void EventSpin()
    {
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                decimalFormat=new DecimalFormat("###,###,###");
                txtmavt.setText(String.valueOf(arraymasp.get(i)));
                txtdongia.setText(String.valueOf(arraydongia.get(i)));
                txtsoluong.setText(String.valueOf(arraysoluong.get(i)));
                Tenvattu=arraytenloai.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                txtmavt.setText("");
                txtdongia.setText("");
            }
        });
    }
    private void Createbill()
    {
        if(getIntent!=null)
        {
            MaNV=getIntent.getIntExtra("manhanvienHD",-1);
        }
       RequestQueue requestQueue=Volley.newRequestQueue(this);
       StringRequest stringRequest=new StringRequest(Request.Method.POST, urlpost, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               try {
                   JSONArray array=new JSONArray(response);
                   for(int i=0;i<array.length();i++)
                   {
                       JSONObject object=array.getJSONObject(i);
                       int success=Integer.parseInt(object.getString("success"));
                       int mahd=Integer.parseInt(object.getString("MaHD"));
                       int loaid=Integer.parseInt(object.getString("loai"));
                       String message=object.getString("message");
                       if(success==1)
                       {
                           txtmahd.setText(String.valueOf(mahd));
                           Toast.makeText(XuatHangActivity.this,message, Toast.LENGTH_SHORT).show();
                           txtmahd.setText(String.valueOf(mahd));

                       }
                   }
               } catch (JSONException e) {
                   e.printStackTrace();
                   Toast.makeText(XuatHangActivity.this, "loi tao hoa don"+e, Toast.LENGTH_LONG).show();
               }

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast.makeText(XuatHangActivity.this, "loi duong dan"+error, Toast.LENGTH_LONG).show();
           }
       }){
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String> parameter=new HashMap<>();
               int a;
               a=1;
               parameter.put("MaNV",String.valueOf(MaNV));
               parameter.put("loai",String.valueOf(a));
               return parameter;
           }
       };
        requestQueue.add(stringRequest);
        EventSpin();//display data on spinner
    }
    private void Eventadd()
    {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenkh=exttenkh.getText().toString();

                if(tenkh.isEmpty()||mySpinner.getSelectedItem()==null||extsoluonghd.getText().toString().trim().length()==0)
                {
                    Toast.makeText(XuatHangActivity.this, "Bạn vui lòng nhập đầy đủ tên khách hàng và số lượng", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int soluong=Integer.parseInt(extsoluonghd.getText().toString());
                    int dongia=Integer.parseInt(txtdongia.getText().toString());
                    thanhtien=soluong*dongia;
                    txtthanhtien.setText(String.valueOf(thanhtien));
                    detailbill();
                    intitview();
                }
            }
        });
    }
    private void detailbill()//post on detail bill
    {
        RequestQueue postdetail=Volley.newRequestQueue(getApplication());
        StringRequest request=new StringRequest(Request.Method.POST, urldetailbill, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject object=new JSONObject(response);
                    int success=Integer.parseInt(object.getString("success"));
                    String message=object.getString("message");
                    Toast.makeText(XuatHangActivity.this,message, Toast.LENGTH_SHORT).show();
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameter=new HashMap<>();
                String mahd=txtmahd.getText().toString();
                int sl=Integer.parseInt(extsoluonghd.getText().toString());
                int dongia=Integer.parseInt(txtdongia.getText().toString());
                int mavt=Integer.parseInt(txtmavt.getText().toString());
                parameter.put("MaHD",mahd);
                parameter.put("SoLuong",String.valueOf(sl));
                parameter.put("DonGia",String.valueOf(dongia));
                parameter.put("MaVT",String.valueOf(mavt));
                return parameter;
            }
        };
        postdetail.add(request);
    }
    private void intitview()
    {
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        arrayListhoadon=new ArrayList<>();
        getdata();
        hoaDonAdapTer.notifyDataSetChanged();
    }
    private void getdata()
    {
        RequestQueue queue=Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, urldisplay, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);
                        success=Integer.parseInt(object.getString("success"));
                        Mavattu=Integer.parseInt(object.getString("MaVT"));
                        Tenvattu=object.getString("TenVT");
                        Dongia=Integer.parseInt(object.getString("DonGia"));
                        soluong=Integer.parseInt(object.getString("SoLuong"));
                        int Tong=Integer.parseInt(object.getString("Tong"));
                        arrayListhoadon.add(new HoaDon(Mavattu,soluong,Tong,Dongia,Tenvattu));
                    }
                    hoaDonAdapTer=new HoaDonAdapTer(arrayListhoadon,getApplicationContext());
                    recyclerView.setAdapter(hoaDonAdapTer);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    Toast.makeText(XuatHangActivity.this, "loi cthd"+e, Toast.LENGTH_LONG).show();
                }
                hoaDonAdapTer.notifyDataSetChanged();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(XuatHangActivity.this, "loi duong link len CTHD"+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                int ma=Integer.parseInt(txtmahd.getText().toString());
                Map<String,String> parameter=new HashMap<>();
                parameter.put("MaHD",String.valueOf(ma));
                return parameter;
            }
        };
        queue.add(request);
    }
    private void Eventbutton()
    {
        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue=Volley.newRequestQueue(getApplication());
                StringRequest stringRequest=new StringRequest(Request.Method.POST, urlupdate, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject object=new JSONObject(response);
                            int thanhtien=Integer.parseInt(object.getString("tong"));
                            thanhtienhd.setText(String.valueOf(thanhtien));
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(XuatHangActivity.this, "loi duong dan hoadon"+error, Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameter=new HashMap<>();
                        parameter.put("MaHD",(txtmahd.getText().toString()));
                        parameter.put("KhachHang",exttenkh.getText().toString());
                        return  parameter;
                    }
                };
                requestQueue.add(stringRequest);
                thoat.setVisibility(View.VISIBLE);
                thoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent=new Intent(getApplication(),MainActivity.class);
                        startActivity(myIntent);
                    }
                });
            }
        });

    }
}
