package controllers.hocsinh;

import controllers.thieunhi.ThemQuaThieuNhiController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ThemNamHocController {
    @FXML
    private TextField themNamMoi;

    public ThemNamHocController(){

    }

    @FXML
    void themNamMoi(ActionEvent event) {
        ThemQuaHocSinhController.listNam.add(this.themNamMoi.getText());
        ThemQuaHocSinhController.listNam.removeAll("Khác");
        ThemQuaHocSinhController.listNam.add("Khác");
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void themNam(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER){
            ThemQuaHocSinhController.listNam.add(this.themNamMoi.getText());
            ThemQuaHocSinhController.listNam.removeAll("Khác");
            ThemQuaHocSinhController.listNam.add("Khác");
            ((Stage)((Node) keyEvent.getSource()).getScene().getWindow()).close();
        }

    }

    @FXML
    void close(ActionEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }
}
