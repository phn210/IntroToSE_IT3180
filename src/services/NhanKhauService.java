package services;

import models.HoGiaDinh;
import models.NhanKhau;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanKhauService {
    public List<NhanKhau> getListNhanKhau() {
        List<NhanKhau> list = new ArrayList<>();
        try {

            Connection conn = DBConnection.getConnection();
            ResultSet rs = DBConnection.getData("select * from NhanKhau", conn);

            while(rs.next()){
                NhanKhau nhanKhauModel = new NhanKhau();
                nhanKhauModel.setIDGiaDinh(rs.getInt("IDGiaDinh"));
                nhanKhauModel.setTen(rs.getNString("Ten"));
                nhanKhauModel.setGioiTinh(rs.getNString("GioiTinh"));
                nhanKhauModel.setNgaySinh(rs.getDate("NgaySinh"));
                nhanKhauModel.setNgheNghiep(rs.getNString("NgheNghiep"));
                nhanKhauModel.setID(rs.getInt("ID"));
                list.add(nhanKhauModel);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
