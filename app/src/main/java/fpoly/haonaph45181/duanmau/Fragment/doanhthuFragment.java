package fpoly.haonaph45181.duanmau.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import fpoly.haonaph45181.duanmau.Dao.ThongKeDao;
import fpoly.haonaph45181.duanmau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link doanhthuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class doanhthuFragment extends Fragment {

 EditText edtungay,eddenngay;
 Button bttungay,btdenngay,btdoanhthu;
 int mYear, mMonth,mDay;
 TextView tvdoanhthu;
 ThongKeDao thongKeDao;
 SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_doanhthu,container,false);
        edtungay=view.findViewById(R.id.editTextText2);
        eddenngay=view.findViewById(R.id.editTextText3);
        bttungay=view.findViewById(R.id.button9);
        btdenngay=view.findViewById(R.id.button8);
        btdoanhthu=view.findViewById(R.id.button7);
        tvdoanhthu=view.findViewById(R.id.textView17);
        thongKeDao=new ThongKeDao(getContext());
        bttungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                mYear=calendar.get(Calendar.YEAR);
                mMonth=calendar.get(Calendar.MONTH);
                mDay=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(),0,onDateSetListenertungay,mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

        btdenngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                mYear=calendar.get(Calendar.YEAR);
                mMonth=calendar.get(Calendar.MONTH);
                mDay=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),0,onDateSetListenerdenngay,mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

        btdoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t=edtungay.getText().toString();
                String tt=eddenngay.getText().toString();
                int tong=thongKeDao.getDoanhThu(t,tt);
                tvdoanhthu.setText(String.valueOf(tong));
            }
        });

        return view;
    }
    DatePickerDialog.OnDateSetListener onDateSetListenertungay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int date) {
           mDay=date;
           mMonth=month;
           mYear=year;
            GregorianCalendar calendar=new GregorianCalendar(mYear, mMonth, mDay);

           edtungay.setText(simpleDateFormat.format(calendar.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener onDateSetListenerdenngay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int date) {
            mDay=date;
            mMonth=month;
            mYear=year;
            GregorianCalendar calendar=new GregorianCalendar(mYear, mMonth, mDay);
            eddenngay.setText(simpleDateFormat.format(calendar.getTime()));
        }
    };
}