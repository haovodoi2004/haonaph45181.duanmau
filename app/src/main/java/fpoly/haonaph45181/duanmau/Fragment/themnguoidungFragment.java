package fpoly.haonaph45181.duanmau.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import fpoly.haonaph45181.duanmau.Dao.ThuThuDAO;
import fpoly.haonaph45181.duanmau.Model.ThuThu;
import fpoly.haonaph45181.duanmau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link themnguoidungFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class themnguoidungFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public themnguoidungFragment() {
        // Required empty public constructor
    }

EditText edname,edmk,edma;
Button btok;
ThuThu thuThu;
ThuThuDAO thuThuDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_themnguoidung, container, false);
        edname=view.findViewById(R.id.editTextText22);
        edmk=view.findViewById(R.id.editTextText23);
        edma=view.findViewById(R.id.editTextText21);
        btok=view.findViewById(R.id.button24);
        btok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuThuDAO=new ThuThuDAO(getContext());
                thuThu=new ThuThu();
                String t=edma.getText().toString();
                String tt=edname.getText().toString();
                String ttt=edmk.getText().toString();
                thuThu.maTT=t;
                thuThu.hoTen=tt;
                thuThu.matKhau=ttt;
                if(edma.getText().length()==0 || edmk.getText().length()==0 || edname.getText().length()==0){
                    Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();

                }else {
                    thuThuDAO.insert(thuThu);
                }
                thuThuDAO.insert(thuThu);

            }
        });

        return view;
    }

}