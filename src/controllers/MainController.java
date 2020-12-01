package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import views.Main;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
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
    public void showBackGR(MouseEvent mouseEvent) {
        Pane view = getPage("main_board/back_gr");
        mainPane.setCenter(view);
    }
    public void handleLinkToFB(MouseEvent mouseEvent) {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI("https://www.facebook.com/lkp.tda.cs");
            desktop.browse(oURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleLinkToGit(MouseEvent mouseEvent) {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI("https://github.com/phn210/IntroToSE_IT3180");
            desktop.browse(oURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleLinkToGG(MouseEvent mouseEvent) {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI("http://www.google.com");
            desktop.browse(oURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void hanldeCheckInfo(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("info/info.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("/static/img/bieutuong.png"));
        stage.show();
    }
    public void handleLogOut(ActionEvent event) {
        System.exit(0);
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
            System.out.println("No page " +fileName+ " please check FxmlLoader");
        }
        return view;
    }

}
