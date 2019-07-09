package gui.utils;

import com.jfoenix.controls.JFXButton;
import gui.Threads.ThreadA;
import gui.VisualizzaProdottiController;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import prodotti.Prodotto;
import prodotti.TipoPortata;
import serverCentrale.ServerCentraleEsterno;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoaderProdotti extends MasterController {

    ServerCentraleEsterno serverCentrale = this.server;
    protected ActionEvent actionEvent;

    public void loadProdotti(ActionEvent event) throws IOException {
        JFXButton button = (JFXButton) event.getSource();
        this.actionEvent = event;
        ThreadA threadA = new ThreadA(serverCentrale,  TipoPortata.valueOf(button.getId()));
        threadA.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                VisualizzaProdottiController visualizzaProdottiController = new VisualizzaProdottiController((ArrayList<Prodotto>) event.getSource().getValue());
                try {
                    FXMLManager.loadFXML(actionEvent, "/gui/VisualizzaProdotti.fxml", visualizzaProdottiController);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        threadA.start();
    }

}
