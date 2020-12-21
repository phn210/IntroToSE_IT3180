package services;

import models.ChiTietThongKe;
import models.GoiQua;
import models.HoGiaDinh;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ThongKeService {

    public static int IDGiaDinh;

    public int countMoney(String nam, String dip,String IDGiaDinh) throws SQLException {
        Connection conn = DBConnection.getConnection();
        ResultSet rs;
        int result = 0;
        rs = DBConnection.getData("select SUM(GiaTien) as SUM\n" +
                "from GoiQua, PhatQua, NhanKhau\n" +
                "where GoiQua.MaGoiQua = PhatQua.MaGoiQua\n" +
                "and PhatQua.ID = NhanKhau.ID\n"+
                "and CAST(Nam as nvarchar) like '%"+nam+"%' and Dip like N'%"+dip+"%' and NhanKhau.IDGiaDinh like N'%"+IDGiaDinh+"%'", conn);
        while(rs.next())
            result = rs.getInt("SUM");

        conn.close();
        return result;
    }

    public List<ChiTietThongKe> getListHoGiaDinh() {
        List<ChiTietThongKe> list = new ArrayList<>();
        try {

            Connection conn = DBConnection.getConnection();
            ResultSet rs = DBConnection.getData("select NhanKhau.ID, NhanKhau.Ten, MoTa, GiaTien, Dip, Nam\n" +
                                                        "from GoiQua, PhatQua, NhanKhau\n" +
                                                        "where GoiQua.MaGoiQua = PhatQua.MaGoiQua \n" +
                                                        "and PhatQua.ID = NhanKhau.ID\n" +
                                                        "and NhanKhau.IDGiaDinh = "+ this.IDGiaDinh, conn);

            while(rs.next()){
                ChiTietThongKe chiTietThongKe = new ChiTietThongKe();
                chiTietThongKe.setID(rs.getInt("ID"));
                chiTietThongKe.setTen(rs.getString("Ten"));
                chiTietThongKe.setMoTa(rs.getString("MoTa"));
                chiTietThongKe.setGiaTien(rs.getInt("GiaTien"));
                chiTietThongKe.setDip(rs.getString("Dip"));
                chiTietThongKe.setNam(rs.getInt("Nam"));
                list.add(chiTietThongKe);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<ChiTietThongKe> filterListHoGiaDinh(String dip, String nam){
        List<ChiTietThongKe> list = new ArrayList<>();
        try {

            Connection conn = DBConnection.getConnection();
            ResultSet rs = DBConnection.getData("select NhanKhau.ID, NhanKhau.Ten, MoTa, GiaTien, Dip, Nam\n" +
                                                        "from GoiQua, PhatQua, NhanKhau\n" +
                                                        "where GoiQua.MaGoiQua = PhatQua.MaGoiQua \n" +
                                                        "and PhatQua.ID = NhanKhau.ID\n" +
                                                        "and NhanKhau.IDGiaDinh = "+ this.IDGiaDinh +
                                                        " and CAST(Nam as nvarchar) like '%"+nam+"%' and "+"Dip like N'%"+dip+"%'", conn);

            while(rs.next()){
                ChiTietThongKe chiTietThongKe = new ChiTietThongKe();
                chiTietThongKe.setID(rs.getInt("ID"));
                chiTietThongKe.setTen(rs.getString("Ten"));
                chiTietThongKe.setMoTa(rs.getString("MoTa"));
                chiTietThongKe.setGiaTien(rs.getInt("GiaTien"));
                chiTietThongKe.setDip(rs.getString("Dip"));
                chiTietThongKe.setNam(rs.getInt("Nam"));
                list.add(chiTietThongKe);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<String> getAllNam(){
        List<String> list = new ArrayList<>();
        String query = "select distinct Nam from GoiQua";
        try{
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                GoiQua goiQua = new GoiQua();
                goiQua.setNam(rs.getInt("Nam"));
                list.add(String.valueOf(goiQua.getNam()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public List<String> getAllDip(){
        List<String> list = new ArrayList<>();
        String query = "select distinct Dip from GoiQua";

        try{
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                GoiQua goiQua = new GoiQua();
                goiQua.setDip(rs.getNString("Dip"));
                list.add(goiQua.getDip());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

}
