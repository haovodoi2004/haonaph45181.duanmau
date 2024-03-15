package fpoly.haonaph45181.duanmau.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.haonaph45181.duanmau.Adapter.TopAdapter;
import fpoly.haonaph45181.duanmau.Dao.ThongKeDao;
import fpoly.haonaph45181.duanmau.Model.Top;
import fpoly.haonaph45181.duanmau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link top10Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class top10Fragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Top> topArrayList;
    ThongKeDao thongKeDao;
    TopAdapter topAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_top10,container,false);
        thongKeDao=new ThongKeDao(getContext());
        topArrayList=thongKeDao.getTop();
        topAdapter=new TopAdapter(getContext(),topArrayList);
        recyclerView=view.findViewById(R.id.thongketop10);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(topAdapter);
        return view;
    }
}