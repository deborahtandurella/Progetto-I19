package gui.cliente.utils;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLManager {
    public static void loadFXML(javafx.event.ActionEvent event, String path, Initializable controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(FXMLManager.class.getResource(path));
        loader.setController(controller);
        finishLoading(event, loader.load());
    }

    public static void loadFXML(javafx.event.ActionEvent event, String path) throws IOException {
        Parent root = FXMLLoader.load(FXMLManager.class.getResource(path));
        finishLoading(event, root);
    }

    private static void finishLoading(javafx.event.ActionEvent event, Parent root){
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }
}
