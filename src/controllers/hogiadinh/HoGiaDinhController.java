package controllers.hogiadinh;

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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.HoGiaDinh;
import services.HoGiaDinhService;
import services.NhanKhauService;
import views.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class HoGiaDinhController implements Initializable{

    @FXML
    private TableView<HoGiaDinh> hoGiaDinhTable;
    @FXML
    private TableColumn<HoGiaDinh, Integer> col_IDGiaDinh;
    @FXML
    private TableColumn<HoGiaDinh, String> col_DiaChi;
    @FXML
    private TableColumn<HoGiaDinh, String> col_TenChuHo;
    @FXML
    private TableColumn<HoGiaDinh, String> col_SDT;
    @FXML
    private ComboBox<String> comboBoxHoGiaDinh;
    @FXML
    private TextField textSearchHGD;

    private HoGiaDinhService hoGiaDinhService;

    private ObservableList<HoGiaDinh> tableOblist;
    private ObservableList<String> comboBoxOblist;

    public static Stage themHGDStage = new Stage();
    public static Stage suaHGDStage = new Stage();


    //Search thong tin
    private String colIndex;

    public HoGiaDinhController() {
        this.hoGiaDinhService = new HoGiaDinhService();
        this.comboBoxOblist = FXCollections.observableArrayList("ID Gia Đình", "Địa chỉ", "Chủ hộ", "SĐT");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxHoGiaDinh.setItems(this.comboBoxOblist);

        //Hien bang HoGiaDinh
        col_IDGiaDinh.setCellValueFactory(new PropertyValueFactory<>("IDGiaDinh"));
        col_DiaChi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
        col_TenChuHo.setCellValueFactory(new PropertyValueFactory<>("ChuHo"));
        col_SDT.setCellValueFactory(new PropertyValueFactory<>("SDT"));

        List<HoGiaDinh> list = this.hoGiaDinhService.getListHoGiaDinh();
        tableOblist = FXCollections.observableList(list);
        hoGiaDinhTable.setItems(tableOblist);
    }

    @FXML
    private void comboBoxSelect(ActionEvent event) {
        String temp = comboBoxHoGiaDinh.getSelectionModel().getSelectedItem().toString();
        switch (temp){
            case "ID Gia Đình": this.colIndex = "IDGiaDinh"; break;
            case "Chủ hộ": this.colIndex = "ChuHo"; break;
            case "Địa chỉ": this.colIndex = "DiaChi"; break;
            case "SĐT": this.colIndex = "SDT"; break;
        }
    }

    @FXML
    public void search(ActionEvent event) throws SQLException {
        if(textSearchHGD.getText() == null){
            List<HoGiaDinh> list = this.hoGiaDinhService.getListHoGiaDinh();
            tableOblist = FXCollections.observableList(list);
            hoGiaDinhTable.setItems(tableOblist);
        }

        hoGiaDinhTable.getItems().clear();
        String key = textSearchHGD.getText();

        List<HoGiaDinh> list = hoGiaDinhService.searchListHoGiaDinh(colIndex, key);
        this.tableOblist = FXCollections.observableList(list);
        hoGiaDinhTable.setItems(tableOblist);
    }

    @FXML
    public void add(ActionEvent event) throws IOException {
        themHGDStage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("hogiadinh/ThemHGD.fxml"))));
        themHGDStage.setTitle("Thêm hộ gia đình");
        themHGDStage.show();

        themHGDStage.setOnCloseRequest((e)->{
            updateTable();
        });
    }

    @FXML
    void delete(ActionEvent event) throws SQLException {
        HoGiaDinh hoGiaDinhModel = hoGiaDinhTable.getSelectionModel().getSelectedItem();
        //canh bao
        if(hoGiaDinhModel == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Chọn 1 hộ gia đình đi đã bạn  -_-");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn chắc muốn xóa?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES) {
            int IDGiaDinh = hoGiaDinhModel.getIDGiaDinh();
            hoGiaDinhService.deleteListHoGiaDinh(IDGiaDinh);
            hoGiaDinhTable.getItems().removeAll(hoGiaDinhTable.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void edit(ActionEvent event) throws IOException {
        HoGiaDinh hoGiaDinhModel = hoGiaDinhTable.getSelectionModel().getSelectedItem();
        //canh bao
        if(hoGiaDinhModel == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Chọn 1 hộ gia đình đi đã bạn  -_-");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("hogiadinh/SuaHGD.fxml"));
        Parent parent = loader.load();

        SuaHGDController suaHGDController = (SuaHGDController) loader.getController();
        suaHGDController.initializeTextField(hoGiaDinhModel);

        suaHGDStage.setScene(new Scene(parent));
        suaHGDStage.setTitle("Sửa hộ gia đình");
        suaHGDStage.show();

        suaHGDStage.setOnCloseRequest((e)->{
            updateTable();
        });
    }

    private void updateTable(){
        col_IDGiaDinh.setCellValueFactory(new PropertyValueFactory<>("IDGiaDinh"));
        col_DiaChi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
        col_TenChuHo.setCellValueFactory(new PropertyValueFactory<>("ChuHo"));
        col_SDT.setCellValueFactory(new PropertyValueFactory<>("SDT"));

        List<HoGiaDinh> list = this.hoGiaDinhService.getListHoGiaDinh();
        tableOblist = FXCollections.observableList(list);
        hoGiaDinhTable.setItems(tableOblist);
    }

    @FXML
    public void handleRow(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getClickCount() == 2 && this.hoGiaDinhTable.getSelectionModel().getSelectedItem() != null) {
            int IDGD = this.hoGiaDinhTable.getSelectionModel().getSelectedItem().getIDGiaDinh();
            NhanKhauService nhanKhauService = new NhanKhauService();
            nhanKhauService.IDGiaDinhNK = IDGD;
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("nhankhau/NhanKhau.fxml"))));
            stage.getIcons().add(new Image("/static/img/bieutuong.png"));
            stage.setTitle("Thành viên trong gia đình");
            stage.show();
        }
    }

}