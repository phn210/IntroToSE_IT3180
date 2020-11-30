package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import views.Main;

import java.io.IOException;
import java.net.URL;

public class MainController {
    @FXML
    private BorderPane mainpane;
    private Stage primaryStage;

    public MainController(){
        this.primaryStage = new Stage();
        this.primaryStage.getIcons().add(new Image("/static/img/bieutuong.png"));
    }

    public void handleHogiadinh(ActionEvent event) {
        Pane view = getPage("hogiadinh/hogiadinh");
        mainpane.setCenter(view);
    }
    public void handlePhatquaThieunhi(ActionEvent event) {
        Pane view = getPage("phatqua/phatquaThieunhi");
        mainpane.setCenter(view);
    }
    public void handlePhatquaHocsinh(ActionEvent event) {
        Pane view = getPage("phatqua/phatquaHocsinh");
        mainpane.setCenter(view);
    }
    public void handleThongke(ActionEvent event) {
        Pane view = getPage("thongke/thongke");
        mainpane.setCenter(view);
    }
    public void handleLogin(ActionEvent event) throws IOException {
        Main.primaryStage.close();
        Parent root = FXMLLoader.load(Main.class.getResource("main_board/main.fxml"));
        this.primaryStage.setScene(new Scene(root));
        this.primaryStage.show();
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
