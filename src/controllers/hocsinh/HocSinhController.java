package controllers.hocsinh;

import controllers.thieunhi.ThongTinQuaThieuNhiController;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.GoiQua;
import models.HocSinh;
import services.HocSinhService;
import services.NhanKhauService;
import services.PhatQuaService;
import views.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class HocSinhController implements Initializable {

    @FXML
    private ComboBox<Integer> chonNam;

    @FXML
    private ComboBox<String> chonThanhTich;

    @FXML
    private TextField timTextTen;

    @FXML
    private TextField timTextGioiTinh;

    @FXML
    private TextField timTextTuoi;

    @FXML
    private TextField timTextChuHo;

    @FXML
    private TableView<HocSinh> hocSinhTable;

    @FXML
    private TableColumn<HocSinh, String> col_Ten;

    @FXML
    private TableColumn<HocSinh, String> col_GioiTinh;

    @FXML
    private TableColumn<HocSinh, Date> col_NgaySinh;

    @FXML
    private TableColumn<HocSinh, String> col_ChuHo;

    @FXML
    private TableColumn<HocSinh, String> col_DiaChi;

    @FXML
    private TableColumn<HocSinh, String> col_SDT;

    private int nam;
    private String thanhTich;

    private HocSinhService hocSinhService;
    private PhatQuaService phatQuaService;

    private ObservableList<HocSinh> tableOblist;
    private ObservableList<String> comboBoxOblist;

    public HocSinhController(){
        this.nam = 0;
        this.thanhTich = "";
        this.hocSinhService = new HocSinhService();
        this.phatQuaService = new PhatQuaService();
        this.comboBoxOblist = FXCollections.observableArrayList("Tên", "Giới tính", "Ngày sinh", "Tuổi", "Tên chủ hộ");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ObservableList<String> thanhTich = FXCollections.observableArrayList(phatQuaService.getAllThanhTich());
        chonThanhTich.setItems(thanhTich);
        ObservableList<Integer> nam = FXCollections.observableArrayList(phatQuaService.getAllNamHoc());
        chonNam.setItems(nam);

        col_Ten.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        col_GioiTinh.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
        col_NgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
        col_ChuHo.setCellValueFactory(t -> new ReadOnlyObjectWrapper<>(t.getValue().hoGiaDinh.getChuHo()));
        col_DiaChi.setCellValueFactory(t -> new ReadOnlyObjectWrapper<>(t.getValue().hoGiaDinh.getDiaChi()));
        col_SDT.setCellValueFactory(t -> new ReadOnlyObjectWrapper<>(t.getValue().hoGiaDinh.getSDT()));

        List<HocSinh> list = this.hocSinhService.getAll();
        tableOblist = FXCollections.observableList(list);
        hocSinhTable.setItems(tableOblist);
    }

    @FXML
    void phatQua(ActionEvent event) {

    }

    @FXML
    void xemThongTin(ActionEvent event) throws IOException {
        if (this.nam == 0 || this.thanhTich.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Chọn năm học và thành tích!");
            alert.setHeaderText("Warning!");
            alert.showAndWait();
        } else {
            GoiQua goiQua = phatQuaService.getGoiQua(this.nam, this.thanhTich);
            if (goiQua == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Gói quà chưa tồn tại, vui lòng thêm mới!");
                alert.setHeaderText("Warning!");
                alert.show();
            } else {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("phatqua/hocsinh/ThongTinQuaHS.fxml"));
                Parent root = loader.load();

                ThongTinQuaHocSinhController thongTinQuaHocSinhController = (ThongTinQuaHocSinhController) loader.getController();
                thongTinQuaHocSinhController.initialize(goiQua);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        }

    }

    @FXML
    void themGoiQua(ActionEvent event) {

    }

    @FXML
    void timKiem(ActionEvent event) {
        String ten = timTextTen.getText();
        String gioiTinh = timTextGioiTinh.getText();
        String tuoi = timTextTuoi.getText();
        String chuHo = timTextChuHo.getText();

        List<HocSinh> list = hocSinhService.search(ten, gioiTinh, tuoi, chuHo);
        this.tableOblist = FXCollections.observableList(list);
        hocSinhTable.setItems(tableOblist);
    }

    @FXML
    void handleRow(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getClickCount() == 2 && this.hocSinhTable.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("phatqua/hocsinh/ThanhTich.fxml"));
            Parent root = loader.load();
            ThanhTichController thanhTichController = loader.getController();
            thanhTichController.initialize(this.hocSinhTable.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("/static/img/bieutuong.png"));
            stage.setTitle("Thành tích học tập");
            stage.show();
        }
    }

}
