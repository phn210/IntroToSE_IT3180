package models;

import java.sql.Date;
import java.util.ArrayList;

public class HocSinh extends ThieuNhi{
    private ArrayList<ThanhTich> dsThanhTich;

    public HocSinh(){

    }

    public HocSinh(int ID, String ten, String gioiTinh, Date ngaySinh, String ngheNghiep, int IDGiaDinh, int tuoi, ArrayList<ThanhTich> dsThanhTich) {
        super(ID, ten, gioiTinh, ngaySinh, ngheNghiep, IDGiaDinh, tuoi);
        dsThanhTich = new ArrayList<>();
    }

    public ArrayList<ThanhTich> getDsThanhTich() {
        return dsThanhTich;
    }

    public void addDsThanhTich(ArrayList<ThanhTich> dsThanhTich) {
        this.dsThanhTich.addAll(dsThanhTich);
    }
}