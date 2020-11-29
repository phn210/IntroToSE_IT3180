package models;

public class HoGiaDinh {
    public static int IDGiaDinh;
    private String diaChi;
    private String chuHo;
    private String SDT;

    public HoGiaDinh(int IDGiaDinh, String diaChi, String chuHo, String SDT){
        this.IDGiaDinh = IDGiaDinh;
        this.diaChi = diaChi;
        this.chuHo = chuHo;
        this.SDT = SDT;
    }

    public int getIDGiaDinh() {
        return IDGiaDinh;
    }

    public void setIDGiaDinh(int IDGiaDinh) {
        this.IDGiaDinh = IDGiaDinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getChuHo() {
        return chuHo;
    }

    public void setChuHo(String chuHo) {
        this.chuHo = chuHo;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
}
