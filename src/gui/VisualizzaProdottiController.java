package gui;

import com.jfoenix.controls.JFXButton;
import gui.utils.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import prodotti.TipoPortata;
import java.net.URL;
import java.util.ResourceBundle;

public class VisualizzaProdottiController extends LoaderProdotti implements Initializable{

    public Label time;
    public VBox vBoxList;
    public JFXButton carrello;
    public Label table;

    private TipoPortata tipoPortata;

    public VisualizzaProdottiController(TipoPortata tipoPortata) {
       this.tipoPortata = tipoPortata;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
        ManagerOrdinazioni.refreshOrdinazioniButton(carrello);
        table.setText(table.getText() + HomeController.getnTavolo());
        new ListFiller(this, this.vBoxList, this.tipoPortata);
    }
}
