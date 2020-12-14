package controllers.thieunhi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.GoiQua;
import services.PhatQuaService;
import views.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ThemQuaThieuNhiController implements Initializable {
    @FXML
    private TextField textEnterMoTa;
    @FXML
    private ComboBox themDipThieuNhi;
    @FXML
    private ComboBox themNamThieuNhi;
    @FXML
    private TextField textEnterDonGia;

    private PhatQuaService phatQuaService;

    private String dipThieuNhi;
    private int namThieuNhi;
    public static ObservableList<String> nam;
    public static ObservableList<String> dip;

    public ThemQuaThieuNhiController(){
        this.phatQuaService = new PhatQuaService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.nam = FXCollections.observableArrayList("2017", "2018", "2019", "2020", "Khác");
        this.themNamThieuNhi.setItems(nam);
        this.dip = FXCollections.observableArrayList("Tết dương lịch", "Tết Nguyên Đán", "Tết thiếu nhi", "Trung Thu", "Noel", "Khác");
        this.themDipThieuNhi.setItems(dip);
    }

    public void add(ActionEvent event){
        GoiQua goiQua = new GoiQua();
        goiQua.setDip(this.dipThieuNhi);
        goiQua.setNam(this.namThieuNhi);
        goiQua.setMoTa(textEnterMoTa.getText());
        
        if(this.textEnterMoTa.getText().trim().isEmpty()
                || goiQua.getNam() == 0
                || this.textEnterDonGia.getText().trim().isEmpty()
                || this.textEnterMoTa.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Vui lòng điền đủ thông tin gói quà!");
            alert.setHeaderText("Warning!");
            alert.showAndWait();
        } else {
            goiQua.setGiaTien(Double.parseDouble(textEnterDonGia.getText()));
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

    public void themDipThieuNhi(ActionEvent event) throws IOException {
        if (this.themDipThieuNhi.getSelectionModel().getSelectedItem().toString().equals("Khác")) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("phatqua/thieunhi/Dip.fxml"));
            Parent root = loader.load();
            ThemNamSuKienController themNamController = (ThemNamSuKienController) loader.getController();
            themNamController.initialize();
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/static/img/bieutuong.png"));
            stage.setScene(new Scene(root));
            stage.show();
        }
        else this.dipThieuNhi = this.themDipThieuNhi.getSelectionModel().getSelectedItem().toString();
    }

    public void themNamThieuNhi(ActionEvent event) throws IOException {
        if (this.themNamThieuNhi.getSelectionModel().getSelectedItem().toString().equals("Khác")) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("phatqua/thieunhi/Nam.fxml"));
            Parent root = loader.load();
            ThemNamSuKienController themNamController = (ThemNamSuKienController) loader.getController();
            themNamController.initialize();
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/static/img/bieutuong.png"));
            stage.setScene(new Scene(root));
            stage.show();
        }
        else this.namThieuNhi = Integer.parseInt(this.themNamThieuNhi.getSelectionModel().getSelectedItem().toString());
    }


}
