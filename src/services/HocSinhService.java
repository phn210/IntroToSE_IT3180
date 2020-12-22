package services;

import models.HocSinh;
import models.ThanhTich;

import java.sql.*;
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
                "and NhanKhau.ID = ThanhTich.ID " +
                "and NhanKhau.ID = " + "'" + hocSinh.getID() + "'";
        try {
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                ThanhTich thanhTich = new ThanhTich();
                thanhTich.setID(rs.getInt("ID"));
                thanhTich.setNamHoc(rs.getInt("NamHoc"));
                thanhTich.setTruong(rs.getNString("Truong"));
                thanhTich.setThanhTich(rs.getNString("ThanhTich"));
                list.add(thanhTich);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public int themThanhTich(ThanhTich thanhTich){

        String checkExist = "select count(*) " +
                "from ThanhTich " +
                "where ID = " + thanhTich.getID() +
                "and NamHoc = " + thanhTich.getNamHoc();
        try {
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(checkExist);
            rs.next();
            int n = 0;
            if(rs.next()){
                String update = "update ThanhTich " +
                                "set ThanhTich = ?" +
                                "where ID = ?" +
                                "and NamHoc = ?";

                PreparedStatement pstmt = conn.prepareStatement(update);
                pstmt.setString(1, thanhTich.getThanhTich());
                pstmt.setInt(2, thanhTich.getID());
                pstmt.setInt(3, thanhTich.getNamHoc());
                n = pstmt.executeUpdate();
                return 1;
            } else {
                String insert = "insert into ThanhTich(ID, NamHoc, ThanhTich, Truong) " +
                        "values (?, ? , ? ,?)";
                PreparedStatement pstmt = conn.prepareStatement(insert);
                pstmt.setInt(1, thanhTich.getID());
                pstmt.setInt(2, thanhTich.getNamHoc());
                pstmt.setString(3, thanhTich.getThanhTich());
                pstmt.setString(4, thanhTich.getTruong());
                pstmt.executeUpdate();
                n = statement.executeUpdate(insert);
                return 2;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }



}
