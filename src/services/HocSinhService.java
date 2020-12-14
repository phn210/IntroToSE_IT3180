package services;

import models.HocSinh;
import models.ThanhTich;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HocSinhService {
    public List<HocSinh> getAll(){
        List<HocSinh> list = new ArrayList<>();
        String query = "select *" +
                "from NhanKhau, HoGiaDinh " +
                "where NgheNghiep = N'Học sinh' " +
                "and NhanKhau.IDGiaDinh = HoGiaDinh.IDGiaDinh";

        try {
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                HocSinh hocSinh = new HocSinh();
                hocSinh.setID(rs.getInt("ID"));
                hocSinh.setTen(rs.getNString("Ten"));
                hocSinh.setGioiTinh(rs.getNString("GioiTinh"));
                hocSinh.setNgaySinh(rs.getDate("NgaySinh"));
                hocSinh.setIDGiaDinh(rs.getInt("IDGiaDinh"));
                hocSinh.hoGiaDinh.setChuHo(rs.getNString("ChuHo"));
                hocSinh.hoGiaDinh.setDiaChi(rs.getNString("DiaChi"));
                hocSinh.hoGiaDinh.setSDT(rs.getNString("SDT"));
                list.add(hocSinh);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public List<HocSinh> getAll(int nam, String tenThanhTich){
        List<HocSinh> list = new ArrayList<>();
        String query = "select *" +
                "from NhanKhau, HoGiaDinh, ThanhTich " +
                "where NgheNghiep = N'Học sinh' " +
                "and NhanKhau.IDGiaDinh = HoGiaDinh.IDGiaDinh " +
                "and NhanKhau.ID = ThanhTich.ID " +
                "and NamHoc = " + "'" + nam + "' " +
                "and ThanhTich = " + "'" + tenThanhTich + "'";

        try {
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                HocSinh hocSinh = new HocSinh();
                hocSinh.setID(rs.getInt("ID"));
                hocSinh.setTen(rs.getNString("Ten"));
                hocSinh.setGioiTinh(rs.getNString("GioiTinh"));
                hocSinh.setNgaySinh(rs.getDate("NgaySinh"));
                hocSinh.setIDGiaDinh(rs.getInt("IDGiaDinh"));
                hocSinh.hoGiaDinh.setChuHo(rs.getNString("ChuHo"));
                hocSinh.hoGiaDinh.setDiaChi(rs.getNString("DiaChi"));
                hocSinh.hoGiaDinh.setSDT(rs.getNString("SDT"));
                list.add(hocSinh);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public List<HocSinh> search(String ten, String gioiTinh, String tuoi, String chuHo){
        List<HocSinh> list = new ArrayList<>();

        String query = "select *" +
                "from NhanKhau, HoGiaDinh " +
                "where NgheNghiep = N'Học sinh' " +
                "and NhanKhau.IDGiaDinh = HoGiaDinh.IDGiaDinh";

        if(!ten.equals(""))
            query = query + " and Ten like " + "N'%" + ten + "%'";
        if(!gioiTinh.equals(""))
            query = query + " and GioiTinh = " + "N'" + gioiTinh + "'";
        if(!tuoi.equals(""))
            query = query + " and DATEDIFF(year, NgaySinh, GetDate()) = " + "'" + tuoi + "'";
        if(!chuHo.equals(""))
            query = query + " and ChuHo like " + "N'%" + chuHo + "%'";

        try {
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                HocSinh hocSinh = new HocSinh();
                hocSinh.setID(rs.getInt("ID"));
                hocSinh.setTen(rs.getNString("Ten"));
                hocSinh.setGioiTinh(rs.getNString("GioiTinh"));
                hocSinh.setNgaySinh(rs.getDate("NgaySinh"));
                hocSinh.setIDGiaDinh(rs.getInt("IDGiaDinh"));
                hocSinh.hoGiaDinh.setChuHo(rs.getNString("ChuHo"));
                hocSinh.hoGiaDinh.setDiaChi(rs.getNString("DiaChi"));
                hocSinh.hoGiaDinh.setSDT(rs.getNString("SDT"));
                list.add(hocSinh);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<ThanhTich> getListThanhTich(HocSinh hocSinh){
        List<ThanhTich> list = new ArrayList<>();

        String query = "select *" +
                "from NhanKhau, ThanhTich " +
                "where NgheNghiep = N'Học sinh' " +
                "and NhanKhau.ID = ThanhTich.ID";

        try {
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                ThanhTich thanhTich = new ThanhTich();
                thanhTich.setID(rs.getInt("ID"));
                thanhTich.setNamHoc(rs.getInt("NamHoc"));
                thanhTich.setThanhTich(rs.getNString("ThanhTich"));
                thanhTich.setTruong(rs.getNString("Truong"));
                list.add(thanhTich);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

}
