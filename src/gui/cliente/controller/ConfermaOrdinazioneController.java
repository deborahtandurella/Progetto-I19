package gui.cliente.controller;

import com.jfoenix.controls.JFXButton;
import eccezioni.NessunProdottoException;
import gui.cliente.general_controller.MasterController;
import gui.cliente.thread.FXServiceOrdini;
import gui.cliente.utils.Clock;
import gui.cliente.utils.FXMLManager;
import gui.cliente.utils.ManagerOrdinazioni;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import prodotti.prodotto_ordinato.ProdottoOrdinato;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ConfermaOrdinazioneController extends MasterController implements Initializable {

    public Label time;
    public VBox vBoxList;
    public JFXButton carrello;
    public Label table;
    public JFXButton conferma;
    private ActionEvent actionEvent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
        ManagerOrdinazioni.refreshOrdinazioniButton(carrello);
        table.setText(table.getText() + TableIdController.idTavolo);

        this.loadProdottiOrdinati(ManagerOrdinazioni.getProdottiOrdinati(), vBoxList);

        if(ManagerOrdinazioni.getNumeroProdottiOrdinati() == 0){
            conferma.setText(" VISUALIZZA CONTO ");
        }
    }

    private void loadProdottiOrdinati(ArrayList<ProdottoOrdinato> aPO, VBox vBox){
        for(ProdottoOrdinato p : aPO){
            AnchorPane tempPane = new AnchorPane();
            JFXButton remove = new JFXButton("RIMUOVI");
            Text titleTemp = new Text(p.getProdotto().getNome());
            titleTemp.setId("titletemp");
            Text quantTemp = new Text(""+p.getQuantita());
            quantTemp.setId("quantTemp");

            tempPane.getChildren().addAll(titleTemp, quantTemp, remove);
            tempPane.getStylesheets().add(getClass().getResource("/gui/cliente/style/StyleConfermaProdotti.css").toExternalForm());

            remove.setLayoutX(514);
            remove.setLayoutY(2);
            remove.setId(Integer.toString(p.getProdotto().getId()));
            remove.setOnAction(this::removeProdotto);
            titleTemp.setLayoutX(7.0);
            titleTemp.setLayoutY(29.0);
            quantTemp.setLayoutX(200);
            quantTemp.setLayoutY(29);
            vBox.getChildren().addAll(tempPane);
        }
    }

    private void removeProdotto(ActionEvent event) {
        JFXButton removeButton = (JFXButton) event.getSource();
        ManagerOrdinazioni.removeProdottoOrdinato(Integer.parseInt(removeButton.getId()), carrello);

        vBoxList.getChildren().clear();
        this.loadProdottiOrdinati(ManagerOrdinazioni.getProdottiOrdinati(), vBoxList);
}

    public void confermaOrdinazione(ActionEvent event) throws IOException, NessunProdottoException {
        this.actionEvent = event;
        FXServiceOrdini fxServiceOrdini = new FXServiceOrdini(super.server, ManagerOrdinazioni.getProdottiOrdinati());
        ManagerOrdinazioni.clearProdottiOrdinatiFromLocal();
        fxServiceOrdini.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                try {
                    FXMLManager.loadFXML(actionEvent, "/gui/cliente/resources/TimerContoFinale.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        fxServiceOrdini.start();
    }
}
