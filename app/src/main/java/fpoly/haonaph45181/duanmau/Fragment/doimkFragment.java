package fpoly.haonaph45181.duanmau.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import fpoly.haonaph45181.duanmau.Dao.ThuThuDAO;
import fpoly.haonaph45181.duanmau.Model.ThuThu;
import fpoly.haonaph45181.duanmau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link doimkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class doimkFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public doimkFragment() {
        // Required empty public constructor
    }

   TextInputEditText edmkcu,edmkmoi,ednhaplaimk;
    Button ok,huy;
    ThuThuDAO thuThuDAO;
    ThuThu thuThu;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_doimk, container, false);

        edmkcu=view.findViewById(R.id.textInput3);
        edmkmoi=view.findViewById(R.id.textInput4);
        ednhaplaimk=view.findViewById(R.id.textInput5);
        ok=view.findViewById(R.id.button4);
        huy=view.findViewById(R.id.button3);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                thuThuDAO=new ThuThuDAO(getContext());

                if(edmkcu.getText().length()==0||edmkmoi.getText().length()==0||ednhaplaimk.getText().length()==0){
                    Toast.makeText(getContext(), "bạn không được bỏ trống", Toast.LENGTH_SHORT).show();
                }else {
                   sharedPreferences= getContext().getSharedPreferences("userfile",Context.MODE_PRIVATE);
                    String user=sharedPreferences.getString("username","");
                    String passold=sharedPreferences.getString("password","");
                    String t=edmkcu.getText().toString();
                    String pass=edmkmoi.getText().toString();
                    String repass=ednhaplaimk.getText().toString();
                    if (passold.equals(t)) {
                        thuThu=thuThuDAO.getID(user);
                        thuThu.matKhau=pass;
                        thuThuDAO.updatePass(thuThu);
                        Toast.makeText(getContext(), "Đơi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "nhập lại mật khẩu kông trùng khớp"+pass, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
}