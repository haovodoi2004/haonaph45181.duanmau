package fpoly.haonaph45181.duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpoly.haonaph45181.duanmau.Model.LoaiSach;
import fpoly.haonaph45181.duanmau.R;

public class LoaiSachSpinner extends ArrayAdapter<LoaiSach> {
    Context context;
    ArrayList<LoaiSach> loaiSachArrayList;
    TextView tvthutu,tvten;

    public LoaiSachSpinner(Context context, ArrayList<LoaiSach> loaiSachArrayList) {
        super(context,0,loaiSachArrayList);
        this.context = context;
        this.loaiSachArrayList = loaiSachArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_sach_spinner,null);
        tvthutu=convertView.findViewById(R.id.textView51);
        tvten=convertView.findViewById(R.id.textView52);
        LoaiSach loaiSach=this.getItem(position);
        if(loaiSach!=null){
            tvthutu.setText(loaiSach.maLoai+"");
            tvten.setText(loaiSach.tenLoai);
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_sach_spinner,null);
        tvthutu=convertView.findViewById(R.id.textView51);
        tvten=convertView.findViewById(R.id.textView52);
        LoaiSach loaiSach=this.getItem(position);
        if(loaiSach!=null){
             tvthutu.setText(loaiSach.maLoai+"");
             tvten.setText(loaiSach.tenLoai);
        }
        return convertView;
    }


}
