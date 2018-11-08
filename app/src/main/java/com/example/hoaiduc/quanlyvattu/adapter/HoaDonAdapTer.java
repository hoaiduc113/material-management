package com.example.hoaiduc.quanlyvattu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoaiduc.quanlyvattu.model.HoaDon;
import com.example.hoaiduc.quanlyvattu.R;

import java.util.ArrayList;

/**
 * Created by hoai duc on 10/11/2017.
 */
public class HoaDonAdapTer extends RecyclerView.Adapter<HoaDonAdapTer.Viewholder> {
    private ArrayList<HoaDon> arrayhoadon;
    private Context myContext;
    public HoaDonAdapTer(ArrayList<HoaDon> arrayhoadon, Context myContext) {
        this.arrayhoadon = arrayhoadon;
        this.myContext = myContext;
    }

    public HoaDonAdapTer() {
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());//khởi tạo layout
        View itemview=inflater.inflate(R.layout.dong_hoadon,parent,false);
        return new Viewholder(itemview);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        holder.hdma.setText(String.valueOf(arrayhoadon.get(position).getMaVT()));
        holder.hdten.setText(arrayhoadon.get(position).getTenHang());
        holder.hdsoluong.setText(String.valueOf(arrayhoadon.get(position).getSoLuong()));
        holder.hdgia.setText(String.valueOf(arrayhoadon.get(position).getDongia()));
        holder.hdthanhtien.setText(String.valueOf(arrayhoadon.get(position).getThanhTien()));
    }

    @Override
    public int getItemCount() {
        return arrayhoadon==null?0:arrayhoadon.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView hdma,hdten,hdsoluong,hdgia,hdthanhtien;
        public Viewholder(View itemView)
        {
            super(itemView);
            hdma=(TextView) itemView.findViewById(R.id.extmahd);
            hdten=(TextView) itemView.findViewById(R.id.extvattuhd);
            hdsoluong=(TextView) itemView.findViewById(R.id.extsoluonghd);
            hdgia=(TextView) itemView.findViewById(R.id.extdongiahd);
            hdthanhtien=(TextView) itemView.findViewById(R.id.exttienhd);

        }
}
}
