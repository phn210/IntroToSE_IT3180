package controllers.thongke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CTThongKeController {

    @FXML
    private TableView<?> thongTinQuaTable;

    @FXML
    private TableColumn<?, ?> col_ID;

    @FXML
    private TableColumn<?, ?> col_Ten;

    @FXML
    private TableColumn<?, ?> col_GoiQua;

    @FXML
    private TableColumn<?, ?> col_GiaTien;

    @FXML
    private TableColumn<?, ?> col_Dip;

    @FXML
    private TableColumn<?, ?> col_Nam;

    @FXML
    private Label chuHoLabel;

    @FXML
    private ComboBox<?> comboBoxNam;

    @FXML
    private ComboBox<?> comboBoxDip;

    @FXML
    void filter(ActionEvent event) {

    }

    @FXML
    void selectComboBoxDip(ActionEvent event) {

    }

    @FXML
    void selectComboBoxNam(ActionEvent event) {

    }

}
