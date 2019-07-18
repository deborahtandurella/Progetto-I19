package gui.cliente.controller;

import gui.cliente.utils.Clock;
import gui.cliente.utils.ListFiller;
import gui.cliente.general_controller.LoaderProdottiController;
import gui.cliente.utils.ManagerCarrello;
import javafx.scene.layout.VBox;
import prodotti.prodotto.Prodotto;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VisualizzaProdottiController extends LoaderProdottiController {
    public VBox vBoxList;
    private ArrayList<Prodotto> menu;

    public VisualizzaProdottiController(ArrayList<Prodotto> menu) { this.menu = menu; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
        ManagerCarrello.refreshOrdinazioniButton(carrello);
        table.setText(table.getText() + SelectorTableIdController.idTavolo);
        new ListFiller(this, this.vBoxList, this.menu);
    }
}
