package models;

import java.sql.Date;

public class NhanKhau {
    private int ID;
    private String ten;
    private String gioiTinh;
    private Date ngaySinh;
    private String ngheNghiep;
    private int IDGiaDinh;

    public NhanKhau(int ID, String ten, String gioiTinh, Date ngaySinh, String ngheNghiep, int IDGiaDinh) {
        this.ID = ID;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.ngheNghiep = ngheNghiep;
        this.IDGiaDinh = IDGiaDinh;
    }

    public NhanKhau() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public int getIDGiaDinh() { return IDGiaDinh; }

    public void setIDGiaDinh() { this.IDGiaDinh = IDGiaDinh; }
}
