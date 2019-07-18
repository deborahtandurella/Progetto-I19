package gui.cucina.controller;

import com.jfoenix.controls.JFXButton;
import gui.cliente.utils.FXMLManager;
import javafx.event.ActionEvent;
import prodotti.prodotto.TipoProdotto;

import java.io.IOException;

public class CuCaController {

    public void loadCucinaOrCaffetteria(ActionEvent event) throws IOException {
        JFXButton button = (JFXButton) event.getSource();

        if(button.getId().equals("CUCINA")){
            CucinaController cucinaController = new CucinaController(TipoProdotto.valueOf(button.getId()));
            FXMLManager.loadFXML(event, "/gui/cucina/resources/Cucina.fxml", cucinaController);
        }
        else {
            CaffetteriaController caffetteriaController = new CaffetteriaController(TipoProdotto.valueOf(button.getId()));
            FXMLManager.loadFXML(event, "/gui/cucina/resources/Caffetteria.fxml", caffetteriaController);
        }
    }
}
