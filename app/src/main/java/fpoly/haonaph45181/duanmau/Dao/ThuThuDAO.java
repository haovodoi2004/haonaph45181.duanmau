package fpoly.haonaph45181.duanmau.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import fpoly.haonaph45181.duanmau.Database.DPHelper;
import fpoly.haonaph45181.duanmau.Model.ThuThu;

import java.util.ArrayList;

public class ThuThuDAO {
    private DPHelper myd;
    private SQLiteDatabase db;
    Context context;

    public ThuThuDAO(Context context) {
        this.context = context;
        myd=new DPHelper(context);
        db=myd.getWritableDatabase();
    }

    public boolean checkLogin(String strUser, String strPass){
        Cursor c = db.rawQuery("Select * from THUTHU where  maTT = ? AND matKhau = ?", new String[]{strUser,strPass});
        Log.d("zzzzzzzzzzz", "checkLogin: "+c.getCount());
        return c.getCount() > 0;
    }
    public ThuThu getID(String id){
        String sql="select * from THUTHU where maTT=?";
        ArrayList<ThuThu> list=getData(sql,id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private ArrayList<ThuThu> getData(String sql, String...selectionArgs){
        ArrayList<ThuThu> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()){
            ThuThu tt=new ThuThu();
            tt.maTT=c.getString(c.getColumnIndex("maTT"));
            tt.hoTen=c.getString(c.getColumnIndex("hoTen"));
            tt.matKhau=c.getString(c.getColumnIndex("matKhau"));
            list.add(tt);
        }
        return list;
    }


    public int updatePass(ThuThu thuthu){
        ContentValues values=new ContentValues();
        values.put("maTT",thuthu.maTT);
        values.put("hoTen",thuthu.hoTen);
        values.put("matKhau",thuthu.matKhau);
        return db.update("THUTHU",values,"maTT=?",new String[]{thuthu.maTT});
    }
    public int insert(ThuThu thuthu){
        ContentValues values=new ContentValues();
        values.put("maTT",thuthu.maTT);
        values.put("hoTen",thuthu.hoTen);
        values.put("matKhau",thuthu.matKhau);
        return (int)db.insert("THUTHU",null,values);
    }


}
