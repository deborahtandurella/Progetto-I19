package gui.utils;

import com.jfoenix.controls.JFXButton;
import gui.VisualizzaProdottiController;
import javafx.event.ActionEvent;
import prodotti.TipoPortata;

import java.io.IOException;

public class LoaderProdotti extends MasterController {

    public void loadProdotti(ActionEvent event) throws IOException {
        JFXButton button = (JFXButton) event.getSource();
        VisualizzaProdottiController visualizzaProdottiController = new VisualizzaProdottiController(TipoPortata.valueOf(button.getId()));
        FXMLManager.loadFXML(event, "/gui/VisualizzaProdotti.fxml", visualizzaProdottiController);
    }
}
