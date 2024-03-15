package fpoly.haonaph45181.duanmau.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import fpoly.haonaph45181.duanmau.Database.DPHelper;
import fpoly.haonaph45181.duanmau.Model.Sach;

import java.util.ArrayList;

public class SachDAO {

    private DPHelper mydb;
    private SQLiteDatabase db;
    public SachDAO(Context context){
   mydb=new DPHelper(context);
   db=mydb.getWritableDatabase();
    }
    public long insert(Sach sach){
        ContentValues values=new ContentValues();
        values.put("tenSach",sach.tenSach);
        values.put("giaThue",sach.giaThue+"");
        values.put("maLoai",sach.maLoai+"");
        values.put(("soTrang"),sach.sotrang+"");
        return db.insert("SACH",null,values);
    }
    public int update(Sach sach){
        ContentValues values=new ContentValues();
        values.put("tenSach",sach.tenSach);
        values.put("giaThue",sach.giaThue+"");
        values.put("maLoai",sach.maLoai+"");
        String[] dieukien=new String[]{String.valueOf(sach.maSach)};
        return db.update("SACH",values,"maSach=?",dieukien);
    }
    public int delete(String id){

        return db.delete("SACH","maSach=?",new String[]{id});
    }
    public ArrayList<Sach> getList(){
        String sql="SELECT * FROM SACH";
        return getData(sql);
    }
    @SuppressLint("Range")
    private ArrayList<Sach> getData(String sql, String...selectionArgs){
        ArrayList<Sach> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()){
            Sach sach=new Sach();
            sach.maSach=Integer.parseInt(c.getString(c.getColumnIndex("maSach")));
            sach.tenSach=c.getString(c.getColumnIndex("tenSach"));
            sach.giaThue=Integer.parseInt(c.getString(c.getColumnIndex("giaThue")));
            sach.maLoai=Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
            sach.sotrang=Integer.parseInt(c.getString(c.getColumnIndex("soTrang")));
            list.add(sach);
        }
        return list;
    }
    public Sach getID(String id){
        String sql="SELECT * FROM SACH WHERE maSach=?";
        ArrayList<Sach> list=getData(sql,id);
        return list.get(0);
    }
}
