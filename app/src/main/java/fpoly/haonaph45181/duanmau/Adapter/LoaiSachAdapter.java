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

import fpoly.haonaph45181.duanmau.Dao.LoaiSachDAO;
import fpoly.haonaph45181.duanmau.Model.LoaiSach;
import fpoly.haonaph45181.duanmau.R;

public  class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.loaisach>{

    Context context;
    LoaiSachDAO loaiSachDAO;
    ArrayList<LoaiSach> loaisachArrayList;
    String t;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> loaisachArrayList) {
        this.context = context;
        loaiSachDAO=new LoaiSachDAO(context);
        this.loaisachArrayList = loaisachArrayList;
    }

    @NonNull
    @Override
    public loaisach onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loaisach,null);
        return new loaisach(view);
    }

    @Override
    public void onBindViewHolder(@NonNull loaisach holder, int position) {
        holder.maloai.setText(String.valueOf(loaisachArrayList.get(position).maLoai));
        holder.tenloai.setText(loaisachArrayList.get(position).tenLoai);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiSach loaiSach=new LoaiSach();
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(view.getContext());
                alertDialog.setTitle("thông báo");
                alertDialog.setMessage("bạn có xác nhận muốn xóa");
                alertDialog.setIcon(R.drawable.ic_launcher_foreground);
                alertDialog.setNegativeButton("yes sir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        loaiSach.maLoai=loaisachArrayList.get(position).maLoai;
                        loaiSachDAO.delete(String.valueOf(loaiSach.maLoai));
                        loaisachArrayList=loaiSachDAO.getList();
                        notifyDataSetChanged();
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.setNeutralButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return loaisachArrayList.size();
    }

    class loaisach extends RecyclerView.ViewHolder {
        TextView maloai,tenloai;
        ImageButton button;
        Button ok,huy;
        EditText edma,edten;
        public loaisach(@NonNull View itemView) {
            super(itemView);
            maloai=itemView.findViewById(R.id.textView34);
            tenloai=itemView.findViewById(R.id.textView36);
            button=itemView.findViewById(R.id.imageButton3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    t=maloai.getText().toString();
                    Dialog dialog=new Dialog(itemView.getContext());
                    dialog.setContentView(R.layout.sua_loai_sach_dialog);
                    ok=dialog.findViewById(R.id.button16);
                    huy=dialog.findViewById(R.id.button17);
                    edma=dialog.findViewById(R.id.editTextText12);
                    edten=dialog.findViewById(R.id.editTextText13);
                    edma.setText(t);
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LoaiSach loaiSach=new LoaiSach();
                            if(edten.getText().length()==0 ){
                                Toast.makeText(context, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();

                            }else {
                                loaiSach.maLoai = Integer.parseInt(edma.getText().toString());
                                loaiSach.tenLoai = edten.getText().toString();
                                loaiSachDAO.update(loaiSach);
                                loaisachArrayList = loaiSachDAO.getList();
                                notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        }
                    });

                    huy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            });


        }
    }
}
