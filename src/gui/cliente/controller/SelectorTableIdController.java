package gui.cliente.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import eccezioni.ErrorTableException;
import gui.cliente.utils.FXMLManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectorTableIdController implements Initializable {
    public JFXTextField textTavolo;
    public static Integer idTavolo;
    public JFXButton submit;
    public Pane pannello;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pannello.getStylesheets().add(getClass().getResource("/gui/cliente/style/StyleTavolo.css").toExternalForm());
    }

    private void errorMessage(){
        textTavolo.clear();
        textTavolo.setPromptText("Inserisci solo valori numerici");
    }

    public void getIdTavolo(ActionEvent event) throws IOException {
        submit.setId("submit");
        textTavolo.setId("tavolo");
        try {
            idTavolo = Integer.valueOf(textTavolo.getText());
            FXMLManager.loadFXML(event, "/gui/cliente/resources/Home.fxml");
        }catch (NumberFormatException e ) {
            this.errorMessage();
        }
    }
}
