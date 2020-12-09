package services;

import models.HoGiaDinh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoGiaDinhService {

    public List<HoGiaDinh> getListHoGiaDinh() {
        List<HoGiaDinh> list = new ArrayList<>();
        try {

            Connection conn = DBConnection.getConnection();
            ResultSet rs = DBConnection.getData("select * from HoGiaDinh", conn);

            while(rs.next()){
                HoGiaDinh hoGiaDinhModel = new HoGiaDinh();
                hoGiaDinhModel.setChuHo(rs.getString("ChuHo"));
                hoGiaDinhModel.setDiaChi(rs.getString("DiaChi"));
                hoGiaDinhModel.setSDT(rs.getString("SDT"));
                hoGiaDinhModel.setIDGiaDinh(rs.getInt("IDGiaDinh"));
                list.add(hoGiaDinhModel);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<HoGiaDinh> searchListHoGiaDinh(String colIndex, String key) {
        List<HoGiaDinh> list = new ArrayList<>();
        try {

            Connection conn = DBConnection.getConnection();
            ResultSet rs = DBConnection.getData("select * from HoGiaDinh where " + colIndex + " LIKE " + "N'%" + key + "%'", conn);

            while(rs.next()){
                HoGiaDinh hoGiaDinhModel = new HoGiaDinh();
                hoGiaDinhModel.setChuHo(rs.getString("ChuHo"));
                hoGiaDinhModel.setDiaChi(rs.getString("DiaChi"));
                hoGiaDinhModel.setSDT(rs.getString("SDT"));
                hoGiaDinhModel.setIDGiaDinh(rs.getInt("IDGiaDinh"));
                list.add(hoGiaDinhModel);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public boolean addListHoGiaDinh(HoGiaDinh hoGiaDinhModel) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "Insert into HoGiaDinh(IDGiaDinh, DiaChi, ChuHo, SDT) Values(?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, hoGiaDinhModel.getIDGiaDinh());
        pst.setString(2, hoGiaDinhModel.getDiaChi());
        pst.setString(3, hoGiaDinhModel.getChuHo());
        pst.setString(4, hoGiaDinhModel.getSDT());

        pst.executeUpdate();

        conn.close();
        pst.close();
        return true;
    }
}
