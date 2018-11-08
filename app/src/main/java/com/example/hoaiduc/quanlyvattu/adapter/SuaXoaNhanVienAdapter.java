package com.example.hoaiduc.quanlyvattu.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hoaiduc.quanlyvattu.Activity.CapnhatnhanvienActivity;
import com.example.hoaiduc.quanlyvattu.ultil.Connect;
import com.example.hoaiduc.quanlyvattu.model.Nhanvien;
import com.example.hoaiduc.quanlyvattu.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hoai duc on 9/24/2017.
 */

public class SuaXoaNhanVienAdapter extends RecyclerView.Adapter<SuaXoaNhanVienAdapter.Viewholder> {
    private ArrayList<Nhanvien> SuaNhanvien;
    private Activity contextsuaxoa;
    private   int success;
    private   int manv;

    public SuaXoaNhanVienAdapter(ArrayList<Nhanvien> suaNhanvien, Activity contextsuaxoa) {
        SuaNhanvien = suaNhanvien;
        this.contextsuaxoa = contextsuaxoa;
    }

    public SuaXoaNhanVienAdapter() {
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());//khởi tạo layout
        View itemview=inflater.inflate(R.layout.dong_suaxoanhanvien,parent,false);
        return new Viewholder(itemview);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {
        //gán dữ liệu vào texview
        holder.suaxoamanhanvien.setText(String.valueOf(SuaNhanvien.get(position).getManhanvien()));
        holder.suaxoaphainv.setText(String.valueOf(SuaNhanvien.get(position).getPhainv()));
        holder.suaxoaloainv.setText(String.valueOf(SuaNhanvien.get(position).getLoainv()));
        holder.suaxoadiachi.setText(SuaNhanvien.get(position).getDiachi());
        holder.suaxoatennv.setText(SuaNhanvien.get(position).getHonv()+SuaNhanvien.get(position).getTennv());
        holder.suaxoaghichu.setText(SuaNhanvien.get(position).getGhichu());
        DecimalFormat dinhdangso=new DecimalFormat("###,###,###");
        holder.suaxoaluong.setText(dinhdangso.format(SuaNhanvien.get(position).getLuong()));
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        holder.suaxoangaysinhnhanvien.setText(SuaNhanvien.get(position).getNgaysinhnhanvien());

        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder dialog=new AlertDialog.Builder(contextsuaxoa);
                dialog.setIcon(R.drawable.delete);
                dialog.setTitle("Xác nhận xóa");
                dialog.setMessage("Bạn có muốn xóa nhân viên?");
                dialog.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        manv=SuaNhanvien.get(position).getManhanvien();
                        delete(manv);
                    }
                });
                dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog=dialog.create();
                alertDialog.show();

            }
        });
        holder.sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nhanvien myNhanvien=SuaNhanvien.get(position);
                Intent intentNv=new Intent(contextsuaxoa,CapnhatnhanvienActivity.class);
                intentNv.putExtra("nhanvien",myNhanvien);
                contextsuaxoa.startActivity(intentNv);
            }
        });

    }
    private void delete(final int manhavien)
    {
        String url= Connect.address+"/xoanhanvien.php";
        RequestQueue queuexoa = Volley.newRequestQueue(contextsuaxoa);
        StringRequest request= new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            for(int i=0;i<response.length();i++){
                                JSONObject jsonObject=new JSONObject(response);
                                success=Integer.parseInt(jsonObject.getString("success"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(success==1)
                        {
                            Toast.makeText(contextsuaxoa, "Bạn đã xóa nhân viên thành công", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {

                            Toast.makeText(contextsuaxoa, "Lỗi xóa nhân viên", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(contextsuaxoa, "lỗi đường dẫn", Toast.LENGTH_SHORT).show();
                        Log.d("loi","loi"+error);
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> param = new HashMap<>();
                param.put("MaNV",String.valueOf(manhavien));
                return param;
            }
        };
        queuexoa.add(request);
    }
    @Override
    public int getItemCount() {
        return SuaNhanvien==null?0:SuaNhanvien.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder
    {
        Button sua,xoa;
        TextView suaxoamanhanvien,suaxoaphainv,suaxoaloainv;
        TextView suaxoadiachi,suaxoatennv,suaxoaghichu,suaxoangaysinhnhanvien;
        TextView  suaxoaluong;
        public Viewholder(View itemView)
        {
            super(itemView);
            suaxoamanhanvien= (TextView) itemView.findViewById(R.id.suatmanhanvien);
            suaxoaphainv= (TextView) itemView.findViewById(R.id.suagiotinh);
            suaxoaloainv= (TextView) itemView.findViewById(R.id.sualoainhanvien);
            suaxoadiachi= (TextView) itemView.findViewById(R.id.suadiachi);
            suaxoatennv= (TextView)  itemView.findViewById(R.id.suatennhanvien);
            suaxoaghichu=(TextView)  itemView.findViewById(R.id.suaghichu);
            suaxoangaysinhnhanvien= (TextView) itemView.findViewById(R.id.suangaysinh);
            suaxoaluong= (TextView) itemView.findViewById(R.id.sualuong);
            xoa=(Button) itemView.findViewById(R.id.btnxoa);
            sua=(Button) itemView.findViewById(R.id.btnsua);
        }
    }
}
