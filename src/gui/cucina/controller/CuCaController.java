package gui.cucina.controller;

import com.jfoenix.controls.JFXButton;
import gui.cliente.utils.FXMLManager;
import javafx.event.ActionEvent;

import java.io.IOException;

public class CuCaController {

    public void loadCucinaOrCaffetteria(ActionEvent event) throws IOException {
        JFXButton button = (JFXButton) event.getSource();

        if(button.getId().equals("CUCINA")){
            FXMLManager.loadFXML(event, "/gui/cucina/resources/Cucina.fxml");
        }
        else {
            FXMLManager.loadFXML(event, "/gui/cucina/resources/Caffetteria.fxml");
        }
    }
}
