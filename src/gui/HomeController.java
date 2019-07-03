package gui;

import com.jfoenix.controls.JFXButton;
import gui.utils.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends LoaderProdotti implements Initializable {
    public Label time;
    public Label table;
    public JFXButton carrello;
    private static Integer nTavolo;

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
        ManagerOrdinazioni.refreshOrdinazioniButton(carrello);
    }

    public void loadOrdinazioni(ActionEvent event) throws IOException {
        FXMLManager.loadFXML(event, "/gui/ConfermaOrdinazioni.fxml");
    }
}
