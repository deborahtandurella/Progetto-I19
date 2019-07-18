package gui.cliente.controller;

import com.jfoenix.controls.JFXButton;
import gui.cliente.thread.FXServiceConto;
import gui.cliente.utils.Clock;
import gui.cliente.utils.ManagerCarrello;
import gui.cliente.general_controller.GeneralController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import prodotti.prodotto_ordinato.ProdottoOrdinato;
import prodotti.prodotto_ordinato.StatoProdottoOrdinato;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StatoOrdinazioneController extends GeneralController {

    public JFXButton conto;
    public JFXButton home;
    public JFXButton prodotti;
    public Label Tempo;
    public Text txtConto;
    public Text txtFinale;
    public VBox vboxProdotti;
    private Timeline clock;
    private List<ProdottoOrdinato> ordini = new ArrayList<>();
    protected ActionEvent actionEvent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
        table.setText(table.getText() + SelectorTableIdController.idTavolo);
        ManagerCarrello.refreshOrdinazioniButton(carrello);
        refresh();
    }

    private void checkConto(){
        conto.setDisable(true);
        if(ordini.isEmpty()){
            return;
        }
        for (ProdottoOrdinato p: ordini) {
            if (p.getStato() != StatoProdottoOrdinato.CONSEGNATO) {
                return;
            }
        }
        conto.setDisable(false);
    }

    private void reloadVboxProdotti(){
        this.vboxProdotti.getChildren().clear();
        for(ProdottoOrdinato p : serverCentraleCliente.getOrdini(SelectorTableIdController.idTavolo)){
            Text prodotto = new Text(p.getQuantita() + "x "+ p.getProdotto().getNome() + " (" + p.getStato() + ")");
            prodotto.setLayoutY(30);
            prodotto.setLayoutX(15);
            AnchorPane pane = new AnchorPane(prodotto);
            this.vboxProdotti.getChildren().add(pane);
        }
    }

    private void refresh(){
        this.clock = new Timeline(new KeyFrame(Duration.ZERO, event1 ->{
            this.ordini = serverCentraleCliente.getOrdini(SelectorTableIdController.idTavolo);
            this.reloadVboxProdotti();
            this.checkConto();
            this.printTimer();
        }),
                new KeyFrame(Duration.seconds(1)));
        this.clock.setCycleCount(Animation.INDEFINITE);
        this.clock.play();
    }

    private void printTimer() {
        String temp="";
        float delta;
        for(ProdottoOrdinato p : this.ordini) {
            if(p.getStato()==StatoProdottoOrdinato.LAVORAZIONE) {
                delta= p.getTempoElaborazioneRimanente(maxTempoPreparazione());
                if (delta >= 0) {
                    temp = "" + (int) (delta / 60) + " Minuti " + (int) (delta % 60) + " Secondi";
                } else {
                    temp = "Il tuo ordine arriverà a breve";
                }
                break;
            }
        }
        Tempo.setLayoutX(360);
        Tempo.setText(temp);
    }

    private int maxTempoPreparazione() {
        int max=0;
        for(ProdottoOrdinato ord : this.ordini){
                if(ord.getProdotto().getTempoPreparazione()>max) {
                    max = ord.getProdotto().getTempoPreparazione();
                }
        }
        return  max*60;
    }

    public void loadConto(ActionEvent event) {
        this.actionEvent = event;
        FXServiceConto fxServiceConto = new FXServiceConto(super.serverCentraleCliente, SelectorTableIdController.idTavolo);
        fxServiceConto.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                txtConto.setText(txtConto.getText() + " " + event.getSource().getValue() );
                clock.stop();
                conto.setVisible(false);
                carrello.setDisable(true);
                home.setDisable(true);
                prodotti.setDisable(true);
                txtFinale.setText("Ti aspettiamo in cassa");
            }
        });
        fxServiceConto.start();
    }
}
