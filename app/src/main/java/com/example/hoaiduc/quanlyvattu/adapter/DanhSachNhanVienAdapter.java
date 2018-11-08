package com.example.hoaiduc.quanlyvattu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoaiduc.quanlyvattu.model.Nhanvien;
import com.example.hoaiduc.quanlyvattu.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by hoai duc on 9/17/2017.
 */

public class DanhSachNhanVienAdapter  extends  RecyclerView.Adapter<DanhSachNhanVienAdapter.Viewholder>{
    ArrayList<Nhanvien> arraynhNhanviens;
    Context myContext;

    public DanhSachNhanVienAdapter(ArrayList<Nhanvien> arraynhNhanviens, Context myContext)
    {
        this.arraynhNhanviens = arraynhNhanviens;
        this.myContext = myContext;
    }

    public DanhSachNhanVienAdapter() {
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());//khởi tạo layout
        View itemview=inflater.inflate(R.layout.dong_danhsachnhanvien,parent,false);
        return new Viewholder(itemview);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        //gán dữ liệu vào texview
        holder.dsmanhanvien.setText(String.valueOf(arraynhNhanviens.get(position).getManhanvien()));
        if(arraynhNhanviens.get(position).getPhainv()==0)
        {
            holder.dsphainv.setText("Nam");
        }
        else
        {
            holder.dsphainv.setText("Nữ");
        }
        if(arraynhNhanviens.get(position).getLoainv()==1)
        {
            holder.dsloainv.setText(String.valueOf("Nhân viên quản trị"));
        }
        else
        {
            holder.dsloainv.setText(String.valueOf("Nhân viên thường"));
        }

        holder.dsdiachi.setText(arraynhNhanviens.get(position).getDiachi());
        holder.dstennv.setText(arraynhNhanviens.get(position).getHonv()+arraynhNhanviens.get(position).getTennv());
        holder.dsghichu.setText(arraynhNhanviens.get(position).getGhichu());
        DecimalFormat dinhdangso=new DecimalFormat("###,###,###");
        holder.dsluong.setText(dinhdangso.format(arraynhNhanviens.get(position).getLuong()));
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        holder.dsngaysinhnhanvien.setText(arraynhNhanviens.get(position).getNgaysinhnhanvien());
        holder.dsusername.setText(arraynhNhanviens.get(position).getTendangnhap());
        holder.dspassword.setText(arraynhNhanviens.get(position).getMatkhau());

    }

    @Override
    public int getItemCount()
    {
        return arraynhNhanviens==null?0:arraynhNhanviens.size();
        //return arraynhNhanviens.size();

    }

    public class  Viewholder extends RecyclerView.ViewHolder
    {
        TextView dsmanhanvien,dsphainv,dsloainv;
        TextView dsdiachi,dstennv,dsghichu,dsngaysinhnhanvien;
        TextView  dsluong,dsusername,dspassword;
        public Viewholder(View itemView)
        {
            super(itemView);
            dsmanhanvien= (TextView) itemView.findViewById(R.id.dsextmanhanvien);
            dsphainv= (TextView) itemView.findViewById(R.id.dsextgiotinh);
            dsloainv= (TextView) itemView.findViewById(R.id.dsextloainhanvien);
            dsdiachi= (TextView) itemView.findViewById(R.id.dsextdiachi);
            dstennv= (TextView)  itemView.findViewById(R.id.dsexttennhanvien);
            dsghichu=(TextView)  itemView.findViewById(R.id.dsextghichu);
            dsngaysinhnhanvien= (TextView) itemView.findViewById(R.id.dsextngaysinh);
            dsluong= (TextView) itemView.findViewById(R.id.dsextluong);
            dsusername=(TextView) itemView.findViewById(R.id.extdstendangnhap);
            dspassword=(TextView) itemView.findViewById(R.id.extdspassword);
        }
    }
}
