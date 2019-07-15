package gui.cucina.controller;

import gui.cliente.utils.FXMLManager;
import javafx.event.ActionEvent;

import java.io.IOException;

public class CuCaController {

    public void loadCucina(ActionEvent event ) throws IOException {

        FXMLManager.loadFXML(event, "/gui/cucina/resources/Cucina.fxml");
    }

    public void loadCaffetteria(ActionEvent event) throws IOException{

        FXMLManager.loadFXML(event, "/gui/cucina/resources/Caffetteria.fxml");
    }
}
