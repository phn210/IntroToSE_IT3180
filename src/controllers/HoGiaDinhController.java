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

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HoGiaDinhController implements Initializable{

    @FXML private TableView<HoGiaDinh> hoGiaDinhTable;
    @FXML private TableColumn<HoGiaDinh, Integer> col_IDGiaDinh;
    @FXML private TableColumn<HoGiaDinh, String> col_DiaChi;
    @FXML private TableColumn<HoGiaDinh, String> col_TenChuHo;
    @FXML private TableColumn<HoGiaDinh, String> col_SDT;
    @FXML private ComboBox<String> comboBoxHoGiaDinh;
    @FXML private TextField textSearch;

    ObservableList<HoGiaDinh> tableOblist = FXCollections.observableArrayList();
    ObservableList<String> comboBoxOblist = FXCollections.observableArrayList("IDGiaDinh", "DiaChi", "ChuHo", "SDT");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxHoGiaDinh.setItems(comboBoxOblist);

        //Hien bang HoGiaDinh
        col_IDGiaDinh.setCellValueFactory(new PropertyValueFactory<>("IDGiaDinh"));
        col_DiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        col_TenChuHo.setCellValueFactory(new PropertyValueFactory<>("chuHo"));
        col_SDT.setCellValueFactory(new PropertyValueFactory<>("SDT"));

        try {

            Connection conn = DBConnection.getConnection();
            ResultSet rs = DBConnection.getData("select * from HoGiaDinh", conn);

            while(rs.next()){
                tableOblist.add(new HoGiaDinh(rs.getInt("IDGiaDinh"), rs.getString("DiaChi"),
                                        rs.getString("ChuHo"), rs.getString("SDT")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        hoGiaDinhTable.setItems(tableOblist);
    }

    //Search thong tin
    String cot;
    @FXML
    void comboBoxSelect(ActionEvent event) {
        cot = comboBoxHoGiaDinh.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void search(ActionEvent event) {
        try {
            hoGiaDinhTable.getItems().clear();
            String hang = textSearch.getText();
            Connection conn = DBConnection.getConnection();
            ResultSet rs = DBConnection.getData("select * from HoGiaDinh where " + cot + " LIKE " +"N'%"+hang+"%'", conn);

            while(rs.next()){
                tableOblist.add(new HoGiaDinh(rs.getInt("IDGiaDinh"), rs.getString("DiaChi"),
                        rs.getString("ChuHo"), rs.getString("SDT")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        hoGiaDinhTable.setItems(tableOblist);
    }
}