package controllers.hogiadinh;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.HoGiaDinh;
import services.HoGiaDinhService;

import java.sql.SQLException;

public class SuaHGDController {

    @FXML
    private TextField textIDGiaDinh;

    @FXML
    private TextField textDiaChi;

    @FXML
    private TextField textChuHo;

    @FXML
    private TextField textSDT;

    @FXML
    void update(ActionEvent event) throws SQLException {
        HoGiaDinh hoGiaDinhModel = new HoGiaDinh(Integer.valueOf(textIDGiaDinh.getText()),
                                                textDiaChi.getText(),
                                                textChuHo.getText(),
                                                textSDT.getText());
        HoGiaDinhService hoGiaDinhService = new HoGiaDinhService();
        hoGiaDinhService.editListHoGiaDinh(hoGiaDinhModel);
    }

    public void initializeTextField(HoGiaDinh hoGiaDinh){
        textIDGiaDinh.setText(String.valueOf(hoGiaDinh.getIDGiaDinh()));
        textDiaChi.setText(hoGiaDinh.getDiaChi());
        textChuHo.setText(hoGiaDinh.getChuHo());
        textSDT.setText(hoGiaDinh.getSDT());
        textIDGiaDinh.setEditable(false);
    }

}
