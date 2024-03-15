package fpoly.haonaph45181.duanmau.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fpoly.haonaph45181.duanmau.Adapter.ThanhVienAdapter;
import fpoly.haonaph45181.duanmau.Model.ThanhVien;
import fpoly.haonaph45181.duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import fpoly.haonaph45181.duanmau.Dao.ThanhVienDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link quanlythanhvienFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class quanlythanhvienFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public quanlythanhvienFragment() {
        // Required empty public constructor
    }

  FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    ThanhVienAdapter thanhVienAdapter;
    ArrayList<ThanhVien> arr;
    ThanhVienDao thanhviendao;
    EditText tentv,namsinh;
    Button add,cancle;
    ThanhVienDao getThanhviendao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_quanlythanhvien, container, false);
        floatingActionButton=view.findViewById(R.id.addtv);
        thanhviendao =new ThanhVienDao(getContext());
        arr=thanhviendao.getlist();
        getThanhviendao=new ThanhVienDao(getContext());
        recyclerView =view.findViewById(R.id.recy);
        thanhVienAdapter=new ThanhVienAdapter(getContext(),arr);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(thanhVienAdapter);
        floatingActionButton.setOnClickListener(v->{
            Dialog dialog=new Dialog(getContext());
            dialog.setTitle("xin chào ace");
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.quan_y_thanhvien_dialog);
            tentv=dialog.findViewById(R.id.editTextText5);
            namsinh=dialog.findViewById(R.id.editTextText6);
            add=dialog.findViewById(R.id.button10);
            cancle=dialog.findViewById(R.id.button11);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String t=tentv.getText().toString();
                    String tt=namsinh.getText().toString();
                    if(tentv.getText().length()==0||namsinh.getText().length()==0){
                        Toast.makeText(getContext(), "bạn không được để trống", Toast.LENGTH_SHORT).show();
                    }else{
                        if(isValidDate((tt),"dd-MM-yyyy")){
                            getThanhviendao.insert(t,tt);
                            Toast.makeText(getContext(), "Thêm thành công nhân viên "+t, Toast.LENGTH_SHORT).show();
                            setup();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getContext(), "bạn nhập sai ngày tháng năm", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
            cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        });


        return view;
    }

    public void setup(){
        arr=getThanhviendao.getlist();
        thanhVienAdapter=new ThanhVienAdapter(getContext(),arr);
      recyclerView.setAdapter(thanhVienAdapter);
    }
    private boolean isValidDate(String input, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        try {
            Date date = dateFormat.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}