package gui.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLManager {

    public static void loadFXML(javafx.event.ActionEvent event, String path) throws IOException {
        Parent root = FXMLLoader.load(FXMLManager.class.getResource(path));
        Scene scene = new Scene(root);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
