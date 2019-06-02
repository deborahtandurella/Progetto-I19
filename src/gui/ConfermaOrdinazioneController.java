package gui;

import com.jfoenix.controls.JFXButton;
import gui.utils.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import prodotti.ProdottoOrdinato;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ConfermaOrdinazioneController implements Initializable
{

    public Label time;
    public VBox vBoxList;
    public JFXButton carrello;
    public Label table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
        RefreshManager.ordinazioniButton(carrello);
        table.setText(table.getText() + HomeController.getnTavolo());

        ManagerOrdinazioni manager = new ManagerOrdinazioni();

        loadProdottiOrdinati(manager.getProdottiOrdinati(), vBoxList);
    }

    public void loadHome(ActionEvent event) throws IOException {
        FXMLManager.loadFXML(event, "/gui/Home.fxml");
    }

    public void loadVisualizzaProdotti(ActionEvent event) throws IOException {
        FXMLManager.loadFXML(event, "/gui/VisualizzaProdotti.fxml");
    }

    private void loadProdottiOrdinati(ArrayList<ProdottoOrdinato> aPO, VBox vBox){
        for(ProdottoOrdinato p : aPO){
            AnchorPane tempPane = new AnchorPane();
            JFXButton addTemp = new JFXButton("RIMUOVI");
            Text titleTemp = new Text(p.getProdotto().getNome());
            titleTemp.setId("titletemp");

            tempPane.getChildren().addAll(titleTemp, addTemp);
            tempPane.getStylesheets().add(getClass().getResource("/gui/style/StyleConfermaProdotti.css").toExternalForm());


            addTemp.setLayoutX(514);
            addTemp.setLayoutY(2);
            addTemp.setId(Integer.toString(p.getProdotto().getId()));
            addTemp.setOnAction(this::removeProdotto);
            titleTemp.setLayoutX(7.0);
            titleTemp.setLayoutY(29.0);
            vBox.getChildren().addAll(tempPane);
        }
    }

    private void removeProdotto(ActionEvent event) {
        JFXButton o = (JFXButton) event.getSource();
        ManagerOrdinazioni.removeProdottoOrdinato(Integer.parseInt(o.getId()));

        vBoxList.getChildren().clear();

        ManagerOrdinazioni manager = new ManagerOrdinazioni();
        loadProdottiOrdinati(manager.getProdottiOrdinati(), vBoxList);
    }

}
