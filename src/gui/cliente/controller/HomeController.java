package gui.cliente.controller;

import gui.cliente.utils.Clock;
import gui.cliente.utils.LoaderProdotti;
import gui.cliente.utils.ManagerOrdinazioni;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends LoaderProdotti implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
        table.setText(table.getText() + TableIdController.idTavolo);
        ManagerOrdinazioni.refreshOrdinazioniButton(carrello);
    }
}
