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

public class CucinaController extends AbstractGUIInternoController{

    public JFXButton startTimer;

    public CucinaController(TipoProdotto tipoProdotto) {
        super(tipoProdotto);
    }

    @Override
    public VBox loadProdottiTemp(){
        VBox vBox = new VBox();
        for(Integer tavolo : this.tavoli){
            this.startTimer = new JFXButton("START TIMER");
            this.startTimer.setId(String.valueOf(tavolo));
            this.startTimer.setOnAction(this::setTimer);
            startTimer.setLayoutX(711);
            startTimer.setLayoutY(28);

            VBox vBox1 = initVboxProdotti(tavolo);
            AnchorPane tempPane = initPaneTavolo(tavolo, vBox1);
            tempPane.getChildren().add(startTimer);

            vBox.getChildren().addAll(tempPane);
        }
        return vBox;
    }
}
