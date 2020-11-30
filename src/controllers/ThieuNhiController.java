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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class ThieuNhiController implements Initializable {

    @FXML
    private ComboBox namThieuNhi;

    @FXML
    private  ComboBox dipThieuNhi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> nam = FXCollections.observableArrayList("2017", "2018", "2019", "2020");
        namThieuNhi.setItems(nam);
        ObservableList<String> dip = FXCollections.observableArrayList("Tết Nguyên Đán", "Trung Thu", "Quốc tế thiếu nhi", "Thôi nôi");
        dipThieuNhi.setItems(dip);
    }
}
