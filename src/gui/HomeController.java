package gui;

import com.jfoenix.controls.JFXButton;
import gui.utils.Clock;
import gui.utils.FXMLManager;
import gui.utils.RefreshManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public Label time;
    public Label table;
    public JFXButton carrello;
    private static int index;
    private static Integer nTavolo;

    public static int getIndex() {
        return index;
    }

    protected static void setIndex(int index) {
        HomeController.index = index;
    }

    private void setTavolo (int n){
        nTavolo = n;
        table.setText(table.getText() + nTavolo);
    }

    public static int getnTavolo() {
        return nTavolo;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
        setTavolo(12);
        RefreshManager.ordinazioniButton(carrello);
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
        FXMLManager.loadFXML(event, "/gui/ConfermaOrdinazioni.fxml");
    }
}
