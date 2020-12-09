package controllers.hogiadinh;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.HoGiaDinh;
import services.HoGiaDinhService;
import controllers.hogiadinh.HoGiaDinhController;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ThemHGDController {

    @FXML
    private TextField textIDGiaDinh;

    @FXML
    private TextField textDiaChi;

    @FXML
    private TextField textChuHo;

    @FXML
    private TextField textSDT;

    @FXML
    public void add(ActionEvent event) throws SQLException {
        //them moi ho gia dinh
        HoGiaDinh hoGiaDinhMoi = new HoGiaDinh(Integer.valueOf(textIDGiaDinh.getText()),
                                                textDiaChi.getText(),
                                                textChuHo.getText(),
                                                textSDT.getText());
        HoGiaDinhService hoGiaDinhService = new HoGiaDinhService();
        hoGiaDinhService.addListHoGiaDinh(hoGiaDinhMoi);
    }

    @FXML
    public void close(ActionEvent event) {
        HoGiaDinhController.themHGDStage.close();
    }

    @FXML
    public void deleteText(ActionEvent event) {
        textIDGiaDinh.clear();
        textDiaChi.clear();
        textChuHo.clear();
        textSDT.clear();
    }

}
