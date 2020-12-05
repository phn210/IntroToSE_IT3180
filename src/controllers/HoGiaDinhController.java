package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.HoGiaDinh;
import services.HoGiaDinhService;

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
    private TextField textSearch;

    private HoGiaDinhService hoGiaDinhService;

    private ObservableList<HoGiaDinh> tableOblist;
    private ObservableList<String> comboBoxOblist;

    public HoGiaDinhController() {
        this.hoGiaDinhService = new HoGiaDinhService();
        this.comboBoxOblist = FXCollections.observableArrayList("ID Gia Đình", "Địa chỉ", "Chủ hộ", "SĐT");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxHoGiaDinh.setItems(this.comboBoxOblist);

        //Hien bang HoGiaDinh
        col_IDGiaDinh.setCellValueFactory(new PropertyValueFactory<>("IDGiaDinh"));
        col_DiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        col_TenChuHo.setCellValueFactory(new PropertyValueFactory<>("chuHo"));
        col_SDT.setCellValueFactory(new PropertyValueFactory<>("SDT"));

        List<HoGiaDinh> list = this.hoGiaDinhService.getListHoGiaDinh();
        tableOblist = FXCollections.observableList(list);
        hoGiaDinhTable.setItems(tableOblist);
    }

    //Search thong tin
    private String colIndex;

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
        if(textSearch.getText() == null){
            List<HoGiaDinh> list = this.hoGiaDinhService.getListHoGiaDinh();
            tableOblist = FXCollections.observableList(list);
            hoGiaDinhTable.setItems(tableOblist);
        }

        hoGiaDinhTable.getItems().clear();
        String key = textSearch.getText();

        List<HoGiaDinh> list = hoGiaDinhService.searchListHoGiaDinh(colIndex, key);
        this.tableOblist = FXCollections.observableList(list);
        hoGiaDinhTable.setItems(tableOblist);
    }
}