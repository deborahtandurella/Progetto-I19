package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import gui.utils.Clock;
import gui.utils.FXMLManager;
import gui.utils.RefreshManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import ordinazioni.Ordinazione;
import prodotti.ProdottoOrdinato;
import prodotti.StatoProdottoOrdinato;
import prodotti.TipoPortata;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TimerContoController implements Initializable {


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


    public void loadOrdinazioni(ActionEvent event) throws IOException {
        FXMLManager.loadFXML(event, "/gui/ConfermaOrdinazioni.fxml");
    }

    public void loadVisualizzaProdotti(ActionEvent event) throws IOException {
        FXMLManager.loadFXML(event, "/gui/VisualizzaProdotti.fxml");
    }


    public void loadHome(ActionEvent event) throws IOException {
        FXMLManager.loadFXML(event, "/gui/Home.fxml");
    }


    public void richiediConto(ActionEvent event) {


    }

    public void refresh(){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event1 ->{
            Clock.initClock(time);
            RefreshManager.ordinazioniButton(carrello);
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
        
        for(Ordinazione ord : c.getOrdini()){
            if (HomeController.getnTavolo() == ord.getIdTavolo())
            {
                for(ProdottoOrdinato p : ord.getOrdini()){
                    if (p.getStato()==StatoProdottoOrdinato.LAVORAZIONE)
                    {
                        if(p.getTempoElaborazioneRimanente(c.maxTempoPreparazione())>=0) {
                            String temp = ""+p.getTempoElaborazioneRimanente(c.maxTempoPreparazione())/60+" Minuti "+ p.getTempoElaborazioneRimanente(c.maxTempoPreparazione())%60+" Secondi";
                            Tempo.setLayoutX(504);
                            Tempo.setText(temp);
                        }
                    }
                }

            }
        }
    }

}
