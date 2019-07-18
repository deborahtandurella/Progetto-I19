package gui.cliente.general_controller;

import com.jfoenix.controls.JFXButton;
import gui.cliente.utils.FXMLManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import serverCentrale.ServerCentraleCliente;
import javafx.scene.control.Label;
import java.io.IOException;

public abstract class GeneralController implements Initializable {
    public JFXButton carrello;
    public Label time;
    public Label table;
    protected ServerCentraleCliente serverCentraleCliente = new ServerCentraleCliente();

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
