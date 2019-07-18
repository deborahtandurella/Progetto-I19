package gui.cliente.general_controller;

import com.jfoenix.controls.JFXButton;
import gui.cliente.utils.FXMLManager;
import javafx.event.ActionEvent;
import serverCentrale.cliente.ServerCentraleEsterno;
import javafx.scene.control.Label;
import java.io.IOException;

public class MasterController {
    public JFXButton carrello;
    public Label time;
    public Label table;
    protected ServerCentraleEsterno server  = new ServerCentraleEsterno();

    public void loadOrdinazioni(ActionEvent event) throws IOException {
        FXMLManager.loadFXML(event, "/gui/cliente/resources/ConfermaOrdinazioni.fxml");
    }

    public void loadHome(ActionEvent event) throws IOException {
        FXMLManager.loadFXML(event, "/gui/cliente/resources/Home.fxml");
    }

    public void loadVisualizzaProdotti(ActionEvent event) throws IOException {
        this.loadHome(event);
    }
}
