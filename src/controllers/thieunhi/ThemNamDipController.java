package controllers.thieunhi;

import controllers.thieunhi.ThemQuaThieuNhiController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class ThemNamDipController {

    @FXML
    private TextField themDipMoi;
    @FXML
    private TextField themNamMoi;

    public ThemNamDipController(){
    }

    @FXML
    void themNamMoi(ActionEvent event) {
        ThemQuaThieuNhiController.nam.add(this.themNamMoi.getText());
        ThemQuaThieuNhiController.nam.removeAll("Khác");
        ThemQuaThieuNhiController.nam.add("Khác");
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void themDipMoi(ActionEvent event) {
        ThemQuaThieuNhiController.dip.add(this.themDipMoi.getText());
        ThemQuaThieuNhiController.dip.removeAll("Khác");
        ThemQuaThieuNhiController.dip.add("Khác");
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void themDip(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER){
            ThemQuaThieuNhiController.dip.add(this.themDipMoi.getText());
            ThemQuaThieuNhiController.dip.removeAll("Khác");
            ThemQuaThieuNhiController.dip.add("Khác");
            ((Stage)((Node) keyEvent.getSource()).getScene().getWindow()).close();
        }
    }

    @FXML
    void themNam(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER){
            ThemQuaThieuNhiController.nam.add(this.themNamMoi.getText());
            ThemQuaThieuNhiController.nam.removeAll("Khác");
            ThemQuaThieuNhiController.nam.add("Khác");
            ((Stage)((Node) keyEvent.getSource()).getScene().getWindow()).close();
        }

    }

    @FXML
    void close(ActionEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

}
