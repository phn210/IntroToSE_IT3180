package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class thieuNhiController {
    @FXML
    void suaThieuNhi(ActionEvent event){
        try{
            Parent book = FXMLLoader.load(getClass().getResource("/views/ThieuNhi/suaThieunhi.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Thieu Nhi");
            stage.setScene(new Scene(book));
            stage.resizableProperty().setValue(false);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
