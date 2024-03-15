package fpoly.haonaph45181.duanmau.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fpoly.haonaph45181.duanmau.Database.DPHelper;
import fpoly.haonaph45181.duanmau.Model.PhieuMuon;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonDAO {
    private DPHelper mydb;
    private SQLiteDatabase db;
    SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");;
    public PhieuMuonDAO(Context context){
        mydb=new DPHelper(context);
        db=mydb.getWritableDatabase();
    }
    public long insert(PhieuMuon phieumuon){

        ContentValues values=new ContentValues();
        values.put("maTT",phieumuon.maTT);
        values.put("maTV",phieumuon.maTV+"");
        values.put("maSach",phieumuon.maSach+"");
        values.put("tienThue",phieumuon.tienThue+"");
        values.put("traSach",phieumuon.traSach+"");
        values.put("ngay",dateFormat.format(phieumuon.ngay));
        return db.insert("PHIEUMUON",null,values);
    }
    public int update(PhieuMuon phieumuon){

        ContentValues values=new ContentValues();
        values.put("maTT",phieumuon.maTT);
        values.put("maTV",phieumuon.maTV+"");
        values.put("maSach",phieumuon.maSach+"");
        values.put("tienThue",phieumuon.tienThue+"");
        values.put("traSach",phieumuon.traSach+"");
        values.put("ngay",dateFormat.format(phieumuon.ngay));
        String[] dieukien=new String[]{String.valueOf(phieumuon.maPM)};
        return db.update("PHIEUMUON",values,"maPM=?",dieukien);
    }
    public int delete(String id){

        return db.delete("PHIEUMUON","maPM=?",new String[]{id});
    }
    public ArrayList<PhieuMuon> getList(){
        String sql="SELECT * FROM PHIEUMUON";
        return getData(sql);
    }
    @SuppressLint("Range")
    private ArrayList<PhieuMuon> getData(String sql, String...selectionArgs){
        ArrayList<PhieuMuon> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()){
            PhieuMuon phieumuon=new PhieuMuon();
            phieumuon.maPM=Integer.parseInt(c.getString(c.getColumnIndex("maPM")));
            phieumuon.maTT=c.getString(c.getColumnIndex("maTT"));
            phieumuon.maTV=Integer.parseInt(c.getString(c.getColumnIndex("maTV")));
            phieumuon.maSach=Integer.parseInt(c.getString(c.getColumnIndex("maSach")));
            phieumuon.tienThue=Integer.parseInt(c.getString(c.getColumnIndex("tienThue")));
            phieumuon.traSach=Integer.parseInt(c.getString(c.getColumnIndex("traSach")));
            try {
                phieumuon.ngay= dateFormat.parse(c.getString(c.getColumnIndex("ngay")));
            }catch (Exception exception){
                exception.getStackTrace();
            }

            list.add(phieumuon);
        }
        return list;
    }
    public PhieuMuon getID(String id){
        String sql="SELECT * FROM PHIEUMUON WHERE maPM=?";
        ArrayList<PhieuMuon> list=getData(sql,id);
        return list.get(0);
    }
}
