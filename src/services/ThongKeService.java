package services;

import models.ChiTietThongKe;
import models.HoGiaDinh;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThongKeService {

    public static int IDGiaDinh;

    public int countMoney(String nam, String dip, String doiTuong, int IDGiaDinh) throws SQLException {
        Connection conn = DBConnection.getConnection();
        ResultSet rs;
        int result = 0;
        if(doiTuong == "Tất cả") {
            rs = DBConnection.getData("select SUM(GiaTien) as SUM " +
                    "from GoiQua, PhatQua, HocSinh, ThieuNhi, NhanKhau " +
                    "where GoiQua.MaGoiQua = PhatQua.MaGoiQua " +
                    "and PhatQua.ID = ThieuNhi.ID and ThieuNhi.ID = NhanKhau.ID " +
                    "and PhatQua.ID = HocSinh.ID and HocSinh.ID = NhanKhau.ID "+
                    "and CAST(Nam as nvarchar) like '%"+nam+"%' and Dip like N'%"+dip+"%' and NhanKhau.IDGiaDinh = "+IDGiaDinh, conn);
            result = rs.getInt("SUM");
        }else if(doiTuong == "Học sinh"){
            rs = DBConnection.getData("select SUM(GiaTien) as SUM" +
                    "from GoiQua, PhatQua, HocSinh, NhanKhau " +
                    "where GoiQua.MaGoiQua = PhatQua.MaGoiQua " +
                    "and PhatQua.ID = HocSinh.ID and HocSinh.ID = NhanKhau.ID "+
                    "and CAST(Nam as nvarchar) like '%"+nam+"%' and Dip like N'%"+dip+"%' and NhanKhau.IDGiaDinh = "+IDGiaDinh, conn);
            result = rs.getInt("SUM");
        }else if(doiTuong == "Thiếu nhi"){
            rs = DBConnection.getData("select SUM(GiaTien) as SUM " +
                    "from GoiQua, PhatQua, ThieuNhi, NhanKhau " +
                    "where GoiQua.MaGoiQua = PhatQua.MaGoiQua " +
                    "and PhatQua.ID = ThieuNhi.ID and ThieuNhi.ID = NhanKhau.ID and " +
                    "CAST(Nam as nvarchar) like '%"+nam+"%' and Dip like N'%"+dip+"%' and NhanKhau.IDGiaDinh = "+IDGiaDinh, conn);
            result = rs.getInt("SUM");
        }

        return result;
    }

    public List<ChiTietThongKe> getListHoGiaDinh() {
        List<ChiTietThongKe> list = new ArrayList<>();
        try {

            Connection conn = DBConnection.getConnection();
            ResultSet rs = DBConnection.getData("select NhanKhau.ID, NhanKhau.Ten, MoTa, GiaTien, Dip, Nam\n" +
                                                        "from GoiQua, PhatQua, HocSinh, ThieuNhi, NhanKhau\n" +
                                                        "where GoiQua.MaGoiQua = PhatQua.MaGoiQua \n" +
                                                        "and PhatQua.ID = HocSinh.ID and PhatQua.ID = ThieuNhi.ID\n" +
                                                        "and HocSinh.ID = NhanKhau.ID and ThieuNhi.ID = NhanKhau.ID\n" +
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
                                                        "from GoiQua, PhatQua, HocSinh, ThieuNhi, NhanKhau\n" +
                                                        "where GoiQua.MaGoiQua = PhatQua.MaGoiQua \n" +
                                                        "and PhatQua.ID = HocSinh.ID and PhatQua.ID = ThieuNhi.ID\n" +
                                                        "and HocSinh.ID = NhanKhau.ID and ThieuNhi.ID = NhanKhau.ID\n" +
                                                        "and NhanKhau.IDGiaDinh = "+ this.IDGiaDinh +
                                                        " and CAST(Nam as nvarchar) like '%"+nam+"%' and "+"Dip like N'%"+dip+"%'" , conn);

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

}
