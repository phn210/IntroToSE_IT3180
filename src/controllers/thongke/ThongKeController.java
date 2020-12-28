package controllers.thongke;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import controllers.hogiadinh.SuaHGDController;
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
import models.HoGiaDinh;
import services.HoGiaDinhService;
import services.NhanKhauService;
import services.PhatQuaService;
import services.ThongKeService;
import views.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ThongKeController implements Initializable {

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
    private ComboBox<String> comboBoxNam;

    @FXML
    private ComboBox<String> comboBoxDip;

    @FXML
    private Label labelMoney;

    private HoGiaDinhService hoGiaDinhService;
    private ThongKeService thongKeService;

    private ObservableList<HoGiaDinh> tableOblist;
    private ObservableList<String> dipOblist, namOblist;

    private String nam, dip; //Gia tri chon tu combobox

    private ObservableList<String> bangOblist = FXCollections.observableArrayList("HoGiaDinh", "ThanhTich", "NhanKhau", "PhatQua", "GoiQua");

    @FXML
    private ComboBox<String> comboBoxBang;

    public ThongKeController(){
        this.hoGiaDinhService = new HoGiaDinhService();
        this.thongKeService = new ThongKeService();

        this.namOblist = FXCollections.observableArrayList("Tất cả");
        namOblist.addAll(thongKeService.getAllNam());
        this.dipOblist = FXCollections.observableArrayList("Tất cả");
        dipOblist.addAll(thongKeService.getAllDip());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxNam.setItems(namOblist);
        comboBoxDip.setItems(dipOblist);
        comboBoxBang.setItems(bangOblist);

        //Hien bang HoGiaDinh
        col_IDGiaDinh.setCellValueFactory(new PropertyValueFactory<>("IDGiaDinh"));
        col_DiaChi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
        col_TenChuHo.setCellValueFactory(new PropertyValueFactory<>("ChuHo"));
        col_SDT.setCellValueFactory(new PropertyValueFactory<>("SDT"));

        List<HoGiaDinh> list = this.hoGiaDinhService.getListHoGiaDinh();
        tableOblist = FXCollections.observableList(list);
        hoGiaDinhTable.setItems(tableOblist);
    }

    @FXML
    void selectComboBoxDip(ActionEvent event) {
        dip = comboBoxDip.getSelectionModel().getSelectedItem().toString();
        if(dip == "Tất cả"){
            dip = "";
        }
    }

    @FXML
    void selectComboBoxNam(ActionEvent event) {
        nam = comboBoxNam.getSelectionModel().getSelectedItem().toString();
        if(nam == "Tất cả"){
            nam = "";
        }
    }

    @FXML
    void countMoney(ActionEvent event) throws SQLException {
        HoGiaDinh hoGiaDinhModel;
        String IDGiaDinh;

        labelMoney.setText(String.valueOf(thongKeService.countMoney(nam, dip, "") + " vnđ"));

        if(hoGiaDinhTable.getSelectionModel().getSelectedItem() != null) {
            hoGiaDinhModel = hoGiaDinhTable.getSelectionModel().getSelectedItem();
            IDGiaDinh = String.valueOf(hoGiaDinhModel.getIDGiaDinh());
            try {
                labelMoney.setText(String.valueOf(thongKeService.countMoney(nam, dip, IDGiaDinh) + " vnđ"));
            } catch (SQLServerException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Thiếu thông tin trong database");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }

    @FXML
    void handleRow(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2 && hoGiaDinhTable.getSelectionModel().getSelectedItem() != null) {
            thongKeService.IDGiaDinh = hoGiaDinhTable.getSelectionModel().getSelectedItem().getIDGiaDinh();
            String tenChuHo = hoGiaDinhTable.getSelectionModel().getSelectedItem().getChuHo();

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("thongke/ChiTietThongKe.fxml"));
            Parent parent = loader.load();

            CTThongKeController ctThongKeController = (CTThongKeController) loader.getController();
            ctThongKeController.setTextChuHoLabel(tenChuHo);

            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.setTitle("Chi tiết thống kê");
            stage.show();
        }
    }

    //Excel

    @FXML
    void nhapExcel(ActionEvent event) throws IOException, SQLException {
        if(comboBoxBang.getSelectionModel().getSelectedItem() == "HoGiaDinh"){
            thongKeService.importExcelHGD();
        }
        else if(comboBoxBang.getSelectionModel().getSelectedItem() == "NhanKhau"){
            thongKeService.importExcelNhanKhau();
        }
        else if(comboBoxBang.getSelectionModel().getSelectedItem() == "GoiQua"){
            thongKeService.importExcelGoiQua();
        }
        else if(comboBoxBang.getSelectionModel().getSelectedItem() == "ThanhTich"){
            thongKeService.importExcelThanhTich();
        }
        else if(comboBoxBang.getSelectionModel().getSelectedItem() == "PhatQua"){
            thongKeService.importExcelPhatQua();
        }
    }

    @FXML
    void xuatExcel(ActionEvent event) throws IOException, SQLException {
        if(comboBoxBang.getSelectionModel().getSelectedItem() == "HoGiaDinh"){
            thongKeService.exportExcelHGD();
        }
        else if(comboBoxBang.getSelectionModel().getSelectedItem() == "NhanKhau"){
            thongKeService.exportExcelNhanKhau();
        }
        else if(comboBoxBang.getSelectionModel().getSelectedItem() == "GoiQua"){
            thongKeService.exportExcelGoiQua();
        }
        else if(comboBoxBang.getSelectionModel().getSelectedItem() == "ThanhTich"){
            thongKeService.exportExcelThanhTich();
        }
        else if(comboBoxBang.getSelectionModel().getSelectedItem() == "PhatQua"){
            thongKeService.exportExcelPhatQua();
        }
    }

}
