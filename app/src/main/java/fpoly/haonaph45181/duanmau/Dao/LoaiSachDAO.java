package fpoly.haonaph45181.duanmau.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



import java.util.ArrayList;

import fpoly.haonaph45181.duanmau.Database.DPHelper;
import fpoly.haonaph45181.duanmau.Model.LoaiSach;

public class LoaiSachDAO {
    private DPHelper mydb;
    private SQLiteDatabase db;
    public LoaiSachDAO(Context context){
        mydb=new DPHelper(context);
        db=mydb.getWritableDatabase();
    }
    public long insert(LoaiSach loaisach){
        ContentValues values=new ContentValues();
        values.put("tenLoai",loaisach.tenLoai);
        return db.insert("LOAISACH",null,values);
    }
    public int update(LoaiSach loaisach){
        ContentValues values=new ContentValues();
        values.put("tenLoai",loaisach.tenLoai);
        String[] dieukien=new String[]{String.valueOf(loaisach.maLoai)};
        return db.update("LOAISACH",values,"maLoai=?",dieukien);
    }
    public int delete(String id){

        return db.delete("LOAISACH","maLoai=?",new String[]{id});
    }
    public ArrayList<LoaiSach> getList(){
        String sql="SELECT * FROM LOAISACH";
        return getData();
    }
    @SuppressLint("Range")
    private ArrayList<LoaiSach> getData(){
        ArrayList<LoaiSach> list=new ArrayList<>();
        Cursor c=db.rawQuery("SELECT * FROM LOAISACH",null);
        while(c.moveToNext()){
            LoaiSach loaisach=new LoaiSach();
            loaisach.maLoai=Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
            loaisach.tenLoai=c.getString(c.getColumnIndex("tenLoai"));
            list.add(loaisach);
        }
        return list;
    }
    public LoaiSach getID(String id){
        String sql="SELECT * FROM LOAISACH WHERE maLoai=?";
        ArrayList<LoaiSach> list=getData();
        return list.get(0);
    }
}
