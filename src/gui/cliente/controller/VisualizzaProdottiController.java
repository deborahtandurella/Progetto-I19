package gui.cliente.controller;

import com.jfoenix.controls.JFXButton;
import gui.cliente.utils.Clock;
import gui.cliente.utils.ListFiller;
import gui.cliente.utils.LoaderProdotti;
import gui.cliente.utils.ManagerOrdinazioni;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import prodotti.prodotto.Prodotto;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VisualizzaProdottiController extends LoaderProdotti implements Initializable{

    public Label time;
    public VBox vBoxList;
    public JFXButton carrello;
    public Label table;

    private ArrayList<Prodotto> menu;

    public VisualizzaProdottiController(ArrayList<Prodotto> menu) {

        this.menu = menu;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
        ManagerOrdinazioni.refreshOrdinazioniButton(carrello);
        table.setText(table.getText() + TableIdController.idTavolo);
        new ListFiller(this, this.vBoxList, this.menu);
    }
}
