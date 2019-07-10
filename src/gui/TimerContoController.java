package gui;

import com.jfoenix.controls.JFXButton;
import gui.threads.FXServiceConto;
import gui.utils.Clock;
import gui.utils.FXMLManager;
import gui.utils.ManagerOrdinazioni;
import gui.utils.MasterController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Duration;
import prodotti.ProdottoOrdinato;
import prodotti.StatoProdottoOrdinato;
import prodotti.TipoPortata;
import prodotti.TipoProdotto;
import serverCentrale.ServerCentraleEsterno;
import serverCentrale.ServerCentraleInterno;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TimerContoController extends MasterController implements Initializable {


    public JFXButton conto;
    public Label table;
    public Label time;
    public JFXButton carrello;
    public Label Tempo;
    public Text txtConto;
    private Timeline clock;

    protected ActionEvent actionEvent;

    private List<ProdottoOrdinato> ordini = new ArrayList<>();
    private ServerCentraleInterno serverCentraleInterno =new ServerCentraleInterno();
    private ServerCentraleEsterno serverCentraleEsterno = new ServerCentraleEsterno();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setText(table.getText() + HomeController.getnTavolo());
        refresh();
    }

    private void checkConto(){
        conto.setDisable(true);
        for (ProdottoOrdinato p: ordini) {
            if (p.getStato() != StatoProdottoOrdinato.CONSEGNATO) {
                return;
            }
        }
        conto.setDisable(false);
    }


    public void refresh(){
        this.clock = new Timeline(new KeyFrame(Duration.ZERO, event1 ->{
            Clock.initClock(time);
            ordini = serverCentraleEsterno.getOrdini(HomeController.getnTavolo());
            ManagerOrdinazioni.refreshOrdinazioniButton(carrello);
            conto.setDisable(true);
            checkConto();
            checkStatoProdottoOrdinato();

        }
        ),
                new KeyFrame(Duration.seconds(1)));
        this.clock.setCycleCount(Animation.INDEFINITE);
        this.clock.play();
    }

    private void checkStatoProdottoOrdinato() {

        String temp="";
        ProdottoOrdinato p = ordini.get(1);
        float delta = p.getTempoElaborazioneRimanente(maxTempoPreparazione());
        if(delta>=0)
        {
             temp = "" + (int)(delta / 60) + " Minuti " + (int)(delta % 60) + " Secondi";
        }
        else{ temp = "Tempo Scaduto";}

        Tempo.setLayoutX(504);
        Tempo.setText(temp);

    }

    private int maxTempoPreparazione() {
        int max=0;
        for(ProdottoOrdinato ord : ordini){
            // Prelevo il tempo di preparazione massimo
                if(ord.getProdotto().getTempoPreparazione()>max) {
                    max = ord.getProdotto().getTempoPreparazione();
                }


        }
        return  max*60; // MAX in minuti
    }

    public void loadConto(ActionEvent event) {

        this.actionEvent = event;
        //txtConto.setText(txtConto.getText() + serverCentraleEsterno.getConto(HomeController.getnTavolo()));

        FXServiceConto fxServiceConto = new FXServiceConto(super.server, HomeController.getnTavolo());
        fxServiceConto.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                txtConto.setText(txtConto.getText() + " " + event.getSource().getValue() );
                clock.stop();
                conto.setVisible(false);
            }
        });
        fxServiceConto.start();

    }
}
