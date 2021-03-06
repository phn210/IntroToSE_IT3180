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
import models.ThieuNhi;
import services.HocSinhService;
import services.NhanKhauService;
import services.PhatQuaService;
import views.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
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

    private ObservableList<String> listThanhTich;
    private ObservableList<Integer> listNam;

    public HocSinhController(){
        this.nam = 0;
        this.thanhTich = "";
        this.hocSinhService = new HocSinhService();
        this.phatQuaService = new PhatQuaService();
        this.comboBoxOblist = FXCollections.observableArrayList("Tên", "Giới tính", "Ngày sinh", "Tuổi", "Tên chủ hộ");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        listThanhTich = FXCollections.observableArrayList("Học sinh Giỏi", "Học sinh Khá", "Học sinh Còn lại", "Học sinh Thành tích Đặc biệt");
        chonThanhTich.setItems(listThanhTich);
        listNam = FXCollections.observableArrayList(phatQuaService.getAllNamHoc());
        chonNam.setItems(listNam);

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
        List<HocSinh> error = new ArrayList<>();
        GoiQua goiQua = phatQuaService.getGoiQua(nam, thanhTich);
        if (goiQua == null) {
            //thong bao goi qua chua ton tai
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Gói quà chưa tồn tại!");
            alert.setHeaderText("Warning!");
            alert.show();
        } else {
            List<HocSinh> list = hocSinhService.getAll(nam, thanhTich);
            System.out.println(list.size());
            for (HocSinh hocSinh: list) {
                boolean check = phatQuaService.phatQuaHS(hocSinh, goiQua);
                if (!check)
                    error.add(hocSinh);
            }
            if (error.size() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Có " + error.size() + " em đã được phát quà từ trước!");
                alert.setHeaderText("Warning!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Phát quà thành công!");
                alert.setHeaderText("Completed!");
                alert.show();
            }
        }
    }

    @FXML
    void chonNam(ActionEvent event){
        this.nam = this.chonNam.getSelectionModel().getSelectedItem();
    }

    @FXML
    void chonThanhTich(ActionEvent event){
        this.thanhTich = this.chonThanhTich.getSelectionModel().getSelectedItem();
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

                ThongTinQuaHocSinhController thongTinQuaHocSinhController = loader.getController();
                thongTinQuaHocSinhController.initialize(goiQua);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        }

    }

    @FXML
    void themGoiQua(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("phatqua/hocsinh/ThemGoiQuaHS.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/static/img/bieutuong.png"));
        stage.setScene(new Scene(root));
        stage.show();
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
            stage.setOnCloseRequest((e) -> {
                listNam = FXCollections.observableArrayList(phatQuaService.getAllNamHoc());
                chonNam.setItems(listNam);
            });
        }
    }

}
