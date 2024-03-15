package fpoly.haonaph45181.duanmau.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fpoly.haonaph45181.duanmau.Adapter.PhieuMuonAdapter;
import fpoly.haonaph45181.duanmau.Adapter.SachAdapter;
import fpoly.haonaph45181.duanmau.Adapter.TenSachSpinner;
import fpoly.haonaph45181.duanmau.Adapter.TenThanhVienSpinner;
import fpoly.haonaph45181.duanmau.Dao.PhieuMuonDAO;
import fpoly.haonaph45181.duanmau.Dao.SachDAO;
import fpoly.haonaph45181.duanmau.Dao.ThanhVienDao;
import fpoly.haonaph45181.duanmau.Dao.ThuThuDAO;
import fpoly.haonaph45181.duanmau.Model.PhieuMuon;
import fpoly.haonaph45181.duanmau.Model.Sach;
import fpoly.haonaph45181.duanmau.Model.ThanhVien;
import fpoly.haonaph45181.duanmau.Model.ThuThu;
import fpoly.haonaph45181.duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link quanlyphieumuonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class quanlyphieumuonFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public quanlyphieumuonFragment() {
        // Required empty public constructor
    }

    FloatingActionButton floatingActionButton;
    EditText edma;
    Spinner spmatv,spmasach;
    CheckBox checkBox;
    PhieuMuonDAO phieuMuonDAO;
    TenThanhVienSpinner tenThanhVienSpinner;
    ThanhVienDao thanhVienDao;
    ArrayList<ThanhVien> thanhVienArrayList;
    SachDAO sachDAO;
    ArrayList<Sach> sachArrayList;
    TenSachSpinner tenSachSpinner;
    Button ok,huy;
    int matv,mas,tienthuesach,trasachh;
    SimpleDateFormat dateFormat;
    RecyclerView recyclerView;
    PhieuMuonAdapter phieuMuonAdapter;
    ArrayList<PhieuMuon> phieuMuonArrayList;
    TextView tvngay,tvgia;
    SharedPreferences sharedPreferences;


    ArrayList<ThuThu> thuThuArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quanlyphieumuon, container, false);
        floatingActionButton = view.findViewById(R.id.floatpm);
        recyclerView=view.findViewById(R.id.recy);

        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        SharedPreferences preferences= getActivity().getSharedPreferences("userfile", Context.MODE_PRIVATE);
        String t=preferences.getString("username","");

        thanhVienDao=new ThanhVienDao(getContext());
        sachDAO =new SachDAO(getContext());
        dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        thanhVienArrayList=thanhVienDao.getlist();
        tenThanhVienSpinner=new TenThanhVienSpinner(getContext(),thanhVienArrayList);
        sachArrayList =sachDAO.getList();
        tenSachSpinner=new TenSachSpinner(getContext(),sachArrayList);
        phieuMuonDAO=new PhieuMuonDAO(getContext());
        phieuMuonArrayList=phieuMuonDAO.getList();
        phieuMuonAdapter=new PhieuMuonAdapter(getContext(),phieuMuonArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(phieuMuonAdapter);
        floatingActionButton.setOnClickListener(v -> {
            Dialog dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.phieu_muon_dialog);
            edma=dialog.findViewById(R.id.editTextText);
            spmatv=dialog.findViewById(R.id.spmatv);
            spmasach=dialog.findViewById(R.id.spmasach);
            checkBox=dialog.findViewById(R.id.checkBox2);
            ok=dialog.findViewById(R.id.button5);
            tvngay=dialog.findViewById(R.id.textView15);
            tvgia=dialog.findViewById(R.id.textView16);
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            tvngay.setText("Ngày thuê"+dateFormat.format(new Date()));
            huy=dialog.findViewById(R.id.button6);
            spmasach.setAdapter(tenSachSpinner);
            spmatv.setAdapter(tenThanhVienSpinner);
            spmasach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    tienthuesach=sachArrayList.get(i).giaThue;
                    mas=sachArrayList.get(i).maLoai;
                    tvgia.setText("Giá: "+tienthuesach);
                    Toast.makeText(getContext(), "bạn đã chọn: "+sachArrayList.get(i).tenSach, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            spmatv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    matv=thanhVienArrayList.get(i).maTV;
                    Toast.makeText(getContext(), "bạn đã chọn: "+thanhVienArrayList.get(i).hoTen, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PhieuMuon phieuMuon=new PhieuMuon();
                    phieuMuon.maSach=mas;
                    phieuMuon.maTV=matv;
                    phieuMuon.ngay=new java.sql.Date(new Date().getTime());
                    phieuMuon.tienThue=tienthuesach;
                    phieuMuon.maTT=t;
                    if(checkBox.isChecked()){
                        trasachh=1;
                    }else{
                        trasachh=0;
                    }
                    phieuMuon.traSach=trasachh;
                    phieuMuonDAO.insert(phieuMuon);
                    phieuMuonArrayList=phieuMuonDAO.getList();
                    phieuMuonAdapter=new PhieuMuonAdapter(getContext(),phieuMuonArrayList);
                    recyclerView.setAdapter(phieuMuonAdapter);
                }
            });

            dialog.show();
        });
        return view;
    }
}
//txtgioitinh.settextcolor(color.parstcolor(#ffffff))