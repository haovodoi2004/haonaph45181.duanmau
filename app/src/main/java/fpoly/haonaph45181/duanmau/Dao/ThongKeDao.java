package fpoly.haonaph45181.duanmau.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.haonaph45181.duanmau.Database.DPHelper;
import fpoly.haonaph45181.duanmau.Model.Sach;
import fpoly.haonaph45181.duanmau.Model.Top;

public class ThongKeDao {
    Context context;
    DPHelper dpHelper;

    public ThongKeDao(Context context) {
        this.context = context;
        dpHelper=new DPHelper(context);
    }

    public ArrayList<Top> getTop(){
        String sqlTop="SELECT maSach,count(maSach) as soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";
        ArrayList<Top> arrayList=new ArrayList();
        SachDAO sachDao=new SachDAO(context);
        Cursor cursor=dpHelper.getWritableDatabase().rawQuery(sqlTop,null);
        while (cursor.moveToNext()){
            Top top=new Top();
            Sach sach=sachDao.getID(cursor.getString(cursor.getColumnIndex("maSach")));
            top.tensach=sach.tenSach;
            top.soluong=Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong")));
            arrayList.add(top);

        }
        return arrayList;
    }
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
       String sqlDoanhThu="SELECT SUM(tienThue) as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
       ArrayList<Integer> arrayList=new ArrayList<>();
       Cursor cursor=dpHelper.getWritableDatabase().rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});
       while (cursor.moveToNext()){
           try{
               arrayList.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
           }catch (Exception e){
               arrayList.add(0);
           }
       }
       return arrayList.get(0);
    }
}
