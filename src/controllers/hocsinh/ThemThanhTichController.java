package controllers.hocsinh;

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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.HocSinh;
import models.ThanhTich;
import services.HocSinhService;
import services.PhatQuaService;
import views.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ThemThanhTichController{

    @FXML
    private ComboBox comboBoxThanhTich;

    @FXML
    private TextField textEnterNamHoc;

    @FXML
    private TextField textEnterTruong;

    private HocSinhService hocSinhService;
    private PhatQuaService phatQuaService;
    private HocSinh hocSinh;
    private String thanhTich;

    private ObservableList<String> listThanhTich;

    public ThemThanhTichController(){
        this.hocSinhService = new HocSinhService();
        this.phatQuaService = new PhatQuaService();
        this.hocSinh = new HocSinh();
        this.thanhTich = "";
    }

    public void initialize(HocSinh hocSinh) {
        this.listThanhTich = FXCollections.observableArrayList("Học sinh Giỏi", "Học sinh Khá", "Học sinh Còn lại", "Học sinh Thành tích Đặc biệt");
        this.comboBoxThanhTich.setItems(listThanhTich);
        this.hocSinh = hocSinh;
    }

    @FXML
    void add(ActionEvent event) {
        if (this.thanhTich.equals("")
                || this.textEnterNamHoc.getText().trim().isEmpty()
                || this.textEnterTruong.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Vui lòng điền đủ thông tin thành tích!");
            alert.setHeaderText("Warning!");
            alert.showAndWait();
        } else {
            ThanhTich thanhTich = new ThanhTich();
            thanhTich.setNamHoc(Integer.parseInt(this.textEnterNamHoc.getText()));
            thanhTich.setThanhTich(this.thanhTich);
            thanhTich.setTruong(this.textEnterTruong.getText());
            thanhTich.setID(this.hocSinh.getID());

            int rs = hocSinhService.themThanhTich(thanhTich);
            if (rs == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Đã sửa thành tích!");
                alert.setHeaderText("Completed!");
                alert.show();
            } else if (rs == 2){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Đã thêm mới thành tích!");
                alert.setHeaderText("Completed!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Không thể thêm thành tích!");
                alert.setHeaderText("Error!");
                alert.show();
            }
        }
    }

    @FXML
    void themThanhTich(ActionEvent event) throws IOException {
        this.thanhTich = this.comboBoxThanhTich.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void close(ActionEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

}
