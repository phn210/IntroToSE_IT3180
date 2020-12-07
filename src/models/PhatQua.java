package models;

public class PhatQua {
    private int IDNguoiNhan;
    private int IDGoiQua;

    public PhatQua(){

    }

    public PhatQua(int IDNguoiNhan, int IDGoiQua) {
        this.IDNguoiNhan = IDNguoiNhan;
        this.IDGoiQua = IDGoiQua;
    }

    public int getIDNguoiNhan() {
        return IDNguoiNhan;
    }

    public void setIDNguoiNhan(int IDNguoiNhan) {
        this.IDNguoiNhan = IDNguoiNhan;
    }

    public int getIDGoiQua() {
        return IDGoiQua;
    }

    public void setIDGoiQua(int IDGoiQua) {
        this.IDGoiQua = IDGoiQua;
    }
}
