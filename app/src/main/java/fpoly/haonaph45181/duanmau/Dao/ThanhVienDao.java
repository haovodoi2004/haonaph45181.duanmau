package fpoly.haonaph45181.duanmau.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import fpoly.haonaph45181.duanmau.Database.DPHelper;
import fpoly.haonaph45181.duanmau.Model.Sach;
import fpoly.haonaph45181.duanmau.Model.ThanhVien;
import java.util.ArrayList;

public class ThanhVienDao {
    Context context;
   DPHelper dpHelper;
   SQLiteDatabase sqLiteDatabase;


    public ThanhVienDao(Context context) {

        dpHelper=new DPHelper(context);
        sqLiteDatabase=dpHelper.getWritableDatabase();

    }

    public void insert(String t, String tt){
        SQLiteDatabase database=dpHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("hoTen",t);
        values.put("namSinh",tt);
        database.insert("THANHVIEN",null,values);
    }

    public void delete(int t){

        SQLiteDatabase database=dpHelper.getWritableDatabase();
        database.delete("THANHVIEN","maTV=?",new String[]{String.valueOf(t)});

    }

    public void update(int t,String tt,String ttt){
        SQLiteDatabase database=dpHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("hoTen",tt);
        values.put("namSinh",ttt);
        database.update("THANHVIEN",values,"maTV=?",new String[]{String.valueOf(t)});
    }

    public ArrayList<ThanhVien> getlist(){
        String t="SELECT * FROM THANHVIEN";
        return getall(t);
    }

    public ArrayList<ThanhVien> getall(String sql,String...AStrings){
        ArrayList<ThanhVien> list=new ArrayList<>();
        Cursor c=sqLiteDatabase.rawQuery(sql,AStrings);
        while(c.moveToNext()){
            ThanhVien tv=new ThanhVien();
            tv.maTV=Integer.parseInt(c.getString(c.getColumnIndex("maTV")));
            tv.hoTen=c.getString(c.getColumnIndex("hoTen"));
            tv.namSinh=c.getString(c.getColumnIndex("namSinh"));
            list.add(tv);
        }
        return list;

    }

    public ThanhVien getID(String id){
        String sql="SELECT * FROM THANHVIEN WHERE maTV=?";
        ArrayList<ThanhVien> list=getall(sql,id);
        return list.get(0);
    }

}
