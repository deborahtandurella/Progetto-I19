package gui;

import gui.utils.Clock;
import gui.utils.FXMLManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public Label time;
    private static int index;

    public static int getIndex() {
        return index;
    }

    private void setIndex(int index) {
        HomeController.index = index;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
    }

    public void loadMenuPiatti(ActionEvent event) throws IOException {
        setIndex(0);
        FXMLManager.loadFXML(event, "/gui/VisualizzaProdotti.fxml");
    }

    public void loadDolci(ActionEvent event) throws IOException {
        setIndex(3);
        FXMLManager.loadFXML(event, "/gui/VisualizzaProdotti.fxml");
    }

    public void loadVini(ActionEvent event) throws IOException {
        setIndex(2);
        FXMLManager.loadFXML(event, "/gui/VisualizzaProdotti.fxml");
    }

    public void loadBevande(ActionEvent event) throws IOException {
        setIndex(1);
        FXMLManager.loadFXML(event, "/gui/VisualizzaProdotti.fxml");
    }

    public void loadOrdinazioni(ActionEvent event) throws IOException {
        FXMLManager.loadFXML(event, "/gui/conferma_ordinazione.fxml");
    }
}
