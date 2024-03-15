package fpoly.haonaph45181.duanmau.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fpoly.haonaph45181.duanmau.Dao.LoaiSachDAO;
import fpoly.haonaph45181.duanmau.Dao.PhieuMuonDAO;
import fpoly.haonaph45181.duanmau.Dao.SachDAO;
import fpoly.haonaph45181.duanmau.Dao.ThanhVienDao;
import fpoly.haonaph45181.duanmau.Model.LoaiSach;
import fpoly.haonaph45181.duanmau.Model.PhieuMuon;
import fpoly.haonaph45181.duanmau.Model.Sach;
import fpoly.haonaph45181.duanmau.Model.ThanhVien;
import fpoly.haonaph45181.duanmau.R;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.phieumuon> {
    Context context;
    ArrayList<PhieuMuon> phieuMuonArrayList;
    PhieuMuonDAO phieuMuonDAO;
    ThanhVien thanhVien;
    ThanhVienDao thanhVienDao;
    SachDAO sachDAO;
    SimpleDateFormat simpleDateFormat;
    String tensach;
    String tenthanhvien;
    SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");

    public PhieuMuonAdapter(Context context, ArrayList<PhieuMuon> phieuMuonArrayList) {
        this.context = context;
        this.phieuMuonArrayList = phieuMuonArrayList;
        phieuMuonDAO=new PhieuMuonDAO(context);

    }

    @NonNull
    @Override
    public phieumuon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieu_muon,null);
        return new phieumuon(view);
    }

    @Override
    public void onBindViewHolder(@NonNull phieumuon holder, int position) {
        thanhVienDao=new ThanhVienDao(context);

        String p=dateFormat.format(phieuMuonArrayList.get(position).ngay);
        holder.tvngay.setText(p);
        holder.tvma.setText(String.valueOf(phieuMuonArrayList.get(position).maPM));
        String y=String.valueOf(phieuMuonArrayList.get(position).maTV);
        thanhVien =thanhVienDao.getID(y);
        holder.tvten.setText(thanhVien.hoTen);
        sachDAO=new SachDAO(context);
        Sach sach=sachDAO.getID(String.valueOf(phieuMuonArrayList.get(position).maSach));
        holder.tvtensach.setText(sach.tenSach);
        holder.tvtien.setText(String.valueOf(sach.giaThue));

        if(phieuMuonArrayList.get(position).traSach==1){
            holder.tvtra.setText("Đã trả");
        }else{
            holder.tvtra.setText("Chưa trả");
        }
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
                        String tt=String.valueOf(phieuMuonArrayList.get(position).maPM);
                        phieuMuonDAO.delete(tt);
                        phieuMuonArrayList=phieuMuonDAO.getList();
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

            }
        });

    }

    @Override
    public int getItemCount() {
        return phieuMuonArrayList.size();
    }

    class phieumuon extends RecyclerView.ViewHolder {
        TextView tvma,tvtensach,tvten,tvngay,tvtien,tvtra;
        ImageButton imgb;
        Spinner sptentv,sptensach;
        EditText edma;
        TextView tvngaythue,tvtienthue;
        CheckBox checkBox;
        Button btok,bthuy;
        int prince,matv,trasachh,masach;
        String dat;


        public phieumuon(@NonNull View itemView) {
            super(itemView);
           tvma=itemView.findViewById(R.id.textView8);
           tvten=itemView.findViewById(R.id.textView9);
           tvtensach=itemView.findViewById(R.id.textView10);
           tvngay=itemView.findViewById(R.id.textView14);
           tvtien=itemView.findViewById(R.id.textView11);
           tvtra=itemView.findViewById(R.id.textView12);
           imgb=itemView.findViewById(R.id.imageButton);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Dialog dialog=new Dialog(context);
                   dialog.setContentView(R.layout.sua_phieu_muon_dialog);
                   dat=tvngay.getText().toString();
                   edma=dialog.findViewById(R.id.editTextText20);
                   edma.setText(tvma.getText().toString());
                   tvngaythue=dialog.findViewById(R.id.textView65);
                   tvngaythue.setText(dat);
                   tvtienthue=dialog.findViewById(R.id.textView66);
                   prince=Integer.parseInt(tvtien.getText().toString());
                   tvtienthue.setText(String.valueOf(prince));
                   sptensach=dialog.findViewById(R.id.spinner3);
                   sptentv=dialog.findViewById(R.id.spinner4);
                   checkBox=dialog.findViewById(R.id.checkBox3);
                   btok=dialog.findViewById(R.id.button22);
                   bthuy=dialog.findViewById(R.id.button23);


                   thanhVienDao=new ThanhVienDao(context);
                   ArrayList<ThanhVien> thanhVienArrayList=thanhVienDao.getlist();
                   TenThanhVienSpinner tenThanhVienSpinner=new TenThanhVienSpinner(context,thanhVienArrayList);
                   sptentv.setAdapter(tenThanhVienSpinner);
                   sptentv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                       @Override
                       public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                           matv=thanhVienArrayList.get(i).maTV;
                           tenthanhvien=thanhVienArrayList.get(i).hoTen;

                           Toast.makeText(context, "chọn: "+thanhVienArrayList.get(i).hoTen, Toast.LENGTH_SHORT).show();
                       }
                       @Override
                       public void onNothingSelected(AdapterView<?> adapterView) {
                       }
                   });

                   sachDAO=new SachDAO(context);
                   ArrayList<Sach> sachArrayList=sachDAO.getList();
                   SachSpinner sachSpinner=new SachSpinner(context,sachArrayList);
                   sptensach.setAdapter(sachSpinner);
                   sptensach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                       @Override
                       public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                           tensach=sachArrayList.get(i).tenSach;
                           masach=sachArrayList.get(i).maSach;
                           Toast.makeText(context, "chọn: "+sachArrayList.get(i).tenSach, Toast.LENGTH_SHORT).show();
                       }
                       @Override
                       public void onNothingSelected(AdapterView<?> adapterView) {
                       }
                   });

                   btok.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           SharedPreferences preferences= context.getSharedPreferences("userfile", Context.MODE_PRIVATE);String t=preferences.getString("username","");
                         String ma=tvma.getText().toString();
                         PhieuMuon phieuMuon=new PhieuMuon();
                         phieuMuon.maPM=Integer.parseInt(ma);
                           try {
                               phieuMuon.ngay=dateFormat.parse(dat);
                           } catch (ParseException e) {
                               throw new RuntimeException(e);
                           }
                         phieuMuon.maTT=  t;
                         phieuMuon.tienThue=prince;
                         phieuMuon.maTV=matv;
                           if(checkBox.isChecked()){
                               trasachh=1;
                           }else{
                               trasachh=0;
                           }
                         phieuMuon.traSach=trasachh;
                         phieuMuon.maSach= masach;
                         phieuMuonDAO.update(phieuMuon);
                         phieuMuonArrayList=phieuMuonDAO.getList();
                         notifyDataSetChanged();
                         dialog.dismiss();
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
