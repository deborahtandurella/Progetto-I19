package gui.utils;

import gui.VisualizzaProdottiController;
import javafx.event.ActionEvent;
import prodotti.TipoPortata;

import java.io.IOException;

public class MasterController {

    public void loadOrdinazioni(ActionEvent event) throws IOException {
        FXMLManager.loadFXML(event, "/gui/ConfermaOrdinazioni.fxml");
    }

    public void loadHome(ActionEvent event) throws IOException {
        FXMLManager.loadFXML(event, "/gui/Home.fxml");
    }

    public void loadVisualizzaProdotti(ActionEvent event) throws IOException {
        VisualizzaProdottiController visualizzaProdottiController = new VisualizzaProdottiController(TipoPortata.PIATTI);
        FXMLManager.loadFXML(event, "/gui/VisualizzaProdotti.fxml", visualizzaProdottiController);
    }
}
