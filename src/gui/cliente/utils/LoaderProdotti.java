package gui.cliente.utils;

import com.jfoenix.controls.JFXButton;
import gui.cliente.thread.FXServiceMenu;
import gui.cliente.controller.VisualizzaProdottiController;
import gui.cliente.general_controller.MasterController;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import prodotti.prodotto.Prodotto;
import prodotti.prodotto.TipoPortata;
import serverCentrale.cliente.ServerCentraleEsterno;

import java.io.IOException;
import java.util.ArrayList;

public class LoaderProdotti extends MasterController {

    protected ServerCentraleEsterno serverCentrale = this.server;
    protected ActionEvent actionEvent;

    public void loadProdotti(ActionEvent event) {
        JFXButton button = (JFXButton) event.getSource();
        this.actionEvent = event;
        FXServiceMenu FXServiceMenu = new FXServiceMenu(serverCentrale,  TipoPortata.valueOf(button.getId()));
        FXServiceMenu.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                VisualizzaProdottiController visualizzaProdottiController = new VisualizzaProdottiController((ArrayList<Prodotto>) event.getSource().getValue());
                try {
                    FXMLManager.loadFXML(actionEvent, "/gui/cliente/resources/VisualizzaProdotti.fxml", visualizzaProdottiController);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        FXServiceMenu.start();
    }

}
