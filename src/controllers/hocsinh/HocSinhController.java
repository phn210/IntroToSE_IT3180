package controllers.hocsinh;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.HocSinh;
import services.HocSinhService;
import services.PhatQuaService;

import java.net.URL;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class HocSinhController implements Initializable {

    @FXML
    private ComboBox<String> chonNam;

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
        ObservableList<String> thanhTich = FXCollections.observableArrayList("Học sinh Giỏi", "Học sinh Khá", "Khác");
        chonThanhTich.setItems(thanhTich);
        ObservableList<String> nam = FXCollections.observableArrayList("2017", "2018", "2019", "2020");
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

    public void update(){

    }

    @FXML
    void phatQua(ActionEvent event) {

    }

    @FXML
    void xemThongTin(ActionEvent event) {

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


}
