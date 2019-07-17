package gui.cucina.controller;

import com.jfoenix.controls.JFXButton;
import gui.cliente.utils.Clock;
import gui.cucina.thread.FXServicePronto;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.util.Duration;
import prodotti.prodotto_ordinato.ProdottoOrdinato;
import prodotti.prodotto_ordinato.StatoProdottoOrdinato;
import prodotti.prodotto.TipoProdotto;
import serverCentrale.cucina.ServerCentraleInterno;

import javafx.scene.control.Label;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CucinaController extends CaffetteriaController {

    JFXButton startTimer;

    public CucinaController(TipoProdotto tipoProdotto) {
        super(tipoProdotto);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
        refresh(vbox); }

    @Override
    public VBox loadProdottiTemp(){
        VBox vBox = new VBox();
        for(Integer tavolo : this.tavoli){
            this.startTimer = new JFXButton("START TIMER");
            this.startTimer.setId(String.valueOf(tavolo));
            this.startTimer.setOnAction(this::setTimer);

            VBox vBox1 = initVboxProdotti(tavolo);
            AnchorPane tempPane = initPaneTavolo(tavolo, vBox1);
            vBox.getChildren().addAll(tempPane);
        }
        return vBox;
    }

    @Override
    protected AnchorPane initPaneTavolo(int tavolo, VBox vBox){
        AnchorPane pane = super.initPaneTavolo(tavolo,vBox);
        pane.getChildren().add(startTimer);
        startTimer.setLayoutX(711);
        startTimer.setLayoutY(28);
        return pane;
    }


    public void setTimer(ActionEvent event)  {

        JFXButton button = (JFXButton)event.getSource();
        int idTavolo = Integer.parseInt(button.getId());

        for (ProdottoOrdinato prodottoOrdinato : ordini){
            if(prodottoOrdinato.getIdTavolo() == idTavolo){
                serverCentraleInterno.changeStatoProdottoOrdinato(prodottoOrdinato, StatoProdottoOrdinato.LAVORAZIONE);
            }
        }
    }
}
