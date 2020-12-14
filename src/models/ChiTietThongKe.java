package models;

public class ChiTietThongKe {
    private int ID;
    private String ten;
    private String moTa;
    private int giaTien;
    private String dip;
    private int nam;

    public ChiTietThongKe(int ID, String ten, String moTa, int giaTien, String dip, int nam){
        this.ID = ID;
        this.ten = ten;
        this.moTa = moTa;
        this.giaTien = giaTien;
        this.dip = dip;
        this.nam = nam;
    }

    public ChiTietThongKe(){

    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public String getDip() {
        return dip;
    }

    public void setDip(String dip) {
        this.dip = dip;
    }
}
