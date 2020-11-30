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

public class MainController{
    @FXML
    private BorderPane mainPane;
    private Stage primaryStage;

    public MainController(){
        this.primaryStage = new Stage();
        this.primaryStage.getIcons().add(new Image("/static/img/bieutuong.png"));
    }

    public void handleHogiadinh(ActionEvent event) {
        Pane view = getPage("hogiadinh/hogiadinh");
        mainPane.setCenter(view);
    }
    public void handlePhatqua(ActionEvent event) {
        Pane view = getPage("phatqua/phatqua");
        mainPane.setCenter(view);
    }
    public void handlePhatquaThieunhi(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("phatqua/thieunhi/phatquaThieunhi.fxml"));
        this.primaryStage.close();
        this.primaryStage.setScene(new Scene(root));
        this.primaryStage.show();
    }
    public void handlePhatquaHocsinh(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("phatqua/hocsinh/phatquaHocsinh.fxml"));
        this.primaryStage.close();
        this.primaryStage.setScene(new Scene(root));
        this.primaryStage.show();
    }
    public void handleThongke(ActionEvent event) {
        Pane view = getPage("thongke/thongke");
        mainPane.setCenter(view);
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
