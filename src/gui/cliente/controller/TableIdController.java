package gui.cliente.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import gui.cliente.utils.FXMLManager;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TableIdController {

    public JFXTextField textTavolo;
    public static Integer idTavolo;
    public Pane pannello;
    public JFXButton submit;

    public void getIdTavolo(ActionEvent event) throws IOException {
        submit.setId("submit");
        //submit.getStylesheets().add("/gui/cliente/style/StyleTavolo.css");
        submit.getStylesheets().add(getClass().getResource("/gui/cliente/style/StyleTavolo.css").toExternalForm());
        idTavolo = Integer.valueOf(textTavolo.getText());
        textTavolo.setId("tavolo");
        FXMLManager.loadFXML(event, "/gui/cliente/resources/Home.fxml");
    }
}
