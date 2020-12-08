package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.HoGiaDinh;
import models.ThieuNhi;
import services.HoGiaDinhService;
import services.ThieuNhiService;
import views.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ThieuNhiController implements Initializable {

    @FXML
    private ComboBox chonNam;

    @FXML
    private ComboBox chonDip;

    @FXML
    private TextField timTextTen;

    @FXML
    private TextField timTextGioiTinh;

    @FXML
    private TextField timTextTuoi;

    @FXML
    private TextField timTextChuHo;

    private ThieuNhiService thieuNhiService;

    private ObservableList<ThieuNhi> tableOblist;
    private ObservableList<String> comboBoxOblist;

    public ThieuNhiController(){
        this.thieuNhiService = new ThieuNhiService();
        this.comboBoxOblist = FXCollections.observableArrayList("Tên", "Giới tính", "Ngày sinh", "Tuổi", "Tên chủ hộ");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> nam = FXCollections.observableArrayList("2017", "2018", "2019", "2020");
        chonNam.setItems(nam);
        ObservableList<String> dip = FXCollections.observableArrayList("Tết dương lịch", "Tết Nguyên Đán", "Quốc tế thiếu nhi", "Trung Thu");
        chonDip.setItems(dip);
    }

    public void phatQua(ActionEvent event){

    }

    public void hoanTac(ActionEvent event){

    }

    public void xemThongTin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("phatqua/thieunhi/ThongTinQuaTN.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void themGoiQua(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("phatqua/thieunhi/ThemGoiQuaTN.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void timKiem(ActionEvent event){

    }
}
