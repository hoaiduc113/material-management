package com.example.hoaiduc.quanlyvattu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hoaiduc.quanlyvattu.model.Nhanvien;
import com.example.hoaiduc.quanlyvattu.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by hoai duc on 9/15/2017.
 */

public class NhanvienAdapter extends BaseAdapter {
    private Context myContext;//màn hình hiện thị dữ liệu
    private  int mylayout;//nơi lấy dữ liệu  cùa màn hình custom listview
    private ArrayList<Nhanvien> arraynhanvien;

    public NhanvienAdapter(Context myContext, int mylayout, ArrayList<Nhanvien> arraynhanvien) {
        this.myContext = myContext;
        this.mylayout = mylayout;
        this.arraynhanvien = arraynhanvien;
    }

    public NhanvienAdapter() {
    }

    public Context getMyContext() {
        return myContext;
    }

    public void setMyContext(Context myContext) {
        this.myContext = myContext;
    }

    public int getMylayout() {
        return mylayout;
    }

    public void setMylayout(int mylayout) {
        this.mylayout = mylayout;
    }

    public ArrayList<Nhanvien> getArraynhanvien() {
        return arraynhanvien;
    }

    public void setArraynhanvien(ArrayList<Nhanvien> arraynhanvien) {
        this.arraynhanvien = arraynhanvien;
    }

    @Override
    public int getCount() {
        return arraynhanvien.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public  static class Viewholder{
        TextView manhanvien,phainv,loainv;
        TextView diachi,tennv,ghichu,ngaysinhnhanvien;
        TextView  luong,username,password;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder myViewholder;
        if(view==null){
            LayoutInflater inflater=LayoutInflater.from(myContext);//chuyển đổi code xml sang code java
            view=inflater.inflate(getMylayout(),null);//khởi tạo layout
            myViewholder=new Viewholder();
            myViewholder.manhanvien= (TextView) view.findViewById(R.id.extmanhanvien);
            myViewholder.phainv= (TextView) view.findViewById(R.id.extgiotinh);
            myViewholder.loainv= (TextView) view.findViewById(R.id.extloainhanvien);
            myViewholder.diachi= (TextView) view.findViewById(R.id.extdiachi);
            myViewholder.tennv= (TextView)  view.findViewById(R.id.exttennhanvien);
            myViewholder.ghichu=(TextView)  view.findViewById(R.id.extghichu);
            myViewholder.ngaysinhnhanvien= (TextView) view.findViewById(R.id.extngaysinh);
            myViewholder.luong= (TextView)   view.findViewById(R.id.extluong);
            myViewholder.username=(TextView) view.findViewById(R.id.extusername);
            myViewholder.password=(TextView) view.findViewById(R.id.extpasswordd);
            //lưu trữ lại nội dung
            view.setTag(myViewholder);
        }
        else{
              myViewholder=(Viewholder) view.getTag();
        }
        Nhanvien myNhanvien=arraynhanvien.get(i);
        myViewholder.manhanvien.setText(String.valueOf(myNhanvien.getManhanvien()));
        if(myNhanvien.getPhainv()==0)
        {
            myViewholder.phainv.setText(String.valueOf("Nam"));
        }
        else
        {
            myViewholder.phainv.setText(String.valueOf("Nữ"));
        }
        if(myNhanvien.getLoainv()==1)
        {
            myViewholder.loainv.setText(String.valueOf("Nhân viên quản trị"));
        }
        else
        {
            myViewholder.loainv.setText(String.valueOf("Nhân viên thường"));
        }
        myViewholder.diachi.setText(myNhanvien.getDiachi());
        myViewholder.tennv.setText(myNhanvien.getHonv()+myNhanvien.getTennv());
        myViewholder.ghichu.setText(myNhanvien.getGhichu());
        DecimalFormat dinhdangso=new DecimalFormat("###,###,###");
        myViewholder.luong.setText(dinhdangso.format(myNhanvien.getLuong()));
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        myViewholder.ngaysinhnhanvien.setText(myNhanvien.getNgaysinhnhanvien());
        myViewholder.username.setText(myNhanvien.getTendangnhap());
        myViewholder.password.setText(myNhanvien.getMatkhau());
        return view;
    }
}
