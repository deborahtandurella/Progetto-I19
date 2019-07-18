package gui.cliente.controller;

import gui.cliente.utils.Clock;
import gui.cliente.general_controller.LoaderProdottiController;
import gui.cliente.utils.ManagerCarrello;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller per Home.fxml
 */
public class HomeController extends LoaderProdottiController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
        table.setText(table.getText() + SelectorTableIdController.idTavolo);
        ManagerCarrello.refreshOrdinazioniButton(carrello);
    }
}
