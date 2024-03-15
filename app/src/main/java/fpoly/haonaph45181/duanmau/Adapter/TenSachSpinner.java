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

import fpoly.haonaph45181.duanmau.Model.Sach;
import fpoly.haonaph45181.duanmau.R;

public class TenSachSpinner extends ArrayAdapter<Sach> {
    Context context;
    ArrayList<Sach>  sachArrayList;
    TextView txtt,txten;

    public TenSachSpinner(@NonNull Context context,  ArrayList<Sach> sachArrayList) {
        super(context, 0,sachArrayList);
        this.context = context;
        this.sachArrayList = sachArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tensachspinner,null);
        txtt=convertView.findViewById(R.id.textView53);
        txten=convertView.findViewById(R.id.textView54);
        Sach sach=this.getItem(position);
        if(sach!=null){
            txtt.setText(String.valueOf(sach.maSach));
            txten.setText(sach.tenSach);
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tensachspinner,null);
        txtt=convertView.findViewById(R.id.textView53);
        txten=convertView.findViewById(R.id.textView54);
        Sach sach=this.getItem(position);
        if(sach!=null){
            txtt.setText(sach.maSach+"");
            txten.setText(sach.tenSach);
        }
        return convertView;
    }
}
