package gui.cliente.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import eccezioni.TavoloIdException;
import gui.cliente.thread.FXServiceIDTavolo;
import gui.cliente.utils.FXMLManager;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import prodotti.prodotto.Prodotto;
import serverCentrale.ServerCentraleStaff;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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


    private void errorText(TavoloIdException e) {
        Text errore = new Text();
        errore.setId("errore");
        errore.setFill(Paint.valueOf("#d50000"));
        errore.setText(e.getMessage());
        errore.setLayoutY(150);
        errore.setLayoutX(90);
        pannello.getChildren().add(errore);
    }

    public void getIdTavolo(ActionEvent actionEvent) {

        try {
            idTavolo = Integer.valueOf(textTavolo.getText());
            FXServiceIDTavolo fxServiceIDTavolo = new FXServiceIDTavolo(new ServerCentraleStaff());
            fxServiceIDTavolo.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    try {

                        List<Integer> idTavoli_list = (List<Integer>) event.getSource().getValue();
                        if(idTavoli_list.contains(Integer.valueOf(idTavolo))) {
                            throw new TavoloIdException();
                        }
                        FXMLManager.loadFXML(actionEvent, "/gui/cliente/resources/Home.fxml");

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (TavoloIdException e) {
                        errorText(e);
                    }
                }
            });
            fxServiceIDTavolo.start();
        }catch (NumberFormatException e ) {
            this.errorMessage();
        }
    }
}
