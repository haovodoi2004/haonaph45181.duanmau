package fpoly.haonaph45181.duanmau.Model;

import java.util.Date;

public class PhieuMuon {
    public int maPM;
    public String maTT;
    public int maTV;
    public int maSach;
    public int tienThue;
    public int traSach;
    public Date ngay;

    public PhieuMuon(int maPM, String maTT, int maTV, int maSach, int tienThue, int traSach, Date ngay) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.tienThue = tienThue;
        this.traSach = traSach;
        this.ngay = ngay;
    }

    public PhieuMuon() {
    }
}
