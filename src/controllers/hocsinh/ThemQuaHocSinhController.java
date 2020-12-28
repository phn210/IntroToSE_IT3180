package controllers.hocsinh;

import controllers.thieunhi.ThemNamDipController;
import javafx.beans.Observable;
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

public class ThemQuaHocSinhController implements Initializable {

    @FXML
    private TextField textEnterMoTa;

    @FXML
    private ComboBox<String> themThanhTich;

    @FXML
    private ComboBox<String> themNam;

    @FXML
    private TextField textEnterDonGia;

    private PhatQuaService phatQuaService;

    private String thanhTich;
    private int nam;

    public static ObservableList<String> listNam;
    public static ObservableList<String> listThanhTich;

    public ThemQuaHocSinhController(){
        this.phatQuaService = new PhatQuaService();
        this.thanhTich = "";
        this.nam = 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.listNam = FXCollections.observableArrayList("2017", "2018", "2019", "2020", "Khác");
        this.themNam.setItems(listNam);
        this.listThanhTich = FXCollections.observableArrayList("Học sinh Giỏi", "Học sinh Khá", "Học sinh Còn lại", "Học sinh Thành tích Đặc biệt");
        this.themThanhTich.setItems(listThanhTich);
    }

    @FXML
    void add(ActionEvent event){
        if (this.thanhTich.equals("") || this.nam == 0
                || this.textEnterMoTa.getText().trim().isEmpty()
                || this.textEnterDonGia.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Vui lòng điền đủ thông tin gói quà!");
            alert.setHeaderText("Warning!");
            alert.showAndWait();
        } else {
            GoiQua goiQua = new GoiQua();
            goiQua.setDip(this.thanhTich);
            goiQua.setNam(this.nam);
            goiQua.setMoTa(textEnterMoTa.getText());
            goiQua.setGiaTien(Double.parseDouble(textEnterDonGia.getText()));
            boolean success = phatQuaService.themGoiQua(goiQua);
            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Đã thêm 1 gói quà!");
                alert.setHeaderText("Completed!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Không thể thêm gói quà, có lỗi xảy ra!");
                alert.setHeaderText("Error!");
                alert.show();
            }
        }
    }

    @FXML
    void close(ActionEvent event){
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void themThanhTich(ActionEvent event) throws IOException {
        this.thanhTich = this.themThanhTich.getSelectionModel().getSelectedItem();
    }

    @FXML
    void themNam(ActionEvent event) throws IOException {
        if (this.themNam.getSelectionModel().getSelectedItem().toString().equals("Khác")) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("phatqua/hocsinh/Nam.fxml"));
            Parent root = loader.load();
            ThemNamHocController themNamHocController = loader.getController();
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/static/img/bieutuong.png"));
            stage.setScene(new Scene(root));
            stage.show();
        }
        else this.nam = Integer.parseInt(this.themNam.getSelectionModel().getSelectedItem());
    }


}
