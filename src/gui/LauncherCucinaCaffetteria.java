package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LauncherCucinaCaffetteria extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SelectCuCa.fxml"));
        primaryStage.setTitle("Cucina-Caffetteria");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
