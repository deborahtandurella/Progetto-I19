package gui.cliente.controller;

import com.jfoenix.controls.JFXTextField;
import gui.cliente.utils.FXMLManager;
import javafx.event.ActionEvent;

import java.io.IOException;

public class TableIdController {

    public JFXTextField tavolo;
    public static Integer idTavolo;

    public void getIdTavolo(ActionEvent event) throws IOException {
        idTavolo = Integer.valueOf(tavolo.getText());
        FXMLManager.loadFXML(event, "/gui/cliente/resources/Home.fxml");
    }
}
