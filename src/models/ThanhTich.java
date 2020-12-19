package models;

public class ThanhTich {
    private int namHoc;
    private int ID;
    private String truong;
    private String thanhTich;

    public ThanhTich() {

    }
    public ThanhTich(int namHoc, int ID, String truong, String thanhTich) {
        this.namHoc = namHoc;
        this.ID = ID;
        this.truong = truong;
        this.thanhTich = thanhTich;
    }

    public int getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(int namHoc) {
        this.namHoc = namHoc;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTruong() {
        return truong;
    }

    public void setTruong(String truong) {
        this.truong = truong;
    }

    public String getThanhTich() {
        return thanhTich;
    }

    public void setThanhTich(String thanhTich) {
        this.thanhTich = thanhTich;
    }
}
