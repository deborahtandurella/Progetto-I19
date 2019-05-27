package gui;

import gui.utils.Clock;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VisualizzaProdottiController implements Initializable {

    public Label time;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
    }

    //Sarebbe preferibile creare una classe FXMLManager
    protected void loadFXML(ActionEvent event, String path) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(root);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void loadHome(ActionEvent event) throws IOException {
        loadFXML(event, "Home.fxml");
    }
}
