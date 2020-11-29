package controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.HoGiaDinh;
import controllers.DBConnection;

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

    ObservableList<HoGiaDinh> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Hien bang HoGiaDinh
        col_IDGiaDinh.setCellValueFactory(new PropertyValueFactory<>("IDGiaDinh"));
        col_DiaChi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
        col_TenChuHo.setCellValueFactory(new PropertyValueFactory<>("chuHo"));
        col_SDT.setCellValueFactory(new PropertyValueFactory<>("SDT"));

        try {

            Connection conn = DBConnection.getConnection();
            ResultSet rs = DBConnection.getData("select * from HoGiaDinh", conn);

            while(rs.next()){
                oblist.add(new HoGiaDinh(rs.getInt("IDGiaDinh"), rs.getString("DiaChi"),
                                        rs.getString("ChuHo"), rs.getString("SDT")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        hoGiaDinhTable.setItems(oblist);
    }
}