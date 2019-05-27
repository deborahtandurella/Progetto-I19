package gui;

import gui.utils.Clock;
import gui.utils.FXMLManager;
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

    public void loadHome(ActionEvent event) throws IOException {
        FXMLManager.loadFXML(event, "/gui/Home.fxml");
    }
}
