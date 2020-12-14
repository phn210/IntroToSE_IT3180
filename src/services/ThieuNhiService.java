package services;

import models.ThieuNhi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThieuNhiService {

    public ThieuNhiService(){

    }

    public List<ThieuNhi> getAll() {
        List<ThieuNhi> list = new ArrayList<>();
        String query = "select *, DATEDIFF(year, NgaySinh, GetDate()) Tuoi " +
                    "from NhanKhau, HoGiaDinh " +
                    "where DATEDIFF(year, NgaySinh, GetDate()) <= 18" +
                    "and NhanKhau.IDGiaDinh = HoGiaDinh.IDGiaDinh";

        try {
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                ThieuNhi thieuNhi = new ThieuNhi();
                thieuNhi.setID(rs.getInt("ID"));
                thieuNhi.setTen(rs.getNString("Ten"));
                thieuNhi.setGioiTinh(rs.getNString("GioiTinh"));
                thieuNhi.setNgaySinh(rs.getDate("NgaySinh"));
                thieuNhi.setIDGiaDinh(rs.getInt("IDGiaDinh"));
                thieuNhi.setTuoi(rs.getInt("Tuoi"));
                thieuNhi.hoGiaDinh.setChuHo(rs.getNString("ChuHo"));
                thieuNhi.hoGiaDinh.setDiaChi(rs.getNString("DiaChi"));
                list.add(thieuNhi);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public List<ThieuNhi> getAll(int nam) {
        List<ThieuNhi> list = new ArrayList<>();
        String date = nam + "-12-30";
        String query = "select *, DATEDIFF(year, NgaySinh, " + date + ") Tuoi " +
                "from NhanKhau, HoGiaDinh " +
                "where DATEDIFF(year, NgaySinh, " + date + ") <= 18 " +
                "and DATEDIFF(year, NgaySinh, " + date + ") >= 0" +
                "and NhanKhau.IDGiaDinh = HoGiaDinh.IDGiaDinh";

        try {
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                ThieuNhi thieuNhi = new ThieuNhi();
                thieuNhi.setID(rs.getInt("ID"));
                thieuNhi.setTen(rs.getNString("Ten"));
                thieuNhi.setGioiTinh(rs.getNString("GioiTinh"));
                thieuNhi.setNgaySinh(rs.getDate("NgaySinh"));
                thieuNhi.setIDGiaDinh(rs.getInt("IDGiaDinh"));
                thieuNhi.setTuoi(rs.getInt("Tuoi"));
                thieuNhi.hoGiaDinh.setChuHo(rs.getNString("ChuHo"));
                thieuNhi.hoGiaDinh.setDiaChi(rs.getNString("DiaChi"));
                list.add(thieuNhi);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public List<ThieuNhi> search(String ten, String gioiTinh, String tuoi, String chuHo){
        List<ThieuNhi> list = new ArrayList<>();

        String query = "select *, DATEDIFF(year, NgaySinh, GetDate()) Tuoi " +
                        "from NhanKhau, HoGiaDinh " +
                        "where DATEDIFF(year, NgaySinh, GetDate()) <= 18 " +
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
                ThieuNhi thieuNhi = new ThieuNhi();
                thieuNhi.setID(rs.getInt("ID"));
                thieuNhi.setTen(rs.getNString("Ten"));
                thieuNhi.setGioiTinh(rs.getNString("GioiTinh"));
                thieuNhi.setNgaySinh(rs.getDate("NgaySinh"));
                thieuNhi.setIDGiaDinh(rs.getInt("IDGiaDinh"));
                thieuNhi.setTuoi(rs.getInt("Tuoi"));
                thieuNhi.hoGiaDinh.setChuHo(rs.getNString("ChuHo"));
                thieuNhi.hoGiaDinh.setDiaChi(rs.getNString("DiaChi"));
                list.add(thieuNhi);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
