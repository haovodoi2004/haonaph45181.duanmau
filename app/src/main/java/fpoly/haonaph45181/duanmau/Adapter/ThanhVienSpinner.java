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
import fpoly.haonaph45181.duanmau.Model.ThanhVien;
import fpoly.haonaph45181.duanmau.R;

public class ThanhVienSpinner extends ArrayAdapter<ThanhVien> {
    Context context;
    ArrayList<ThanhVien> thanhVienArrayList;
    TextView tvten,tvthutu;


    public ThanhVienSpinner(@NonNull Context context, ArrayList<ThanhVien> thanhVienArrayList) {
        super(context, 0,thanhVienArrayList);
        this.context = context;
        this.thanhVienArrayList = thanhVienArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_sach_spinner,null);
        tvten=convertView.findViewById(R.id.textView68);
        tvthutu=convertView.findViewById(R.id.textView67);
        ThanhVien thanhVien=this.getItem(position);
        if(thanhVien!=null){
            tvthutu.setText(thanhVien.maTV+"");
            tvten.setText(thanhVien.hoTen);
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_sach_spinner,null);
        tvten=convertView.findViewById(R.id.textView68);
        tvthutu=convertView.findViewById(R.id.textView67);
        ThanhVien thanhVien=this.getItem(position);
        if(thanhVien!=null){
            tvthutu.setText(thanhVien.maTV+"");
            tvten.setText(thanhVien.hoTen);
        }
        return convertView;
    }
}
