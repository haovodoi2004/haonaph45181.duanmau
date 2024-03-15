package fpoly.haonaph45181.duanmau.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.haonaph45181.duanmau.Model.ThanhVien;
import fpoly.haonaph45181.duanmau.R;
import fpoly.haonaph45181.duanmau.Dao.ThanhVienDao;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.thanhvien> {
    Context context;
    ArrayList<ThanhVien> arr;
    ThanhVienDao thanhviendao;
    String y;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> arr) {
        this.context = context;
        this.arr = arr;
        thanhviendao = new ThanhVienDao(context);
    }
    @NonNull
    @Override
    public thanhvien onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thanh_vien,null);
        return new thanhvien(view);
    }

    @Override
    public void onBindViewHolder(@NonNull thanhvien holder, int position) {
         holder.tvma.setText(String.valueOf(arr.get(position).maTV));
         holder.tvname.setText(arr.get(position).hoTen);
         holder.tvnamsinh.setText(arr.get(position).namSinh);

         holder.imb.setOnClickListener(v->{
             AlertDialog.Builder alertDialog=new AlertDialog.Builder(v.getContext());
             alertDialog.setTitle("thông báo");
             alertDialog.setMessage("bạn có xác nhận muốn xóa");
             alertDialog.setIcon(R.drawable.ic_launcher_foreground);
             alertDialog.setNegativeButton("yes sir", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                     int t=arr.get(position).maTV;
                     thanhviendao.delete(t);
                     arr=thanhviendao.getlist();
                     notifyDataSetChanged();

                 }
             });
             alertDialog.setNeutralButton("no", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {

                 }
             });
             alertDialog.show();
             notifyDataSetChanged();
         });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class thanhvien extends RecyclerView.ViewHolder {
        TextView tvma,tvname,tvnamsinh;
        ImageButton imb;
        EditText name,since,id;
        Button ok,huy;
        public thanhvien(@NonNull View itemView) {

            super(itemView);
            tvma=itemView.findViewById(R.id.textView22);
            tvname=itemView.findViewById(R.id.textView23);
            tvnamsinh=itemView.findViewById(R.id.textView24);
            imb=itemView.findViewById(R.id.imageButton2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    y=  tvma.getText().toString();
                    Dialog dialog=new Dialog(itemView.getContext());
                    dialog.setContentView(R.layout.sua_thong_tin_nguoi_dung);
                    id=dialog.findViewById(R.id.editTextText7);
                    name=dialog.findViewById(R.id.editTextText8);
                    since=dialog.findViewById(R.id.editTextText9);
                    ok=dialog.findViewById(R.id.button12);
                    huy=dialog.findViewById(R.id.button13);
                    id.setText(y);
                    Toast.makeText(itemView.getContext(), "", Toast.LENGTH_SHORT).show();
                    huy.setOnClickListener(v->{
                        dialog.dismiss();
                    });
                    ok.setOnClickListener(v->{
                        int t=Integer.parseInt(y);
                        String tt=name.getText().toString();
                        String ttt=since.getText().toString();
                        if(name.getText().length()==0 || since.getText().length()==0 ){
                            Toast.makeText(context, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();

                        }else {
                            thanhviendao.update(t,tt,ttt);
                            arr=thanhviendao.getlist();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }


                    });
                    dialog.show();
                }
            });

        }
    }
}
