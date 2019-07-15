package gui;

import com.jfoenix.controls.JFXTextField;
import gui.utils.FXMLManager;
import javafx.event.ActionEvent;

import java.io.IOException;

public class TableIdController {

    public JFXTextField tavolo;
    public static Integer idTavolo;

    public void getIdTavolo(ActionEvent event) throws IOException {
        idTavolo = Integer.valueOf(tavolo.getText());
        FXMLManager.loadFXML(event, "/gui/Home.fxml");
    }
}
