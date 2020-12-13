package services;

import models.GoiQua;
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
                        "where Dip = " + "'" + dip + "'" +
                        "and Nam = " + nam;
        try{
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if(rs.next()){
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

            if(rs.next()){
                return false;
            }
            else {
                String insert = "insert into PhatQua(ID, MaGoiQua) " +
                                "value (" + IDNguoiNhan + "," + IDGoiQua + ")";
                statement = conn.createStatement();
                rs = statement.executeQuery(insert);
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}