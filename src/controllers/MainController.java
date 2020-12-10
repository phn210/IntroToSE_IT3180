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

    public void handleHoGiaDinh(ActionEvent event) {
        Pane view = getPage("hogiadinh/HoGiaDinh");
        mainPane.setCenter(view);
    }
    public void handlePhatQua(ActionEvent event) {
        Pane view = getPage("phatqua/PhatQua");
        mainPane.setCenter(view);
    }
    public void handleThieuNhi(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("phatqua/thieunhi/ThieuNhi.fxml"));
        this.primaryStage.close();
        this.primaryStage.setScene(new Scene(root));
        this.primaryStage.show();
    }
    public void handleHocSinh(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("phatqua/hocsinh/HocSinh.fxml"));
        this.primaryStage.close();
        this.primaryStage.setScene(new Scene(root));
        this.primaryStage.show();
    }
    public void handleThongKe(ActionEvent event) {
        Pane view = getPage("thongke/ThongKe");
        mainPane.setCenter(view);
    }
    public void handleLogin(ActionEvent event) throws IOException {
        Main.primaryStage.close();
        Parent root = FXMLLoader.load(Main.class.getResource("main_board/Main.fxml"));
        this.primaryStage.setScene(new Scene(root));
        this.primaryStage.setTitle("Quan Ly Phat Qua - Nhom 17");
        this.primaryStage.show();
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
    public void handleCheckInfo(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("main_board/Infomation.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("/static/img/bieutuong.png"));
        stage.show();
    }
    public void handleLogOut(ActionEvent event) {
        System.exit(0);
    }
    public static Pane getPage(String fileName){
        Pane view = new Pane();
        try {
            URL fileUrl = Main.class.getResource("/views/"+ fileName + ".fxml");
            if(fileName == null){
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }
            view = new FXMLLoader().load(fileUrl);
        } catch (Exception e) {
            System.out.println("No page " +fileName+ " please check controllers files");
        }
        return view;
    }

}
