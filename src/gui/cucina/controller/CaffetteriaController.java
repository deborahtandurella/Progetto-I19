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

public class CaffetteriaController implements Initializable {

    protected List<ProdottoOrdinato> ordini = new ArrayList<>();
    protected ServerCentraleInterno serverCentraleInterno = new ServerCentraleInterno();
    protected List<Integer> tavoli = new ArrayList<>();
    public VBox vbox;
    public final int REFRESH_RATE = 1;
    public Label time;
    protected ProdottoOrdinato p= new ProdottoOrdinato();
    protected int indiceBottone;

    protected TipoProdotto tipoProdotto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
        refresh(vbox);
    }

    public CaffetteriaController(TipoProdotto tipoProdotto) {
        this.tipoProdotto = tipoProdotto;
    }

    public VBox loadProdottiOrdinati(){
        this.getTavoliAperti();
        this.ordini = serverCentraleInterno.getOrdini(tipoProdotto, StatoProdottoOrdinato.ORDINATO);
        this.ordini.addAll(serverCentraleInterno.getOrdini(tipoProdotto, StatoProdottoOrdinato.LAVORAZIONE));
        return loadProdottiTemp();
    }

    public VBox loadProdottiTemp(){
        this.indiceBottone = 0;
        VBox vBox = new VBox();
        for(Integer tavolo : this.tavoli){
            VBox vBox1 = initVboxProdotti(tavolo, this.indiceBottone);
            AnchorPane tempPane = initPaneTavolo(tavolo, vBox1);
            vBox.getChildren().addAll(tempPane);
        }
        return vBox;
    }

    public void refresh(VBox vbox){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event1 ->{
            this.vbox.getChildren().clear();
            vbox.getChildren().add(this.loadProdottiOrdinati());
        }),
                new KeyFrame(Duration.seconds(REFRESH_RATE)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void getTavoliAperti(){
        this.tavoli.clear();
        this.tavoli = serverCentraleInterno.getTavoli(StatoProdottoOrdinato.ORDINATO, tipoProdotto);
        for(Integer tavolo : serverCentraleInterno.getTavoli(StatoProdottoOrdinato.LAVORAZIONE)) {
            if (!this.tavoli.contains(tavolo)) {
                this.tavoli.add(tavolo);
            }
        }
    }

public void setPronto(ActionEvent event)  {
    JFXButton o = (JFXButton) event.getSource();
    boolean check=false;
    int  index = 0;
    for(ProdottoOrdinato ord : ordini){
        if (o.getId().equals(Integer.toString(index))) {
            p = ord;
            check=true;
            break;
        }
        index++;
    }
    if(check) {
        FXServicePronto fxServicePronto;
        fxServicePronto = new FXServicePronto(serverCentraleInterno, p, StatoProdottoOrdinato.CONSEGNATO);
        fxServicePronto.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                ordini.remove(p);
                vbox.getChildren().clear();
                vbox.getChildren().add(loadProdottiTemp());
            }
        });
        fxServicePronto.start();
    }
}

    protected VBox initVboxProdotti(int tavolo, int indiceBottone){
        VBox vBox1 = new VBox();
        vBox1.setPrefHeight(217);
        vBox1.setPrefWidth(668);
        vBox1.setLayoutX(14);
        vBox1.setLayoutY(30);
        for(ProdottoOrdinato p : this.ordini){
            if(tavolo == p.getIdTavolo()){
                Text prodotto = new Text(p.getQuantita() + "x "+ p.getProdotto().getNome() + " (" + p.getStato() + ")");
                JFXButton pronto = new JFXButton("PRONTO");
                pronto.setId(Integer.toString(indiceBottone));
                indiceBottone++;
                pronto.setOnAction(this::setPronto);
                pronto.setLayoutX(532);
                pronto.setPrefHeight(39);
                pronto.setPrefWidth(135);
                prodotto.setLayoutY(27);
                AnchorPane pane = new AnchorPane(prodotto, pronto);
                pane.setId("secondAnchor");
                vBox1.getChildren().add(pane);
            }
        }
        return vBox1;
    }

    protected AnchorPane initPaneTavolo(int tavolo, VBox vBox){
        Text table = new Text("TAVOLO N. " + tavolo);
        table.setId("tableText");
        table.setLayoutX(7.0);
        table.setLayoutY(22.0);
        table.setStrokeType(StrokeType.OUTSIDE);
        AnchorPane tempPane = new AnchorPane();
        tempPane.getChildren().addAll(table, vBox);
        tempPane.setId("mainAnchor");
        tempPane.setPrefHeight(115);
        tempPane.setPrefWidth(959);
        tempPane.getStylesheets().add(getClass().getResource("/gui/cucina/style/StyleCucina.css").toExternalForm());
        return tempPane;
    }
}
