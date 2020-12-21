package controllers.hocsinh;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.HocSinh;
import models.ThanhTich;
import services.HocSinhService;
import services.PhatQuaService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ThanhTichController {

    @FXML
    private TextField textTen;

    @FXML
    private TextField textNgaySinh;

    @FXML
    private TableView<ThanhTich> tableThanhTich;

    @FXML
    private TableColumn<ThanhTich, Integer> col_NamHoc;

    @FXML
    private TableColumn<ThanhTich, String> col_ThanhTich;

    @FXML
    private TableColumn<ThanhTich, String> col_Truong;

    private HocSinh hocSinh;
    private HocSinhService hocSinhService;
    private PhatQuaService phatQuaService;

    private ObservableList<ThanhTich> tableObList;

    public void initialize(HocSinh hocSinh) {
        this.hocSinh = hocSinh;
        this.textTen.setText(hocSinh.getTen());
        this.textNgaySinh.setText(hocSinh.getNgaySinh().toString());

        col_NamHoc.setCellValueFactory(new PropertyValueFactory<>("NamHoc"));
        col_ThanhTich.setCellValueFactory(new PropertyValueFactory<>("ThanhTich"));
        col_Truong.setCellValueFactory(new PropertyValueFactory<>("Truong"));

        List<ThanhTich> list = this.hocSinhService.getListThanhTich(hocSinh);
        hocSinh.addDsThanhTich((ArrayList<ThanhTich>) list);
        tableObList = FXCollections.observableList(list);
        tableThanhTich.setItems(tableObList);
    }

    public void updateTable(){
        col_NamHoc.setCellValueFactory(new PropertyValueFactory<>("NamHoc"));
        col_ThanhTich.setCellValueFactory(new PropertyValueFactory<>("ThanhTich"));
        col_Truong.setCellValueFactory(new PropertyValueFactory<>("Truong"));

        List<ThanhTich> list = this.hocSinhService.getListThanhTich(hocSinh);
        hocSinh.addDsThanhTich((ArrayList<ThanhTich>) list);
        tableObList = FXCollections.observableList(list);
        tableThanhTich.setItems(tableObList);
    }

    @FXML
    void addThanhTich(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {

    }
}
