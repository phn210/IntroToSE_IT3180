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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.ThieuNhi;
import services.ThieuNhiService;
import views.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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


    private ThieuNhiService thieuNhiService;

    private ObservableList<ThieuNhi> tableOblist;
    private ObservableList<String> comboBoxOblist;

    public ThieuNhiController(){
        this.thieuNhiService = new ThieuNhiService();
        this.comboBoxOblist = FXCollections.observableArrayList("Tên", "Giới tính", "Ngày sinh", "Tuổi", "Tên chủ hộ");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> nam = FXCollections.observableArrayList("2017", "2018", "2019", "2020");
        chonNam.setItems(nam);
        ObservableList<String> dip = FXCollections.observableArrayList("Tết dương lịch", "Tết Nguyên Đán", "Quốc tế thiếu nhi", "Trung Thu");
        chonDip.setItems(dip);

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

    public void phatQua(ActionEvent event){

    }

    public void hoanTac(ActionEvent event){

    }

    public void xemThongTin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("phatqua/thieunhi/ThongTinQuaTN.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void themGoiQua(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("phatqua/thieunhi/ThemGoiQuaTN.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void timKiem(ActionEvent event){
        
    }
}
