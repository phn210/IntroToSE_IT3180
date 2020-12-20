package services;

import models.GoiQua;
import models.HocSinh;
import models.ThanhTich;
import models.ThieuNhi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PhatQuaService {
    public PhatQuaService(){

    }

    public GoiQua getGoiQua(int nam, String dip){
        List<GoiQua> list = new ArrayList<>();

        String query = "select * " +
                        "from GoiQua " +
                        "where Dip = " + "N'" + dip + "'" +
                        "and Nam = " + nam;
        try{
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()){
                GoiQua goiQua = new GoiQua();
                goiQua.setID(rs.getInt("MaGoiQua"));
                goiQua.setDip(rs.getNString("Dip"));
                goiQua.setNam(rs.getInt("Nam"));
                goiQua.setMoTa(rs.getNString("MoTa"));
                goiQua.setGiaTien(rs.getDouble("GiaTien"));
                list.add(goiQua);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (list.size() > 0)
            return list.get(0);
        else return null;
    }

    public boolean phatQuaTN(ThieuNhi thieuNhi, GoiQua goiQua){
        int IDNguoiNhan = thieuNhi.getID();
        int IDGoiQua = goiQua.getID();

        String checkExist = "select count(*) " +
                            "from PhatQua " +
                            "where ID = " + "'" + IDNguoiNhan + "'" +
                            "and MaGoiQua = " + "'" + IDGoiQua + "'";
        try {
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(checkExist);
            rs.next();
            if(rs.next()){
                return false;
            }
            else {
                String insert = "insert into PhatQua(ID, MaGoiQua) " +
                                "values (" + IDNguoiNhan + "," + IDGoiQua + ")";
                statement = conn.createStatement();
                int n = statement.executeUpdate(insert);
                if (n == 0)
                    return false;
                else return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean phatQuaHS(HocSinh hocSinh, GoiQua goiQua){
        int IDNguoiNhan = hocSinh.getID();
        int IDGoiQua = goiQua.getID();

        String checkExist = "select count(*) " +
                "from PhatQua " +
                "where ID = " + "'" + IDNguoiNhan + "'" +
                "and MaGoiQua = " + "'" + IDGoiQua + "'";
        try {
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(checkExist);
            rs.next();
            if(rs.next()){
                return false;
            }
            else {
                String insert = "insert into PhatQua(ID, MaGoiQua) " +
                        "values (" + IDNguoiNhan + "," + IDGoiQua + ")";
                statement = conn.createStatement();
                int n = statement.executeUpdate(insert);
                if (n == 0)
                    return false;
                else return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean suaGoiQua(GoiQua goiQua){
        String update = "update GoiQua " +
                        "set MoTa = " + "'" + goiQua.getMoTa() + "', " +
                        "GiaTien = " + "'" + goiQua.getGiaTien() + "' " +
                        "where Dip = " + "N'" + goiQua.getDip() + "' " +
                        "and Nam = " + "'" + goiQua.getNam() + "'";
        try {
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            int n = statement.executeUpdate(update);

            if (n == 0)
                return false;
            else return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean themGoiQua(GoiQua goiQua){
        String insert = "insert into GoiQua(Dip, Nam, GiaTien, MoTa) " +
                        "values (" + "'" + goiQua.getDip() + "', " +
                        goiQua.getNam() + ", " + goiQua.getGiaTien() +
                        ", '" + goiQua.getMoTa() + "'";
        try {
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            int n = statement.executeUpdate(insert);

            if (n == 0)
                return false;
            else return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public List<String> getAllThanhTich(){
        List<String> list = new ArrayList<>();
        String query = "select distinct ThanhTich " +
                        "from ThanhTich";

        try{
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                ThanhTich thanhTich = new ThanhTich();
                thanhTich.setThanhTich(rs.getNString("ThanhTich"));
                list.add(thanhTich.getThanhTich());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public List<Integer> getAllNamHoc(){
        List<Integer> list = new ArrayList<>();
        String query = "select distinct NamHoc " +
                        "from ThanhTich";
        try{
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                ThanhTich thanhTich = new ThanhTich();
                thanhTich.setNamHoc(rs.getInt("NamHoc"));
                list.add(thanhTich.getNamHoc());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public List<String> getAllDip(){
        List<String> list = new ArrayList<>();
        String query = "select distinct Dip " +
                "from GoiQua where Dip not like N'%Học Sinh%'";

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

    public List<Integer> getAllNamDip(){
        List<Integer> list = new ArrayList<>();
        String query = "select distinct Nam " +
                "from GoiQua";
        try{
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                GoiQua goiQua = new GoiQua();
                goiQua.setNam(rs.getInt("Nam"));
                list.add(goiQua.getNam());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public void addNewNamTN(int NamHoc){
//        String query = "insert into GoiQua"
    }
}
