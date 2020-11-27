package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import views.Main;

import java.net.URL;

public class MainController {
    @FXML
    private BorderPane mainpane;

    public void handleHogiadinh(ActionEvent event) {
        Pane view = getPage("HoGiaDinh/hogiadinh");
        mainpane.setCenter(view);
    }
    public void handlePhatquaThieunhi(ActionEvent event) {
        Pane view = getPage("PhatQua/phatquaThieunhi");
        mainpane.setCenter(view);
    }
    public void handlePhatquaHocsinh(ActionEvent event) {
        Pane view = getPage("PhatQua/phatquaHocsinh");
        mainpane.setCenter(view);
    }
    public void handleThongke(ActionEvent event) {
        Pane view = getPage("ThongKe/thongke");
        mainpane.setCenter(view);
    }
    public Pane getPage(String fileName){
        Pane view = new Pane();
        try {
            URL fileUrl = Main.class.getResource("/views/"+ fileName + ".fxml");
            if(fileName == null){
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }
            view = new FXMLLoader().load(fileUrl);
        } catch (Exception e) {
            System.out.println("No page" +fileName+ " please check FxmlLoader");
        }
        return view;
    }
}
