package com.example.hoaiduc.quanlyvattu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoaiduc.quanlyvattu.R;
import com.example.hoaiduc.quanlyvattu.model.Vattu;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by hoai duc on 9/30/2017.
 */

public class DanhsachVattuadapter extends RecyclerView.Adapter<DanhsachVattuadapter.Viewholder> {
    ArrayList<Vattu> arrayvattu;
    Context myContext;

    public DanhsachVattuadapter(ArrayList<Vattu> arrayvattu, Context myContext) {
        this.arrayvattu = arrayvattu;
        this.myContext = myContext;
    }

    public DanhsachVattuadapter() {
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());//khởi tạo layout
        View itemview=inflater.inflate(R.layout.dong_vattu,parent,false);
        return new Viewholder(itemview);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        holder.Mavattu.setText(String.valueOf(arrayvattu.get(position).getMavattu()));
        holder.Tenvattu.setText(arrayvattu.get(position).getTenvattu());
        DecimalFormat format=new DecimalFormat("###,###,###");
        holder.Soluong.setText(String.valueOf(arrayvattu.get(position).getSoluong()));
        holder.Dongia.setText(format.format(arrayvattu.get(position).getDongia()));
        holder.Ghichu.setText(arrayvattu.get(position).getGhichu());
    }

    @Override
    public int getItemCount() {
        return arrayvattu==null?0:arrayvattu.size();
    }

    public class  Viewholder extends RecyclerView.ViewHolder
    {
        TextView Mavattu,Tenvattu,Dongia,Ghichu,Soluong;
        public Viewholder(View itemView) {
            super(itemView);
            Mavattu=(TextView) itemView.findViewById(R.id.mavt);
            Tenvattu=(TextView) itemView.findViewById(R.id.tenvt);
            Dongia=(TextView) itemView.findViewById(R.id.dongiavt);
            Ghichu=(TextView) itemView.findViewById(R.id.ghichuvt);
            Soluong=(TextView) itemView.findViewById(R.id.soluongvt);
        }
    }
}
