package controllers.nhankhau;

import controllers.hogiadinh.SuaHGDController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.HoGiaDinh;
import models.NhanKhau;
import services.NhanKhauService;
import views.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class NhanKhauController implements Initializable {

    @FXML
    private TableView<NhanKhau> nhanKhauTable;
    @FXML
    private TableColumn<NhanKhau, Integer> col_IDGiaDinhNK;
    @FXML
    private TableColumn<NhanKhau, String> col_Ten;
    @FXML
    private TableColumn<NhanKhau, String> col_GioiTinh;
    @FXML
    private TableColumn<NhanKhau, Date> col_NgaySinh;
    @FXML
    private TableColumn<NhanKhau, String> col_NgheNghiep;
    @FXML
    private TableColumn<NhanKhau, Integer> col_ID;
    @FXML
    private ComboBox<String> comboBoxNhanKhau;
    @FXML
    private TextField textSearchNhanKhau;

    private NhanKhauService nhanKhauService;

    private ObservableList<NhanKhau> tableOblist;
    private ObservableList<String> comboBoxOblist;

    public static Stage themNKStage = new Stage();
    public static Stage suaNKStage = new Stage();

    //Search thong tin
    private String colIndex;

    public NhanKhauController() {
        this.nhanKhauService = new NhanKhauService();
        this.comboBoxOblist = FXCollections.observableArrayList("ID Gia Đình", "Tên", "Giới tính", "Ngày sinh", "Nghề nghiệp", "Số CMND");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxNhanKhau.setItems(this.comboBoxOblist);

        //Hien bang Nhan Khau
        col_IDGiaDinhNK.setCellValueFactory(new PropertyValueFactory<>("IDGiaDinh"));
        col_Ten.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        col_GioiTinh.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
        col_NgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
        col_NgheNghiep.setCellValueFactory(new PropertyValueFactory<>("NgheNghiep"));
        col_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));

        List<NhanKhau> list = this.nhanKhauService.getListNhanKhau();
        tableOblist = FXCollections.observableList(list);
        nhanKhauTable.setItems(tableOblist);
    }

    @FXML
    private void comboBoxSelect(ActionEvent event) {
        String temp = comboBoxNhanKhau.getSelectionModel().getSelectedItem().toString();
        switch (temp){
            case "ID Gia Đình": this.colIndex = "IDGiaDinh"; break;
            case "Tên": this.colIndex = "Ten"; break;
            case "Giới tính": this.colIndex = "GioiTinh"; break;
            case "Ngày sinh": this.colIndex = "NgaySinh"; break;
            case "Nghề nghiệp": this.colIndex = "NgheNghiep"; break;
            case "Số CMND": this.colIndex = "ID"; break;
        }
    }

    @FXML
    public void searchNhanKhau(ActionEvent event) {
        nhanKhauTable.getItems().clear();
        String key = textSearchNhanKhau.getText();

        List<NhanKhau> list = nhanKhauService.searchListNhanKhau(colIndex, key);
        this.tableOblist = FXCollections.observableList(list);
        nhanKhauTable.setItems(tableOblist);
    }

    @FXML
    public void addNhanKhau(ActionEvent event) throws IOException {
        themNKStage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("nhankhau/ThemNK.fxml"))));
        themNKStage.setTitle("Thêm nhân khẩu");
        themNKStage.show();

        themNKStage.setOnCloseRequest((e)->{
            updateTable();
        });
    }

    @FXML
    void deleteNhanKhau(ActionEvent event) throws SQLException {
        NhanKhau nhanKhauModel = nhanKhauTable.getSelectionModel().getSelectedItem();
        //canh bao
        if(nhanKhauModel == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Chọn 1 hộ nhân khẩu đi đã bạn  -_-");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn chắc muốn xóa?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES) {
            int ID = nhanKhauModel.getID();
            nhanKhauService.deleteListNhanKhau(ID);
            nhanKhauTable.getItems().removeAll(nhanKhauTable.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void editNhanKhau(ActionEvent event) throws IOException {
        NhanKhau nhanKhauModel = nhanKhauTable.getSelectionModel().getSelectedItem();
        //canh bao
        if(nhanKhauModel == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Chọn 1 người đi đã bạn  -_-");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("nhankhau/SuaNK.fxml"));
        Parent parent = loader.load();

        SuaNKController suaNKController = (SuaNKController) loader.getController();
        suaNKController.initializeTextField(nhanKhauModel);

        NhanKhauController.suaNKStage.setScene(new Scene(parent));
        suaNKStage.setTitle("Sửa nhân khẩu");
        suaNKStage.show();

        suaNKStage.setOnCloseRequest((e)->{
            updateTable();
        });
    }

    private void updateTable(){
        col_IDGiaDinhNK.setCellValueFactory(new PropertyValueFactory<>("IDGiaDinh"));
        col_Ten.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        col_GioiTinh.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
        col_NgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
        col_NgheNghiep.setCellValueFactory(new PropertyValueFactory<>("NgheNghiep"));
        col_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));

        List<NhanKhau> list = this.nhanKhauService.getListNhanKhau();
        tableOblist = FXCollections.observableList(list);
        nhanKhauTable.setItems(tableOblist);
    }



}
