package gui.cucina.controller;

import com.jfoenix.controls.JFXButton;
import gui.cliente.utils.Clock;
import gui.cucina.thread.FXServiceStaffButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.util.Duration;
import prodotti.prodotto.TipoProdotto;
import prodotti.prodotto_ordinato.ProdottoOrdinato;
import prodotti.prodotto_ordinato.StatoProdottoOrdinato;
import serverCentrale.ServerCentraleStaff;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Classe padre per gestione cucina e caffetteria
 */
public abstract class AbstractGUIStaffController implements Initializable {

    protected List<ProdottoOrdinato> ordini = new ArrayList<>();
    protected ServerCentraleStaff serverCentraleStaff = new ServerCentraleStaff();
    protected List<Integer> tavoli = new ArrayList<>();
    public VBox vbox;
    private final int REFRESH_RATE = 2;
    public Label time;
    protected ProdottoOrdinato p = new ProdottoOrdinato();
    protected TipoProdotto tipoProdotto;
    public JFXButton startTimer;
    protected List<Integer> tavoliInLavorazione;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
        refresh(vbox);
    }

    public AbstractGUIStaffController(TipoProdotto tipoProdotto) {

        this.tipoProdotto = tipoProdotto;
        this.tavoliInLavorazione = new ArrayList<>();
    }

    public abstract VBox loadProdottiTemp();

    public VBox loadProdottiOrdinati(){
        this.getTavoliAperti();
        this.ordini = serverCentraleStaff.getOrdini(tipoProdotto, StatoProdottoOrdinato.ORDINATO);
        this.ordini.addAll(serverCentraleStaff.getOrdini(tipoProdotto, StatoProdottoOrdinato.LAVORAZIONE));
        return loadProdottiTemp();
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

    /**
     * Permette l'eliminazione del prodotto tramite bottone
     * @param event
     */
    public void setPronto(ActionEvent event)  {
        JFXButton button = (JFXButton) event.getSource();
        eliminaProdottoPronto(button);
    }

    /**
     * Lancia thread per settare lo stato dei prodotti in lavorazione, inoltre permette lo start del timer per il cliente
     * @param event
     */
    public void setTimer(ActionEvent event)  {
        JFXButton button = (JFXButton)event.getSource();
        int idTavolo = Integer.parseInt(button.getId());
        for (ProdottoOrdinato prodottoOrdinato : ordini){
            if(prodottoOrdinato.getIdTavolo() == idTavolo){
                FXServiceStaffButton fxServiceStaffButton = new FXServiceStaffButton(serverCentraleStaff, prodottoOrdinato, StatoProdottoOrdinato.LAVORAZIONE);
                fxServiceStaffButton.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent event) {
                        startTimer.setDisable(true);
                    }
                });
                fxServiceStaffButton.start();
            }
        }
    }

    /**
     * Ottengo tavoli in stato lavorazione e stato ordinato
     */
    private void getTavoliAperti(){
        this.tavoli.clear();
        this.tavoli = serverCentraleStaff.getTavoli(StatoProdottoOrdinato.ORDINATO, tipoProdotto);
        for(Integer tavolo : serverCentraleStaff.getTavoli(StatoProdottoOrdinato.LAVORAZIONE, tipoProdotto)) {
            if (!this.tavoli.contains(tavolo)) {
                this.tavoli.add(tavolo);
            }
        }
    }

    /**
     * riempimento Vbox di prodotti per tavolo
     * @param tavolo
     * @return
     */
    protected VBox initVboxProdotti(int tavolo){
        VBox vBox1 = new VBox();
        vBox1.setPrefHeight(217);
        vBox1.setPrefWidth(668);
        vBox1.setLayoutX(14);
        vBox1.setLayoutY(30);
        for(ProdottoOrdinato p : this.ordini){
            if(tavolo == p.getIdTavolo()){
                Text prodotto = new Text(p.getQuantita() + "x "+ p.getProdotto().getNome() + " (" + p.getStato() + ")");
                JFXButton pronto = new JFXButton("PRONTO");
                pronto.setId(Integer.toString(p.getId()));
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

    /**
     * Riempipento pannelli per tavolo
     * @param tavolo
     * @param vBox
     * @return
     */
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

    /**
     * Lancia thread per eliminare il prodotto cioè setto lo stato del prodotto selezionato in stato consegnato
     * @param button
     */
    protected  void eliminaProdottoPronto(JFXButton button){
        boolean check=false;
        for(int tavolo : tavoli) {
            for (ProdottoOrdinato ord : ordini) {
                if(tavolo == ord.getIdTavolo()) {
                    if (button.getId().equals(Integer.toString(ord.getId()))) {
                        p = ord;
                        check = true;
                        break;
                    }
                }
            }
        }
        if(check) {
            FXServiceStaffButton fxServiceStaffButton;
            fxServiceStaffButton = new FXServiceStaffButton(serverCentraleStaff, p, StatoProdottoOrdinato.CONSEGNATO);
            fxServiceStaffButton.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    vbox.getChildren().clear();
                    vbox.getChildren().add(loadProdottiTemp());
                    ordini.remove(p);
                }
            });
            button.setDisable(true);
            fxServiceStaffButton.start();
        }
    }
}
