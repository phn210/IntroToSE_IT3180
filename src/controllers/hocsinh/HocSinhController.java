package controllers.hocsinh;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HocSinhController implements Initializable {

    @FXML
    private ComboBox dipHocSinh;

    @FXML
    private  ComboBox namHocSinh;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ObservableList<String> hocSinh = FXCollections.observableArrayList("Học sinh Giỏi", "Học sinh Khá", "Khác");
        dipHocSinh.setItems(hocSinh);
        ObservableList<String> namhocSinh = FXCollections.observableArrayList("2018", "2019", "2020");
        namHocSinh.setItems(namhocSinh);
    }

}
