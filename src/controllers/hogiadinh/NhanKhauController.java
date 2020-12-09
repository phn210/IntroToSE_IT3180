package controllers.hogiadinh;

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
import models.NhanKhau;
import services.NhanKhauService;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class NhanKhauController implements Initializable {

    @FXML
    private TableView<NhanKhau> nhanKhauTable;
    @FXML
    private TableColumn<NhanKhau, Integer> col_IDGiaDinhNK;
    @FXML
    private TableColumn<NhanKhau, String> col_Ten;
    @FXML
    private TableColumn<NhanKhau, String> col_GioiTinh;
    @FXML
    private TableColumn<NhanKhau, Date> col_NgaySinh;
    @FXML
    private TableColumn<NhanKhau, String> col_NgheNghiep;
    @FXML
    private TableColumn<NhanKhau, Integer> col_ID;
    @FXML
    private ComboBox<String> comboBoxNhanKhau;
    @FXML
    private TextField textSearchHGD;

    private NhanKhauService nhanKhauService;

    private ObservableList<NhanKhau> tableOblist;
    private ObservableList<String> comboBoxOblist;

    //Search thong tin
    private String colIndex;

    public NhanKhauController() {
        this.nhanKhauService = new NhanKhauService();
        this.comboBoxOblist = FXCollections.observableArrayList("ID Gia Đình", "Tên", "Giới tính", "Ngày sinh", "Nghề nghiệp", "Số CMND");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxNhanKhau.setItems(this.comboBoxOblist);

        //Hien bang Nhan Khau
        col_IDGiaDinhNK.setCellValueFactory(new PropertyValueFactory<>("IDGiaDinh"));
        col_Ten.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        col_GioiTinh.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
        col_NgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
        col_NgheNghiep.setCellValueFactory(new PropertyValueFactory<>("NgheNghiep"));
        col_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));

        List<NhanKhau> list = this.nhanKhauService.getListNhanKhau();
        tableOblist = FXCollections.observableList(list);
        nhanKhauTable.setItems(tableOblist);
    }

    public void searchNhanKhau(ActionEvent event) {
    }

}
