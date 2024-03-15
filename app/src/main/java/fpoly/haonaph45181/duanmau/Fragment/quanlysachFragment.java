package fpoly.haonaph45181.duanmau.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.haonaph45181.duanmau.Adapter.LoaiSachAdapter;
import fpoly.haonaph45181.duanmau.Adapter.SachAdapter;
import fpoly.haonaph45181.duanmau.Dao.LoaiSachDAO;
import fpoly.haonaph45181.duanmau.Dao.SachDAO;
import fpoly.haonaph45181.duanmau.Model.LoaiSach;
import fpoly.haonaph45181.duanmau.Model.Sach;
import fpoly.haonaph45181.duanmau.R;
import fpoly.haonaph45181.duanmau.Adapter.LoaiSachSpinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link quanlysachFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class quanlysachFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public quanlysachFragment() {
        // Required empty public constructor
    }

 RecyclerView recyclerView;
    FloatingActionButton actionButton;
    SachAdapter sachAdapter;
    ArrayList<Sach> sachArrayList;
    SachDAO  sachDAO;
    EditText edtensach,edgiathue,edsotrang;
    Spinner spinner;
    Button ok,huy;
    LoaiSachSpinner loaisachspinner;
    LoaiSachDAO loaiSachDAO;
    ArrayList<LoaiSach> loaiSachArrayList;
    int maloai;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_quanlysach, container, false);
        recyclerView=view.findViewById(R.id.recysach);
        actionButton=view.findViewById(R.id.btthemsach);
        sachDAO=new SachDAO(getContext());
        sachArrayList=sachDAO.getList();
        SearchView srview = view.findViewById(R.id.searchSach2);
        loaiSachDAO=new LoaiSachDAO(getContext());
        loaiSachArrayList=loaiSachDAO.getList();
        loaisachspinner =new LoaiSachSpinner(getContext(),loaiSachArrayList);
        sachAdapter=new SachAdapter(getContext(),sachArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(sachAdapter);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.them_sach_dialog);
                edtensach=dialog.findViewById(R.id.editTextText15);
                edgiathue=dialog.findViewById(R.id.editTextText16);
                spinner=dialog.findViewById(R.id.spinner);
                edsotrang=dialog.findViewById(R.id.editTextText24);
                ok=dialog.findViewById(R.id.button18);
                huy=dialog.findViewById(R.id.button19);

                spinner.setAdapter(loaisachspinner);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        maloai=loaiSachArrayList.get(i).maLoai;
                        Toast.makeText(getContext(), "chọn: "+loaiSachArrayList.get(i).tenLoai, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Sach sach=new Sach();
                        String t=edgiathue.getText().toString();
                        String tt=edsotrang.getText().toString();
                        if(edgiathue.getText().length()==0||edtensach.getText().length()==0){
                            Toast.makeText(getContext(), "bạn không được bỏ trống", Toast.LENGTH_SHORT).show();
                        }else{
                        if(TextUtils.isDigitsOnly(t)){
                            sach.giaThue=Integer.parseInt(t);
                            sach.tenSach=edtensach.getText().toString();
                            sach.maLoai=maloai;
                            sach.sotrang=Integer.parseInt(tt);
                            sachDAO.insert(sach);
                            sachArrayList=sachDAO.getList();
                            sachAdapter=new SachAdapter(getContext(),sachArrayList);
                            recyclerView.setAdapter(sachAdapter);
                            Toast.makeText(getContext(), "Thêm thành công sách "+ sach.tenSach, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getContext(), "bạn nhập sai định dạng số", Toast.LENGTH_SHORT).show();
                        }
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
        srview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Sach> newlist = new ArrayList<>();

                for (Sach s :  sachArrayList){
                    String r=String.valueOf(s.getSotrang());
                    if (r.toLowerCase().contains(newText.toLowerCase())){
                        if(s.sotrang>=Integer.parseInt(r)){
                            newlist.add(s);
                        }

                    }
                }

                sachAdapter = new SachAdapter(getContext(),newlist);
                recyclerView.setAdapter(sachAdapter);
                return false;
            }
        });
        return view;
    }
}