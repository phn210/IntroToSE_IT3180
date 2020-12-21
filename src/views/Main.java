package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import services.DBConnection;

public class Main extends Application {

    public static Stage primaryStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login/Login.fxml"));
        this.primaryStage.setTitle("Quan Ly Phat Qua - Nhom 17");
        this.primaryStage.setScene(new Scene(root));
        this.primaryStage.getIcons().add(new Image("/static/img/bieutuong.png"));
        this.primaryStage.resizableProperty().setValue(false);
        this.primaryStage.show();
        }

    public static void main(String[] args) {
        launch(args);
    }
    
}


