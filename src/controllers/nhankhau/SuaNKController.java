package controllers.nhankhau;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.HoGiaDinh;
import models.NhanKhau;
import services.HoGiaDinhService;
import services.NhanKhauService;

import java.sql.Date;
import java.sql.SQLException;

public class SuaNKController {

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
    void updateNhanKhau(ActionEvent event) throws SQLException {
        NhanKhau nhanKhauModel = new NhanKhau(Integer.valueOf(textID.getText()),
                                                textTen.getText(),
                                                textGioiTinh.getText(),
                                                Date.valueOf(textNgaySinh.getText()),
                                                textNgheNghiep.getText(),
                                                Integer.valueOf(textIDGiaDinh.getText()));
        NhanKhauService nhanKhauService = new NhanKhauService();
        nhanKhauService.editListNhanKhau(nhanKhauModel);
    }

    public void initializeTextField(NhanKhau nhanKhau){
        textIDGiaDinh.setText(String.valueOf(nhanKhau.getIDGiaDinh()));
        textTen.setText(nhanKhau.getTen());
        textGioiTinh.setText(nhanKhau.getGioiTinh());
        textNgaySinh.setText(String.valueOf(nhanKhau.getNgaySinh()));
        textNgheNghiep.setText(nhanKhau.getNgheNghiep());
        textID.setText(String.valueOf(nhanKhau.getID()));
        textID.setEditable(false);
    }

}