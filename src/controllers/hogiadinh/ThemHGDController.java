package controllers.hogiadinh;

import controllers.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import models.HoGiaDinh;
import services.HoGiaDinhService;
import controllers.hogiadinh.HoGiaDinhController;
import views.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ThemHGDController {

    @FXML
    public TableView<HoGiaDinh> hoGiaDinhTable;
    @FXML
    private TextField textIDGiaDinh;
    @FXML
    private TextField textDiaChi;
    @FXML
    private TextField textChuHo;
    @FXML
    private TextField textSDT;
    @FXML
    public void addHoGiaDinh(ActionEvent event) throws SQLException, IOException {
        //them moi ho gia dinh
        HoGiaDinh hoGiaDinhMoi = new HoGiaDinh(Integer.valueOf(textIDGiaDinh.getText()),
                                                textDiaChi.getText(),
                                                textChuHo.getText(),
                                                textSDT.getText());
        HoGiaDinhService hoGiaDinhService = new HoGiaDinhService();
        hoGiaDinhService.addListHoGiaDinh(hoGiaDinhMoi);

        //update bang ho gia dinh
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("hogiadinh/HoGiaDinh.fxml"));
        Parent parent = loader.load();

        HoGiaDinhController hoGiaDinhController = (HoGiaDinhController) loader.getController();
        hoGiaDinhController.updateTable();
    }

    @FXML
    public void deleteText(ActionEvent event) {
        textIDGiaDinh.clear();
        textDiaChi.clear();
        textChuHo.clear();
        textSDT.clear();
    }

}