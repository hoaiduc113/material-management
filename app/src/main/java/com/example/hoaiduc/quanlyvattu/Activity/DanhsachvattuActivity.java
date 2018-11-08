package com.example.hoaiduc.quanlyvattu.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hoaiduc.quanlyvattu.ultil.Connect;
import com.example.hoaiduc.quanlyvattu.adapter.DanhsachVattuadapter;
import com.example.hoaiduc.quanlyvattu.R;
import com.example.hoaiduc.quanlyvattu.model.Vattu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DanhsachvattuActivity extends AppCompatActivity {
    String url;
    private String TAG = DanhSachNhanvienActivity.class.getSimpleName();
    private int  success,Mavattu,soluong;
    private String Tenvattu,ghichu;
    private int Dongia;
    private ArrayList<Vattu> vattuArrayList;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private DanhsachVattuadapter vattuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachvattu);
        url= Connect.address+"/DanhSachVatTu.php/";
        vattuAdapter=new DanhsachVattuadapter();
        intitview();


    }
    private void intitview()
    {
        recyclerView=(RecyclerView) findViewById(R.id.recydsvattu);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration=new DividerItemDecoration(getApplicationContext(),layoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);
        vattuArrayList=new ArrayList<>();
        getdata();
        vattuAdapter.notifyDataSetChanged();
    }
    private void getdata()
    {
        RequestQueue myRequestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                    for(int i=0;i<response.length();i++)
                    {
                        try
                        {
                            JSONObject object1=response.getJSONObject(i);
                            Mavattu = Integer.parseInt(object1.getString("MaVT"));
                            Tenvattu = object1.getString("TenVT");
                            soluong = Integer.parseInt(object1.getString("SoLuong"));
                            Dongia = Integer.parseInt(object1.getString("DonGia"));
                            ghichu = object1.getString("GhiChu");
                            vattuArrayList.add(new Vattu(Mavattu,soluong,Tenvattu,ghichu,Dongia));
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    vattuAdapter =new DanhsachVattuadapter(vattuArrayList,getApplicationContext());
                    recyclerView.setAdapter(vattuAdapter);
                    vattuAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DanhsachvattuActivity.this, "loi duong link"+error, Toast.LENGTH_SHORT).show();

            }
        });
        myRequestQueue.add(arrayRequest);
    }

}
