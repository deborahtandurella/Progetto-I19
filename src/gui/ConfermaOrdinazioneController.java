package gui;

import com.jfoenix.controls.JFXButton;
import eccezioni.NessunProdottoException;
import gui.utils.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import prodotti.ProdottoOrdinato;
import serverCentrale.ServerCentraleEsterno;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
        ManagerOrdinazioni.refreshOrdinazioniButton(carrello);
        table.setText(table.getText() + HomeController.getnTavolo());

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

            tempPane.getChildren().addAll(titleTemp, remove);
            tempPane.getStylesheets().add(getClass().getResource("/gui/style/StyleConfermaProdotti.css").toExternalForm());

            remove.setLayoutX(514);
            remove.setLayoutY(2);
            remove.setId(Integer.toString(p.getProdotto().getId()));
            remove.setOnAction(this::removeProdotto);
            titleTemp.setLayoutX(7.0);
            titleTemp.setLayoutY(29.0);
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
        ServerCentraleEsterno serverCentraleEsterno = new ServerCentraleEsterno();
        //CucinaController c=new CucinaController();
        serverCentraleEsterno.inviaOrdine(ManagerOrdinazioni.getProdottiOrdinati());
        //c.setOrdini();
        ManagerOrdinazioni.clearProdottiOrdinatiFromLocal();

        FXMLManager.loadFXML(event, "/gui/TimerContoFinale.fxml");
    }
}
