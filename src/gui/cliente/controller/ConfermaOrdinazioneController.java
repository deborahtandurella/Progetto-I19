package gui.cliente.controller;

import com.jfoenix.controls.JFXButton;
import eccezioni.NessunProdottoException;
import gui.cliente.general_controller.GeneralController;
import gui.cliente.thread.FXServiceOrdini;
import gui.cliente.utils.Clock;
import gui.cliente.utils.FXMLManager;
import gui.cliente.utils.ManagerCarrello;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import prodotti.prodotto_ordinato.ProdottoOrdinato;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Classe Controller per ConfermaOrdinazione.fxml per confermare ordine
 */
public class ConfermaOrdinazioneController extends GeneralController {
    public VBox vBoxList;
    public JFXButton conferma;
    private ActionEvent actionEvent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
        ManagerCarrello.refreshOrdinazioniButton(carrello);
        table.setText(table.getText() + SelectorTableIdController.idTavolo);

        this.loadProdottiOrdinati(ManagerCarrello.getProdottiOrdinati(), vBoxList);
        if(ManagerCarrello.getNumeroProdottiOrdinati() == 0){
            conferma.setText(" VISUALIZZA CONTO ");
        }
    }

    /**
     * Lancia thread per settaggio ordinazione nel db
     * @param event
     * @throws IOException
     * @throws NessunProdottoException
     */
    public void confermaOrdinazione(ActionEvent event) throws IOException, NessunProdottoException {
        this.actionEvent = event;
        FXServiceOrdini fxServiceOrdini = new FXServiceOrdini(super.serverCentraleCliente, ManagerCarrello.getProdottiOrdinati());
        ManagerCarrello.clearProdottiOrdinatiFromLocal();
        fxServiceOrdini.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                try {
                    FXMLManager.loadFXML(actionEvent, "/gui/cliente/resources/StatoOrdinazione.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        fxServiceOrdini.start();
    }

    /**
     * Rimuove prodotto tramite id Bottone cliccato
     * @param event
     */
    private void removeProdotto(ActionEvent event) {
        JFXButton removeButton = (JFXButton) event.getSource();
        ManagerCarrello.removeProdottoOrdinato(Integer.parseInt(removeButton.getId()), carrello);

        vBoxList.getChildren().clear();
        this.loadProdottiOrdinati(ManagerCarrello.getProdottiOrdinati(), vBoxList);
    }

    /**
     * Riempe vBox di prodotti
     * @param lista
     * @param vBox
     */
    private void loadProdottiOrdinati(ArrayList<ProdottoOrdinato> lista, VBox vBox){
        for(ProdottoOrdinato p : lista){
            AnchorPane tempPane = initPane(p);
            vBox.getChildren().addAll(tempPane);
        }
    }

    /**
     * Riempe pannello di prodotti ordinati
     * @param prodottoOrdinato
     * @return pannello
     */
    private AnchorPane initPane(ProdottoOrdinato prodottoOrdinato){
        AnchorPane tempPane = new AnchorPane();

        JFXButton remove = new JFXButton("RIMUOVI");
        remove.setLayoutX(514);
        remove.setLayoutY(2);
        remove.setId(String.valueOf(prodottoOrdinato.getProdotto().getId()));
        remove.setOnAction(this::removeProdotto);

        Text titleTemp = new Text(prodottoOrdinato.getProdotto().getNome());
        titleTemp.setId("titletemp");
        titleTemp.setLayoutX(7.0);
        titleTemp.setLayoutY(29.0);

        Text quantTemp = new Text(""+prodottoOrdinato.getQuantita());
        quantTemp.setId("quantTemp");
        quantTemp.setLayoutX(200);
        quantTemp.setLayoutY(29);

        tempPane.getChildren().addAll(titleTemp, quantTemp, remove);
        tempPane.getStylesheets().add(getClass().getResource("/gui/cliente/style/StyleConfermaProdotti.css").toExternalForm());

        return tempPane;
    }
}
