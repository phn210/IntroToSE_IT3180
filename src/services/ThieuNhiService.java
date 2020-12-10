package services;

import models.HoGiaDinh;
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
                    "where Tuoi <= 18" +
                    "and NhanKhau.IDGiaDinh = HoGiaDinh.IDGiaDinh";
        Connection conn = DBConnection.connection;
        try (Statement statement = conn.createStatement()){

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

        String query = "select *, DATEDIFF(year, NgaySinh, GetDate()) Tuoi from NhanKhau where Tuoi <= 18";

        if(ten != null)
            query = query + " and Ten = " + "'" + ten + "'";
        if(gioiTinh != null)
            query = query + " and GioiTinh = " + "'" + gioiTinh + "'";
        if(tuoi != null)
            query = query + " and Tuoi = " + tuoi;
        if(chuHo != null)
            query = query + " and ChuHo = " + "'" + chuHo + "'";

        Connection conn = DBConnection.connection;
        try (Statement statement = conn.createStatement()){

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
