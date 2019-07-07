package gui;

import com.jfoenix.controls.JFXButton;
import gui.utils.Clock;
import gui.utils.FXMLManager;
import gui.utils.ManagerOrdinazioni;
import gui.utils.MasterController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;
import ordinazioni.Ordinazione;
import prodotti.ProdottoOrdinato;
import prodotti.StatoProdottoOrdinato;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TimerContoController extends MasterController implements Initializable {


    public JFXButton conto;
    public Label table;
    public Label time;
    public JFXButton carrello;
    public Label Tempo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setText(table.getText() + HomeController.getnTavolo());
        refresh();
    }

    private void checkConto(){
        CucinaController c = new CucinaController();
       // for (Ordinazione o: CucinaController.getOrdini()) {
           // if(HomeController.getnTavolo() == o.getIdTavolo()){
                for (ProdottoOrdinato p: c.getOrdini()) {
                    if(p.getStato() != StatoProdottoOrdinato.CONSEGNATO){
                        conto.setDisable(true);
                    }else{
                        conto.setDisable(false);
                    }
                }
           // }

        //}
    }

    public void richiediConto(ActionEvent event) {


    }

    public void refresh(){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event1 ->{
            Clock.initClock(time);
            ManagerOrdinazioni.refreshOrdinazioniButton(carrello);
            conto.setDisable(true);
            checkConto();
            checkStatoProdottoOrdinato();

        }
        ),
                new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void checkStatoProdottoOrdinato()
    {
        CucinaController c= new CucinaController();

       // for(Ordinazione ord : c.getOrdini()) {
           // if (HomeController.getnTavolo() == ord.getIdTavolo()) {
                for (ProdottoOrdinato p : c.getOrdini()) {
                    if (p.getStato() == StatoProdottoOrdinato.LAVORAZIONE) {
                        if (p.getTempoElaborazioneRimanente(c.maxTempoPreparazione()) >= 0) {
                            String temp = "" + p.getTempoElaborazioneRimanente(c.maxTempoPreparazione()) / 60 + " Minuti " + p.getTempoElaborazioneRimanente(c.maxTempoPreparazione()) % 60 + " Secondi";
                            Tempo.setLayoutX(504);
                            Tempo.setText(temp);
                        }
                    }
                }
            //}
       // }
    }

}
