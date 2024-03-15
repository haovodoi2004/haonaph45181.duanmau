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

import fpoly.haonaph45181.duanmau.Model.ThanhVien;
import fpoly.haonaph45181.duanmau.R;

public class TenThanhVienSpinner extends ArrayAdapter<ThanhVien> {
   ArrayList<ThanhVien> thanhVienArrayList;

   Context context;
    TextView txtt,txten;

    public TenThanhVienSpinner(@NonNull Context context, ArrayList<ThanhVien> thanhVienArrayList) {
        super(context, 0,thanhVienArrayList);
        this.thanhVienArrayList = thanhVienArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tenthanhvien_spinner,null);
        txtt=convertView.findViewById(R.id.textView55);
        txten=convertView.findViewById(R.id.textView56);
        ThanhVien thanhVien=this.getItem(position);
        if(thanhVien!=null){
            txtt.setText(String.valueOf(thanhVien.maTV));
            txten.setText(thanhVien.hoTen);
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tenthanhvien_spinner,null);
        txtt=convertView.findViewById(R.id.textView55);
        txten=convertView.findViewById(R.id.textView56);
        ThanhVien thanhVien=this.getItem(position);
        if(thanhVien!=null){
            txtt.setText(thanhVien.maTV+"");
            txten.setText(thanhVien.hoTen);
        }
        return convertView;
    }
}
