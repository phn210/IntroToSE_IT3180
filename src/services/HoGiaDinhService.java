package services;

import javafx.scene.control.Alert;
import models.HoGiaDinh;

import java.nio.file.Watchable;
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

    public void addListHoGiaDinh(HoGiaDinh hoGiaDinhModel) throws SQLException {
        try {
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

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Thêm thành công!");
            alert.showAndWait();
        }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Thêm thất bại!");
            alert.showAndWait();
        }
    }

    public void deleteListHoGiaDinh(int IDGiaDinh) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "Delete from NhanKhau where IDGiaDinh = ?;" +
                    "Delete from HoGiaDinh where IDGiaDinh = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, String.valueOf(IDGiaDinh));
        pst.setString(2, String.valueOf(IDGiaDinh));

        pst.execute();

        conn.close();
        pst.close();
    }

    public void editListHoGiaDinh(HoGiaDinh hoGiaDinhModel) throws SQLException {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "Update HoGiaDinh set DiaChi = ?, ChuHo = ?, SDT = ? " +
                    "where IDGiaDinh = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, hoGiaDinhModel.getDiaChi());
            pst.setString(2, hoGiaDinhModel.getChuHo());
            pst.setString(3, hoGiaDinhModel.getSDT());
            pst.setString(4, String.valueOf(hoGiaDinhModel.getIDGiaDinh()));

            pst.executeUpdate();

            conn.close();
            pst.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Sửa thành công!");
            alert.showAndWait();
        }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Sửa thất bại!");
            alert.showAndWait();
        }
    }
}
