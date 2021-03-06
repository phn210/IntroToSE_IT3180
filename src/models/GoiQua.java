package models;

public class GoiQua {
    private int ID;
    private String dip;
    private int nam;
    private String moTa;
    private double giaTien;

    public GoiQua(){

    }

    public GoiQua(int ID, String dip, int nam, String moTa, double giaTien) {
        this.ID = ID;
        this.dip = dip;
        this.nam = nam;
        this.moTa = moTa;
        this.giaTien = giaTien;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDip() {
        return dip;
    }

    public void setDip(String dip) {
        this.dip = dip;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }
}
