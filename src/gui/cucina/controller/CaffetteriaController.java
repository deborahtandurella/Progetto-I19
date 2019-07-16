package gui.cucina.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CaffetteriaController implements Initializable {

    private List<ProdottoOrdinato> ordini = new ArrayList<>();
    private ServerCentraleInterno serverCentraleInterno = new ServerCentraleInterno();
    private List<Integer> tavoli = new ArrayList<>();
    public VBox vbox;
    public final int REFRESH_RATE = 3;

    @Override
    public void initialize(URL location, ResourceBundle resources) { refresh(vbox); }

    public VBox loadProdottiOrdinati(){

        int indiceBottone=0;
        this.getTavoliAperti();
        this.ordini = serverCentraleInterno.getOrdini(TipoProdotto.CAFFETTERIA, StatoProdottoOrdinato.ORDINATO);
        this.ordini.addAll(serverCentraleInterno.getOrdini(TipoProdotto.CAFFETTERIA, StatoProdottoOrdinato.LAVORAZIONE));

        VBox vBox = new VBox();
        for(Integer tavolo : this.tavoli){
            Text table = new Text("TAVOLO N. " + tavolo);

            VBox vBox1 = new VBox();
            AnchorPane tempPane1 = new AnchorPane();

            vBox1.setPrefHeight(217);
            vBox1.setPrefWidth(668);

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

            table.setId("tableText");

            tempPane1.getChildren().addAll(table, vBox1);
            tempPane1.setId("mainAnchor");
            tempPane1.setPrefHeight(115);
            tempPane1.setPrefWidth(959);

            tempPane1.getStylesheets().add(getClass().getResource("/gui/cucina/style/StyleCucina.css").toExternalForm());

            table.setLayoutX(7.0);
            table.setLayoutY(22.0);
            table.setStrokeType(StrokeType.OUTSIDE);
            vBox1.setLayoutX(14);
            vBox1.setLayoutY(30);
            vBox.getChildren().addAll(tempPane1);
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
        this.tavoli = serverCentraleInterno.getTavoli(StatoProdottoOrdinato.ORDINATO, TipoProdotto.CAFFETTERIA);
        for(Integer tavolo : serverCentraleInterno.getTavoli(StatoProdottoOrdinato.LAVORAZIONE)) {
            if (!this.tavoli.contains(tavolo)) {
                this.tavoli.add(tavolo);
            }
        }
    }


    public void setPronto(ActionEvent event)  {
        JFXButton o = (JFXButton) event.getSource();
        int  index = 0;
        for(ProdottoOrdinato ord : ordini){
            if (o.getId().equals(Integer.toString(index))) {
                serverCentraleInterno.changeStatoProdottoOrdinato(ord,StatoProdottoOrdinato.CONSEGNATO);
                index=0;
                break;
            }
            index++;
        }
    }
}
