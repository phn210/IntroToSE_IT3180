package controllers.hocsinh;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.GoiQua;
import services.PhatQuaService;

public class ThongTinQuaHocSinhController {
    @FXML
    private TextField textMoTa;

    @FXML
    private TextField textThanhTich;

    @FXML
    private TextField textNam;

    @FXML
    private TextField textDonGia;

    private PhatQuaService phatQuaService;

    public ThongTinQuaHocSinhController(){
        this.phatQuaService = new PhatQuaService();
    }

    public void initialize(GoiQua goiQua){
        this.textThanhTich.setText(goiQua.getDip());
        this.textNam.setText(String.valueOf(goiQua.getNam()));
        this.textMoTa.setText(goiQua.getMoTa());
        this.textDonGia.setText(String.valueOf(goiQua.getGiaTien()));
        this.textThanhTich.setEditable(false);
        this.textNam.setEditable(false);
    }

    @FXML
    void update(ActionEvent event) {
        GoiQua goiQua = new GoiQua();
        goiQua.setDip(textThanhTich.getText());
        goiQua.setNam(Integer.parseInt(textNam.getText()));
        goiQua.setMoTa(textMoTa.getText());
        goiQua.setGiaTien(Double.parseDouble(textDonGia.getText()));
        boolean success = this.phatQuaService.suaGoiQua(goiQua);
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

    @FXML
    void close(ActionEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }
}
