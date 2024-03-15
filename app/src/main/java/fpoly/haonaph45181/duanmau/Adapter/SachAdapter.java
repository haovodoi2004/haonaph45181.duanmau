package fpoly.haonaph45181.duanmau.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.haonaph45181.duanmau.Dao.LoaiSachDAO;
import fpoly.haonaph45181.duanmau.Dao.SachDAO;
import fpoly.haonaph45181.duanmau.Model.LoaiSach;
import fpoly.haonaph45181.duanmau.Model.Sach;
import fpoly.haonaph45181.duanmau.R;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.sach>{
    Context context;
    ArrayList<Sach> sachArrayList;
    LoaiSachDAO loaiSachDAO;
    SachDAO sachDAO;
    Sach sach;
    String t,tt;
    int loai;



    public SachAdapter(Context context, ArrayList<Sach> sachArrayList) {
        this.context = context;
        this.sachArrayList = sachArrayList;
        sachDAO=new SachDAO(context);
    }

    @NonNull
    @Override
    public sach onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach,null);
        return new sach(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sach holder, int position) {
          loaiSachDAO=new LoaiSachDAO(context);
          sach=sachArrayList.get(position);
          holder.tvma.setText(String.valueOf(sachArrayList.get(position).maSach));
          holder.tvten.setText(sachArrayList.get(position).tenSach);
          t=String.valueOf(sachArrayList.get(position).maSach);
          holder.tvgia.setText(String.valueOf(sachArrayList.get(position).giaThue));
          LoaiSach loaiSach=loaiSachDAO.getID(String.valueOf(sach.maLoai));
          holder.tvloai.setText(loaiSach.tenLoai);
              holder.tvsotrang.setText(String.valueOf(sachArrayList.get(position).sotrang));
          holder.imgb.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  AlertDialog.Builder alertDialog=new AlertDialog.Builder(view.getContext());
                  alertDialog.setTitle("thông báo");
                  alertDialog.setMessage("bạn có xác nhận muốn xóa");
                  alertDialog.setIcon(R.drawable.ic_launcher_foreground);
               alertDialog.setNegativeButton("yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                       sachDAO.delete(t);
                       sachArrayList=sachDAO.getList();
                       notifyDataSetChanged();
                   }
               });
               alertDialog.setNeutralButton("no", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       dialogInterface.dismiss();
                   }
               });
                  alertDialog.show();
                 sachArrayList=sachDAO.getList();
                 notifyDataSetChanged();
              }
          });
    }

    @Override
    public int getItemCount() {
        return sachArrayList.size();
    }

    class sach extends RecyclerView.ViewHolder {
        TextView tvma,tvten,tvloai,tvgia,tvsotrang;
        ImageButton imgb;
        EditText edname,edgia,edma;
        Button btok,bthuy;
        Spinner spinner;


        public sach(@NonNull View itemView) {
            super(itemView);
            tvma=itemView.findViewById(R.id.textView39);
            tvten=itemView.findViewById(R.id.textView41);
            tvloai=itemView.findViewById(R.id.textView44);
            tvgia=itemView.findViewById(R.id.textView42);
            tvsotrang=itemView.findViewById(R.id.textView71);
            imgb=itemView.findViewById(R.id.imageButton4);
                    itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog=new Dialog(context);
                    dialog.setContentView(R.layout.sua_sach_dialog);
                    bthuy=dialog.findViewById(R.id.button21);
                    btok=dialog.findViewById(R.id.button20);
                    bthuy=dialog.findViewById(R.id.button21);
                    edname=dialog.findViewById(R.id.editTextText18);
                    edgia=dialog.findViewById(R.id.editTextText19);
                    edma=dialog.findViewById(R.id.editTextText17);
                    spinner=dialog.findViewById(R.id.spinner2);
                    edma.setText(t);
                     loaiSachDAO=new LoaiSachDAO(context);
                    ArrayList<LoaiSach> loaiSachArrayList=loaiSachDAO.getList();
                    LoaiSachSpinner sachSpinner=new LoaiSachSpinner(context,loaiSachArrayList);
                    spinner.setAdapter(sachSpinner);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                              loai=loaiSachArrayList.get(i).maLoai;
                            Toast.makeText(context, "chọn: "+loaiSachArrayList.get(i).tenLoai, Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    btok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String name=edname.getText().toString();
                            String gia=edgia.getText().toString();
                            String d=edma.getText().toString();
                            if(edname.getText().length()==0 || edgia.getText().length()==0 ){
                                Toast.makeText(context, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();

                            }else{
                                if(gia.matches("\\d+")&&edname.getText().length()!=0 && edgia.getText().length()!=0) {
                                    Sach sach1=new Sach();
                                    sach1.maSach=Integer.parseInt(d);
                                    sach1.tenSach=name;
                                    sach1.giaThue=Integer.parseInt(gia);
                                    sach1.maLoai=loai;
                                    sachDAO.update(sach1);
                                    sachArrayList=sachDAO.getList();
                                    notifyDataSetChanged();
                                    dialog.dismiss();
                                }else{
                                    Toast.makeText(context, "bạn đã nhập sai định dạng", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    });

                    bthuy.setOnClickListener(new View.OnClickListener() {
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
