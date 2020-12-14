package controllers.thongke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.ChiTietThongKe;
import services.ThongKeService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CTThongKeController implements Initializable {

    @FXML
    private TableView<ChiTietThongKe> thongTinQuaTable;

    @FXML
    private TableColumn<ChiTietThongKe, Integer> col_ID;

    @FXML
    private TableColumn<ChiTietThongKe, String> col_Ten;

    @FXML
    private TableColumn<ChiTietThongKe, String> col_GoiQua;

    @FXML
    private TableColumn<ChiTietThongKe, Integer> col_GiaTien;

    @FXML
    private TableColumn<ChiTietThongKe, String> col_Dip;

    @FXML
    private TableColumn<ChiTietThongKe, Integer> col_Nam;

    @FXML
    private Label chuHoLabel;

    @FXML
    private ComboBox<String> comboBoxNam;

    @FXML
    private ComboBox<String> comboBoxDip;

    private ThongKeService thongKeService;

    private ObservableList<ChiTietThongKe> tableOblist;
    private ObservableList<String> namOblist, dipOblist;

    private String dip, nam;

    public CTThongKeController(){
        this.thongKeService = new ThongKeService();
        this.namOblist = FXCollections.observableArrayList("Tất cả", "2017", "2018", "2019", "2020");
        this.dipOblist = FXCollections.observableArrayList("Tất cả", "Tết dương lịch", "Tết Nguyên Đán", "Quốc tế thiếu nhi", "Trung Thu", "Khen thưởng hsg");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxNam.setItems(namOblist);
        comboBoxDip.setItems(dipOblist);

        //hien bang
        col_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_Ten.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        col_GoiQua.setCellValueFactory(new PropertyValueFactory<>("MoTa"));
        col_GiaTien.setCellValueFactory(new PropertyValueFactory<>("GiaTien"));
        col_Dip.setCellValueFactory(new PropertyValueFactory<>("Dip"));
        col_Nam.setCellValueFactory(new PropertyValueFactory<>("Nam"));

        List<ChiTietThongKe> list = this.thongKeService.getListHoGiaDinh();
        tableOblist = FXCollections.observableList(list);
        thongTinQuaTable.setItems(tableOblist);

    }

    @FXML
    void filter(ActionEvent event) {
        thongTinQuaTable.getItems().clear();

        List<ChiTietThongKe> list = thongKeService.filterListHoGiaDinh(dip, nam);
        this.tableOblist = FXCollections.observableList(list);
        thongTinQuaTable.setItems(tableOblist);
    }

    @FXML
    void selectComboBoxDip(ActionEvent event) {
        String temp = comboBoxDip.getSelectionModel().getSelectedItem().toString();
        switch(temp){
            case "Tất cả": this.dip = ""; break;
            case "Tết dương lịch": this.dip = "Tết dương lịch"; break;
            case "Tết Nguyên Đán": this.dip = "Tết Nguyên Đán"; break;
            case "Quốc tế thiếu nhi": this.dip = "Quốc tế thiếu nhi"; break;
            case "Trung Thu": this.dip = "Trung Thu"; break;
            case "Khen thưởng hsg": this.dip = "Khen thưởng hsg"; break;
        }
    }

    @FXML
    void selectComboBoxNam(ActionEvent event) {
        String temp = comboBoxNam.getSelectionModel().getSelectedItem().toString();
        switch(temp){
            case "Tất cả": this.nam = ""; break;
            case "2017": this.nam = "2017"; break;
            case "2018": this.nam = "2018"; break;
            case "2019": this.nam = "2019"; break;
            case "2020": this.nam = "2020"; break;
        }
    }

    public void setTextChuHoLabel(String tenChuHo) {
        chuHoLabel.setText(tenChuHo);
    }

}
