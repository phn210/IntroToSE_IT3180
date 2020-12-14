package controllers.thieunhi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.GoiQua;
import services.PhatQuaService;

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

    private PhatQuaService phatQuaService;

    public ThongTinQuaThieuNhiController(){
        this.phatQuaService = new PhatQuaService();
    }

    public void initialize(GoiQua goiQua) {
        textDip.setText(goiQua.getDip());
        textNam.setText(String.valueOf(goiQua.getNam()));
        textMoTa.setText(goiQua.getMoTa());
        textDonGia.setText(String.valueOf(goiQua.getGiaTien()));
        textDip.setEditable(false);
        textNam.setEditable(false);
    }

    public void update(ActionEvent event){
        GoiQua goiQua = new GoiQua();
        goiQua.setDip(textDip.getText());
        goiQua.setNam(Integer.parseInt(textNam.getText()));
        goiQua.setGiaTien(Double.parseDouble(textDonGia.getText()));
        goiQua.setMoTa(textMoTa.getText());
        boolean success = phatQuaService.suaGoiQua(goiQua);
        if (success) {
            ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Đã cập nhật gói quà!");
            alert.setHeaderText("Completed!");
            alert.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Không thể sửa gói quà, có lỗi xảy ra!");
            alert.setHeaderText("Error!");
            alert.show();
        }
    }

    public void close(ActionEvent event){
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }
}
