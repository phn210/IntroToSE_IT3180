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
    private ObservableList<String> namOblist, dipOblist;

    private String nam, dip, doiTuong; //Gia tri chon tu combobox

    public ThongKeController(){
        this.hoGiaDinhService = new HoGiaDinhService();
        this.thongKeService = new ThongKeService();
        this.namOblist = FXCollections.observableArrayList("Tất cả", "2017", "2018", "2019", "2020");
        this.dipOblist = FXCollections.observableArrayList("Tất cả", "Tết dương lịch", "Tết Nguyên Đán", "Quốc tế thiếu nhi", "Trung Thu", "Khen thưởng hsg", "Noel");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxNam.setItems(namOblist);
        comboBoxDip.setItems(dipOblist);

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
        String temp = comboBoxDip.getSelectionModel().getSelectedItem().toString();
        switch(temp){
            case "Tất cả": this.dip = ""; break;
            case "Tết dương lịch": this.dip = "Tết dương lịch"; break;
            case "Tết Nguyên Đán": this.dip = "Tết Nguyên Đán"; break;
            case "Quốc tế thiếu nhi": this.dip = "Quốc tế thiếu nhi"; break;
            case "Trung Thu": this.dip = "Trung Thu"; break;
            case "Khen thưởng hsg": this.dip = "Khen thưởng hsg"; break;
            case "Noel": this.dip = "Noel"; break;
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

    @FXML
    void countMoney(ActionEvent event) throws SQLException {
        HoGiaDinh hoGiaDinhModel = hoGiaDinhTable.getSelectionModel().getSelectedItem();
        //canh bao
        if(hoGiaDinhModel == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Chọn 1 hộ gia đình đã bạn -_-");
            alert.showAndWait();
        }

        int IDGiaDinh = hoGiaDinhModel.getIDGiaDinh();
        try {
            labelMoney.setText(String.valueOf(thongKeService.countMoney(nam, dip, IDGiaDinh) + " vnđ"));
        }catch(SQLServerException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thiếu thông tin trong database");
            alert.showAndWait();
            e.printStackTrace();
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

}
