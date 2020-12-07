package models;

import java.sql.Date;

public class ThieuNhi extends NhanKhau{
    private int tuoi;

    public ThieuNhi(){

    }

    public ThieuNhi(int ID, String ten, String gioiTinh, Date ngaySinh, String ngheNghiep, int IDGiaDinh, int tuoi) {
        super(ID, ten, gioiTinh, ngaySinh, ngheNghiep, IDGiaDinh);
        this.tuoi = tuoi;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }
}
