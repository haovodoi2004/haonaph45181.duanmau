package fpoly.haonaph45181.duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.haonaph45181.duanmau.Dao.ThongKeDao;
import fpoly.haonaph45181.duanmau.Model.Top;
import fpoly.haonaph45181.duanmau.R;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.top>{

    Context context;
    ArrayList<Top> topArrayList;
    ThongKeDao thongKeDao;

    public TopAdapter(Context context, ArrayList<Top> topArrayList) {
        this.context = context;
        this.topArrayList = topArrayList;
        thongKeDao =new ThongKeDao(context);
    }

    @NonNull
    @Override
    public top onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top10,null);
        return new top(view);
    }

    @Override
    public void onBindViewHolder(@NonNull top holder, int position) {
        holder.tvsoluong.setText(String.valueOf(topArrayList.get(position).soluong));
        holder.tvtensach.setText(topArrayList.get(position).tensach);
    }

    @Override
    public int getItemCount() {
        return topArrayList.size();
    }

    class top extends RecyclerView.ViewHolder {
        TextView tvsoluong,tvtensach;
        public top(@NonNull View itemView) {
            super(itemView);
            tvsoluong=itemView.findViewById(R.id.textView57);
            tvtensach=itemView.findViewById(R.id.textView58);
        }
    }
}
