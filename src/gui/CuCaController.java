package gui;

import gui.utils.FXMLManager;
import javafx.event.ActionEvent;

import java.io.IOException;

public class CuCaController {

    public void loadCucina(ActionEvent event ) throws IOException {

        FXMLManager.loadFXML(event, "/gui/Cucina.fxml");
    }

    public void loadCaffetteria(ActionEvent event) throws IOException{

        FXMLManager.loadFXML(event, "/gui/Caffetteria.fxml");
    }
}
