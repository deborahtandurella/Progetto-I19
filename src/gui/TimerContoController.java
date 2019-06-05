package gui;

import com.jfoenix.controls.JFXButton;
import gui.utils.Clock;
import gui.utils.FXMLManager;
import gui.utils.RefreshManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ordinazioni.Ordinazione;
import prodotti.ProdottoOrdinato;
import prodotti.StatoProdottoOrdinato;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TimerContoController implements Initializable {


    public JFXButton conto;
    public Label table;
    public Label time;
    public JFXButton carrello;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setText(table.getText() + HomeController.getnTavolo());
        Clock.initClock(time);
        RefreshManager.ordinazioniButton(carrello);
        conto.setDisable(true);
        checkConto();

    }

    private void checkConto(){

        for (Ordinazione o: CucinaController.getOrdini()) {
            if(HomeController.getnTavolo() == o.getIdTavolo()){
                for (ProdottoOrdinato p: o.getOrdini()) {
                    if(p.getStato() != StatoProdottoOrdinato.CONSEGNATO){
                        conto.setDisable(true);
                    }else{
                        conto.setDisable(false);
                    }
                }
            }

        }
    }


    public void loadOrdinazioni(ActionEvent event) {
    }

    public void loadHome(ActionEvent event) throws IOException {
        FXMLManager.loadFXML(event, "/gui/Home.fxml");
    }


    public void richiediConto(ActionEvent event) {


    }
}
