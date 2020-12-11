package controllers.nhankhau;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.HoGiaDinh;
import models.NhanKhau;
import services.HoGiaDinhService;
import services.NhanKhauService;

import java.sql.Date;
import java.sql.SQLException;

public class ThemNKController {

    @FXML
    private TextField textIDGiaDinh;

    @FXML
    private TextField textTen;

    @FXML
    private TextField textGioiTinh;

    @FXML
    private TextField textNgaySinh;

    @FXML
    private TextField textNgheNghiep;

    @FXML
    private TextField textID;

    @FXML
    void addNhanKhau(ActionEvent event) {
        //them moi ho gia dinh
        NhanKhau nhanKhauMoi = new NhanKhau(Integer.valueOf(textID.getText()),
                                            textTen.getText(),
                                            textGioiTinh.getText(),
                                            Date.valueOf(textNgaySinh.getText()),
                                            textNgheNghiep.getText(),
                                            Integer.valueOf(textIDGiaDinh.getText()));
        NhanKhauService nhanKhauService = new NhanKhauService();
        try{
            nhanKhauService.addListNhanKhau(nhanKhauMoi);
        }catch(SQLServerException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Trùng CMND!");
            alert.setHeaderText(null);
            alert.showAndWait();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void deleteText(ActionEvent event) {
        textIDGiaDinh.clear();
        textTen.clear();
        textGioiTinh.clear();
        textNgaySinh.clear();
        textNgheNghiep.clear();
        textID.clear();
    }

}