package fpoly.haonaph45181.duanmau.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DPHelper extends SQLiteOpenHelper {
    public DPHelper(@Nullable Context context) {

        super(context, "bang", null, 23);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
                String t="CREATE TABLE THUTHU(maTT TEXT PRIMARY KEY,hoTen TEXT NOT NULL,matKhau TEXT NOT NULL)";
        sqLiteDatabase.execSQL(t);
        String t1="INSERT INTO THUTHU(maTT,hoTen,matKhau) VALUES('1','NGUYEN ANH HAO','123456'),('2','nguyen van an','12345'),('3','tran anh quan','1234')";
        sqLiteDatabase.execSQL(t1);

        String tt="CREATE TABLE THANHVIEN(maTV INTEGER PRIMARY KEY AUTOINCREMENT,hoTen TEXT NOT NULL,namSinh TEXT NOT NULL )";
        sqLiteDatabase.execSQL(tt);
        String tt1="INSERT INTO THANHVIEN(maTV,hoTen,namSinh) VALUES(1,'nguyen anh hao','22-12-2004'),(2,'trần trung sơn','12-10-2007'),(3,'lê văn trung','10-11-2005')";
        sqLiteDatabase.execSQL(tt1);

        String ttt="CREATE TABLE LOAISACH(maLoai INTEGER PRIMARY KEY AUTOINCREMENT,tenLoai TEXT NOT NULL)";
        sqLiteDatabase.execSQL(ttt);
        String ttt1="INSERT INTO LOAISACH(maLoai,tenLoai) VALUES(1,'Lập trình'),(2,'nấu ăn'),(3,'khoa học')";
        sqLiteDatabase.execSQL(ttt1);

        String tttt="CREATE TABLE SACH(maSach INTEGER PRIMARY KEY AUTOINCREMENT,tenSach TEXT NOT NULL,giaThue INTEGER NOT NULL,maLoai INTEGER REFERENCES LOAISACH(maLoai),soTrang INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(tttt);
//        String tttt1="INSERT INTO SACH(maSach,tenSach,giaThue,maLoai,soTrang) VALUES(1,'lập trình java',1200,1,120),(2,'món ngon mỗi ngày',2350,2,1200),(3,'vật lý',1500,3,234)";
//        sqLiteDatabase.execSQL(tttt1);

        String ttttt="CREATE TABLE PHIEUMUON(maPM INTEGER PRIMARY KEY AUTOINCREMENT ,maTT TEXT REFERENCES THUTHU(maTT),maTV INTEGER REFERENCES THANHVIEN(maTV),maSach INTEGER REFERENCES SACH(maSach),tienThue INTEGER NOT NULL,ngay DATE NOT NULL,traSach INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(ttttt);
        String ttttt1="INSERT INTO PHIEUMUON(maPM,maTT,maTV,maSach,tienThue,ngay,traSach) VALUES(1,'1',1,1,1200,'12-02-2024',1),(2,'2',2,2,2350,'01-03-2024',0),(3,'3',3,3,1500,'29-02-2024',1)";
        sqLiteDatabase.execSQL(ttttt1);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i1!=i) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THUTHU");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            onCreate(sqLiteDatabase);
        }
    }
}
