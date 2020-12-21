package controllers.thieunhi;

import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.stage.Stage;
import models.GoiQua;
import models.ThieuNhi;
import services.PhatQuaService;
import services.ThieuNhiService;
import views.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ThieuNhiController implements Initializable {

    @FXML
    private ComboBox chonNam;
    @FXML
    private ComboBox chonDip;
    @FXML
    private TableView<ThieuNhi> thieuNhiTable;
    @FXML
    private TableColumn<ThieuNhi, String> col_Ten;
    @FXML
    private TableColumn<ThieuNhi, String> col_GioiTinh;
    @FXML
    private TableColumn<ThieuNhi, Date> col_NgaySinh;
    @FXML
    private TableColumn<ThieuNhi, Integer> col_Tuoi;
    @FXML
    private TableColumn<ThieuNhi, String> col_ChuHo;
    @FXML
    private TableColumn<ThieuNhi, String> col_DiaChi;
    @FXML
    private TextField timTextTen;
    @FXML
    private TextField timTextGioiTinh;
    @FXML
    private TextField timTextTuoi;
    @FXML
    private TextField timTextChuHo;

    private int nam;
    private String dip;

    private ThieuNhiService thieuNhiService;
    private PhatQuaService phatQuaService;

    private Stage thongTinStage;
    private Stage themStage;

    private ObservableList<ThieuNhi> tableOblist;
    private ObservableList<String> comboBoxOblist;

    public static ObservableList<Integer> namComboBox;
    public static ObservableList<String> dipComboBox;

    public ThieuNhiController(){
        this.nam = 0;
        this.dip = "";
        this.thieuNhiService = new ThieuNhiService();
        this.phatQuaService = new PhatQuaService();
        this.comboBoxOblist = FXCollections.observableArrayList("Tên", "Giới tính", "Ngày sinh", "Tuổi", "Tên chủ hộ");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        namComboBox = FXCollections.observableArrayList(phatQuaService.getAllNamDip());
        chonNam.setItems(namComboBox);
        dipComboBox = FXCollections.observableArrayList(phatQuaService.getAllDip());
        chonDip.setItems(dipComboBox);

        col_Ten.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        col_GioiTinh.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
        col_NgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
        col_Tuoi.setCellValueFactory(new PropertyValueFactory<>("Tuoi"));
        col_ChuHo.setCellValueFactory(t -> new ReadOnlyObjectWrapper<>(t.getValue().hoGiaDinh.getChuHo()));
        col_DiaChi.setCellValueFactory(t -> new ReadOnlyObjectWrapper<>(t.getValue().hoGiaDinh.getDiaChi()));

        List<ThieuNhi> list = this.thieuNhiService.getAll();
        tableOblist = FXCollections.observableList(list);
        thieuNhiTable.setItems(tableOblist);
    }

//    public void updateTable(){
//        col_Ten.setCellValueFactory(new PropertyValueFactory<>("Ten"));
//        col_GioiTinh.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
//        col_NgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
//        col_Tuoi.setCellValueFactory(new PropertyValueFactory<>("Tuoi"));
//        col_ChuHo.setCellValueFactory(t -> new ReadOnlyObjectWrapper<>(t.getValue().hoGiaDinh.getChuHo()));
//        col_DiaChi.setCellValueFactory(t -> new ReadOnlyObjectWrapper<>(t.getValue().hoGiaDinh.getDiaChi()));
//
//        List<ThieuNhi> list = this.thieuNhiService.getAll();
//        tableOblist = FXCollections.observableList(list);
//        thieuNhiTable.setItems(tableOblist);
//    }

    @FXML
    void chonDip(ActionEvent event) {
        this.dip = this.chonDip.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void chonNam(ActionEvent event) {
        nam = (int) chonNam.getSelectionModel().getSelectedItem();
    }

    @FXML
    void phatQua(ActionEvent event){
        List<ThieuNhi> error = new ArrayList<>();
        GoiQua goiQua = phatQuaService.getGoiQua(nam, dip);
        if (goiQua == null){
            //thong bao goi qua chua ton tai
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Gói quà chưa tồn tại!");
            alert.setHeaderText("Warning!");
            alert.show();
        } else {
            List<ThieuNhi> list = thieuNhiService.getAll(nam);
            for (ThieuNhi thieuNhi: list) {
                boolean check = phatQuaService.phatQuaTN(thieuNhi, goiQua);
                if(!check)
                    error.add(thieuNhi);
            }
        }
        if(error.size() > 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Có " + error.size() + " người không thể phát quà!");
            alert.setHeaderText("Warning!");
            alert.show();
        }
    }

    @FXML
    void xemThongTin(ActionEvent event) throws IOException {
        if (this.nam == 0 || this.dip.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Chọn năm và dịp!");
            alert.setHeaderText("Warning!");
            alert.showAndWait();
        } else {
            GoiQua goiQua = phatQuaService.getGoiQua(this.nam, this.dip);
            if (goiQua == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Gói quà chưa tồn tại, vui lòng thêm mới!");
                alert.setHeaderText("Warning!");
                alert.show();
            } else {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("phatqua/thieunhi/ThongTinQuaTN.fxml"));
                Parent root = loader.load();

                ThongTinQuaThieuNhiController thongTinQuaThieuNhiController = (ThongTinQuaThieuNhiController) loader.getController();
                thongTinQuaThieuNhiController.initialize(goiQua);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        }
    }

    @FXML
    void themGoiQua(ActionEvent event) throws IOException {
        GoiQua goiQua = phatQuaService.getGoiQua(nam, dip);
        if (!(goiQua == null)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Gói quà đã tồn tại!");
            alert.setHeaderText("Warning!");
            alert.show();
        } else {
            Parent root = FXMLLoader.load(Main.class.getResource("phatqua/thieunhi/ThemGoiQuaTN.fxml"));
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/static/img/bieutuong.png"));
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    void timKiem(ActionEvent event){
        String ten = timTextTen.getText();
        String gioiTinh = timTextGioiTinh.getText();
        String tuoi = timTextTuoi.getText();
        String chuHo = timTextChuHo.getText();

        List<ThieuNhi> list = thieuNhiService.search(ten, gioiTinh, tuoi, chuHo);
        this.tableOblist = FXCollections.observableList(list);
        thieuNhiTable.setItems(tableOblist);
    }
}
