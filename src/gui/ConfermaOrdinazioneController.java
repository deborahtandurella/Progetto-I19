package gui;

import com.jfoenix.controls.JFXButton;
import eccezioni.PrezzoNegativoException;
import gui.utils.Clock;
import gui.utils.FXMLManager;
import gui.utils.ListFiller;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import prodotti.Prodotto;
import prodotti.TipoPortata;
import prodotti.TipoProdotto;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ConfermaOrdinazioneController implements Initializable
{

    //public Label time;
    public VBox vBoxList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // Clock.initClock(time);



        ListFiller fillList = new ListFiller();
        ArrayList<Prodotto> lista= new ArrayList<>();

        fillList.vBoxFiller(fillList.getProdotti(),vBoxList);
    }

    public void loadHome(ActionEvent event) throws IOException {
        FXMLManager.loadFXML(event, "/gui/Home.fxml");
    }
}
