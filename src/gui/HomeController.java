package gui;

import com.jfoenix.controls.JFXButton;
import gui.utils.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends LoaderProdotti implements Initializable {
    public Label time;
    public Label table;
    public JFXButton carrello;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
        table.setText(table.getText() + TableIdController.idTavolo);
        ManagerOrdinazioni.refreshOrdinazioniButton(carrello);
    }
}
