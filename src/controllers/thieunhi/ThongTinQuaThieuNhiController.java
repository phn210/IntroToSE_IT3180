package controllers.thieunhi;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.GoiQua;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ThongTinQuaThieuNhiController {

    @FXML
    private TextField textMoTa;

    @FXML
    private TextField textDip;

    @FXML
    private TextField textNam;

    @FXML
    private TextField textDonGia;

    public void initialize(GoiQua goiQua) {
        textDip.setText(goiQua.getDip());
        textNam.setText(String.valueOf(goiQua.getNam()));
        textMoTa.setText(goiQua.getMoTa());
        textDonGia.setText(String.valueOf(goiQua.getGiaTien()));
        textDip.setEditable(false);
        textNam.setEditable(false);
    }

    public void update(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    }
}
