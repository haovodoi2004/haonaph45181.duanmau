package fpoly.haonaph45181.duanmau.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.haonaph45181.duanmau.Adapter.LoaiSachAdapter;
import fpoly.haonaph45181.duanmau.Dao.LoaiSachDAO;
import fpoly.haonaph45181.duanmau.Model.LoaiSach;
import fpoly.haonaph45181.duanmau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link quanlyloaisachFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class quanlyloaisachFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public quanlyloaisachFragment() {
        // Required empty public constructor
    }

   RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    EditText maloaisach,tenloaisach;
    Button ok,huy;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    LoaiSachAdapter loaiSachAdapter;
    ArrayList<LoaiSach> loaiSachArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_quanlyloaisach, container, false);
       recyclerView=view.findViewById(R.id.recyloaisach);
       floatingActionButton=view.findViewById(R.id.addloaisach);
       loaiSachDAO=new LoaiSachDAO(getContext());
       loaiSachArrayList=loaiSachDAO.getList();
        SearchView srview = view.findViewById(R.id.searchSach);

        loaiSachAdapter=new LoaiSachAdapter(getContext(),loaiSachArrayList);
       recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
       recyclerView.setAdapter(loaiSachAdapter);
       floatingActionButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Dialog dialog=new Dialog(getContext());
               dialog.setContentView(R.layout.them_loai_sach);
               maloaisach=dialog.findViewById(R.id.editTextText10);
               tenloaisach=dialog.findViewById(R.id.editTextText11);
               ok=dialog.findViewById(R.id.button14);
               huy=dialog.findViewById(R.id.button15);
               ok.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       loaiSach=new LoaiSach();
                       if(tenloaisach.getText().length()==0){

                           Toast.makeText(getContext(), "bạn không được bỏ trống", Toast.LENGTH_SHORT).show();

                       }if(tenloaisach.getText().length()>5&&tenloaisach.getText().length()<20){
                           loaiSach.tenLoai = tenloaisach.getText().toString();
                           loaiSachDAO.insert(loaiSach);
                           loaiSachArrayList = loaiSachDAO.getList();
                           loaiSachAdapter = new LoaiSachAdapter(getContext(), loaiSachArrayList);
                           recyclerView.setAdapter(loaiSachAdapter);
                           Toast.makeText(getContext(), "Thêm thành công loại sách " + loaiSach.tenLoai, Toast.LENGTH_SHORT).show();
                           dialog.dismiss();
                       }else {
                           Toast.makeText(getContext(), "nhập ko đúng số lượng", Toast.LENGTH_SHORT).show();
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
                ArrayList<LoaiSach> newlist = new ArrayList<>();

                for (LoaiSach s :  loaiSachArrayList){
                    if (s.getTenLoai().toLowerCase().contains(newText.toLowerCase())){
                        newlist.add(s);
                    }
                }

                loaiSachAdapter = new LoaiSachAdapter(getContext(), newlist);
                recyclerView.setAdapter(loaiSachAdapter);
                return false;
            }
        });
        return view;
    }
}