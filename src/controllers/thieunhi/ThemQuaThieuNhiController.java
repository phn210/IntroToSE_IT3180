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

public class ThemQuaThieuNhiController{
    @FXML
    private TextField textEnterMoTa;

    @FXML
    private TextField textEnterDip;

    @FXML
    private TextField textEnterNam;

    @FXML
    private TextField textEnterDonGia;
    
    private PhatQuaService phatQuaService;
    
    public ThemQuaThieuNhiController(){
        this.phatQuaService = new PhatQuaService();
    }
    
    public void add(ActionEvent event){
        GoiQua goiQua = new GoiQua();
        goiQua.setDip(textEnterDip.getText());
        goiQua.setNam(Integer.parseInt(textEnterNam.getText()));
        goiQua.setGiaTien(Double.parseDouble(textEnterDonGia.getText()));
        goiQua.setMoTa(textEnterMoTa.getText());
        
        if(goiQua.getDip().equals("") || goiQua.getNam() == 0 || goiQua.getGiaTien() == 0 || goiQua.getMoTa().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Vui lòng điền đủ thông tin gói quà!");
            alert.setHeaderText("Warning!");
            alert.showAndWait();
        } else {
            boolean success = phatQuaService.themGoiQua(goiQua);
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
    }

    public void close(ActionEvent event){
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }
}
