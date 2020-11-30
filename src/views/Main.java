package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage primaryStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("login/login.fxml"));
        this.primaryStage.setScene(new Scene(root));
        this.primaryStage.getIcons().add(new Image("/static/img/bieutuong.png"));
        this.primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}


