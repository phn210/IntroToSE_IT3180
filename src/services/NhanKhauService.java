package services;

import javafx.scene.control.Alert;
import models.HoGiaDinh;
import models.NhanKhau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanKhauService {

    public static int IDGiaDinhNK;

    public  NhanKhauService(){
    }

    public List<NhanKhau> getListNhanKhau() {
        List<NhanKhau> list = new ArrayList<>();
        try {

            Connection conn = DBConnection.getConnection();
            ResultSet rs = DBConnection.getData("select * from NhanKhau where IDGiaDinh = " + this.IDGiaDinhNK, conn);

            while(rs.next()){
                NhanKhau nhanKhauModel = new NhanKhau();
                nhanKhauModel.setIDGiaDinh(rs.getInt("IDGiaDinh"));
                nhanKhauModel.setTen(rs.getNString("Ten"));
                nhanKhauModel.setGioiTinh(rs.getNString("GioiTinh"));
                nhanKhauModel.setNgaySinh(rs.getDate("NgaySinh"));
                nhanKhauModel.setNgheNghiep(rs.getNString("NgheNghiep"));
                nhanKhauModel.setID(rs.getInt("ID"));
                list.add(nhanKhauModel);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<NhanKhau> searchListNhanKhau(String colIndex, String key) {
        List<NhanKhau> list = new ArrayList<>();
        try {

            Connection conn = DBConnection.getConnection();
            ResultSet rs = DBConnection.getData("select * from NhanKhau where " + colIndex + " LIKE " + "N'%" + key + "%'"
                                                        + " and IDGiaDinh = " + this.IDGiaDinhNK, conn);

            while(rs.next()){
                NhanKhau nhanKhauModel = new NhanKhau();
                nhanKhauModel.setID(rs.getInt("ID"));
                nhanKhauModel.setTen(rs.getString("Ten"));
                nhanKhauModel.setGioiTinh(rs.getString("GioiTinh"));
                nhanKhauModel.setNgaySinh(rs.getDate("NgaySinh"));
                nhanKhauModel.setNgheNghiep(rs.getString("NgheNghiep"));
                nhanKhauModel.setIDGiaDinh(rs.getInt("IDGiaDinh"));
                list.add(nhanKhauModel);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void addListNhanKhau(NhanKhau nhanKhauModel) throws SQLException {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "Insert into NhanKhau(ID, Ten, GioiTinh, NgaySinh, NgheNghiep, IDGiaDinh) Values(?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, nhanKhauModel.getID());
            pst.setString(2, nhanKhauModel.getTen());
            pst.setString(3, nhanKhauModel.getGioiTinh());
            pst.setDate(4, nhanKhauModel.getNgaySinh());
            pst.setString(5, nhanKhauModel.getNgheNghiep());
            pst.setInt(6, nhanKhauModel.getIDGiaDinh());

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

    public void deleteListNhanKhau(int ID) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "Delete from NhanKhau where ID = ?;";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, String.valueOf(ID));

        pst.execute();

        conn.close();
        pst.close();
    }

    public void editListNhanKhau(NhanKhau nhanKhauModel) throws SQLException {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "Update NhanKhau set Ten = ?, GioiTinh = ?, NgaySinh = ?, NgheNghiep = ?, IDGiaDinh = ? " +
                    "where ID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nhanKhauModel.getTen());
            pst.setString(2, nhanKhauModel.getGioiTinh());
            pst.setDate(3, nhanKhauModel.getNgaySinh());
            pst.setString(4, nhanKhauModel.getNgheNghiep());
            pst.setInt(5, nhanKhauModel.getIDGiaDinh());
            pst.setInt(6, nhanKhauModel.getID());

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
