package gui;

import com.jfoenix.controls.JFXButton;
import gui.utils.FXMLManager;
import gui.utils.ManagerOrdinazioni;
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
import ordinazioni.Ordinazione;
import prodotti.ProdottoOrdinato;
import prodotti.StatoProdottoOrdinato;
import prodotti.TipoPortata;
import prodotti.TipoProdotto;
import serverCentrale.ServerCentraleInterno;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.StrictMath.abs;

public class CucinaController implements Initializable {

    private List<ProdottoOrdinato> ordini = new ArrayList<>();
    private ServerCentraleInterno serverCentraleInterno = new ServerCentraleInterno();
    private List<Integer> vettore = new ArrayList<>();
    public VBox vbox = new VBox();

    @Override
    public void initialize(URL location, ResourceBundle resources) { refresh(vbox); }

   /* public VBox loadProdottiOrdinati(){
        AnchorPane PaneTavolo= new AnchorPane();
        VBox vboxTavolo= new VBox();
        ordini = serverCentraleInterno.getOrdini(TipoProdotto.CUCINA,StatoProdottoOrdinato.ORDINATO);
        vettore = serverCentraleInterno.getTavoli();
        int indiceBottone=0;

        for(Integer tavolo : vettore) {
            Text table = new Text("TAVOLO N. " + tavolo);
            table.setId("tableText");
            JFXButton startTimer = new JFXButton("START TIMER");
            startTimer.setId(Integer.toString(tavolo));
            startTimer.setOnAction(this::setTimer);
            PaneTavolo.setId("mainAnchor");
            PaneTavolo.setPrefHeight(300);
            PaneTavolo.setPrefWidth(959);
            PaneTavolo.getChildren().add(table);
            PaneTavolo.getChildren().add(startTimer);
            PaneTavolo.getStylesheets().add(getClass().getResource("/gui/style/StyleCucina.css").toExternalForm());
            vboxTavolo.setPrefHeight(300);
            vboxTavolo.setPrefWidth(959);

            for (ProdottoOrdinato ord : ordini) {
                if(tavolo == ord.getIdTavolo()) {

                    Text prodotto = new Text(ord.getProdotto().getNome());
                    JFXButton pronto = new JFXButton("PRONTO");
                    pronto.setId(Integer.toString(indiceBottone));
                    indiceBottone++;
                    pronto.setOnAction(this::setPronto);

                    pronto.setLayoutX(562);
                    pronto.setLayoutY(50);
                    pronto.setPrefHeight(39);
                    pronto.setPrefWidth(130);
                    prodotto.setLayoutY(100);
                    PaneTavolo.getChildren().add(prodotto);
                    PaneTavolo.getChildren().add(pronto);
                    startTimer.setLayoutX(711);
                    startTimer.setLayoutY(28);
                    table.setLayoutX(7.0);
                    table.setLayoutY(200);
                    table.setStrokeType(StrokeType.OUTSIDE);
                }
            }
            vboxTavolo.getChildren().add(PaneTavolo);
        }
        vbox.getChildren().add(vboxTavolo);
        return vbox;
    }*/

    public VBox loadProdottiOrdinati(){
        int indiceBottone=0;
        ordini = serverCentraleInterno.getOrdini(TipoProdotto.CUCINA,StatoProdottoOrdinato.ORDINATO);
        vettore = serverCentraleInterno.getTavoli();
        VBox vBox = new VBox();
        for(Integer tavolo : vettore) {

            Text table = new Text("TAVOLO N. " + tavolo);
            VBox vBox1 = new VBox();
            JFXButton startTimer = new JFXButton("START TIMER");
            startTimer.setId(Integer.toString(tavolo));
            startTimer.setOnAction(this::setTimer);
            startTimer.setLayoutX(711);
            startTimer.setLayoutY(28);

            for (ProdottoOrdinato ord : ordini) {
                if(tavolo == ord.getIdTavolo()) {
                    AnchorPane tempPane1 = new AnchorPane();

                    vBox1.setPrefHeight(217);
                    vBox1.setPrefWidth(668);

                    Text prodotto = new Text(ord.getProdotto().getNome());
                    JFXButton pronto = new JFXButton("PRONTO");
                    pronto.setId(Integer.toString(indiceBottone));
                    indiceBottone++;
                    pronto.setOnAction(this::setPronto);

                    pronto.setLayoutX(562);
                    pronto.setPrefHeight(39);
                    pronto.setPrefWidth(106);
                    prodotto.setLayoutY(27);

                    AnchorPane pane = new AnchorPane(prodotto, pronto);
                    pane.setId("secondAnchor");
                    vBox1.getChildren().add(pane);


                    table.setId("tableText");

                    tempPane1.getChildren().addAll(startTimer,table, vBox1);
                    tempPane1.setId("mainAnchor");
                    tempPane1.setPrefHeight(115);
                    tempPane1.setPrefWidth(959);

                    tempPane1.getStylesheets().add(getClass().getResource("/gui/style/StyleCucina.css").toExternalForm());



                    table.setLayoutX(7.0);
                    table.setLayoutY(22.0);
                    table.setStrokeType(StrokeType.OUTSIDE);
                    vBox1.setLayoutX(14);
                    vBox1.setLayoutY(30);
                    vBox.getChildren().addAll(tempPane1);
                }
            }
        }
        return vBox;
    }

    public void refresh(VBox vbox){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event1 ->{
            this.vbox.getChildren().clear();
            vbox.getChildren().add(this.loadProdottiOrdinati());
        }
        ),
                new KeyFrame(Duration.seconds(5)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

/*
    public void setOrdini() {
        ordini = serverCentraleInterno.getOrdini(TipoProdotto.CUCINA);
        vettore = serverCentraleInterno.getTavoli();
        System.out.println(vettore);
        System.out.println(ordini.toString());
    }
*/
    public  List<ProdottoOrdinato> getOrdini() {
        return ordini;
    }

    public void setTimer(ActionEvent event)  {

        JFXButton b= (JFXButton)event.getSource();
        for(Integer tavolo : vettore){
            if (b.getId().equals(Integer.toString(tavolo)))
            {
                for (ProdottoOrdinato ord : ordini) {
                    if (ord.getProdotto().getTipoPortata() == TipoPortata.PIATTI || ord.getProdotto().getTipoPortata() == TipoPortata.DOLCI) {
                       // ord.setStatoProdottoOrdinatoLavorazione(); //DA SETTARE NEL DB TEMPO INIZIO LAVRAZIONE
                        serverCentraleInterno.changeStatoProdottoOrdinato(ord, StatoProdottoOrdinato.LAVORAZIONE);
                    }
                }
            }
        }
    }

    public int maxTempoPreparazione()
    {
        int max=0;
        for(ProdottoOrdinato ord : ordini){
            if (HomeController.getnTavolo() == ord.getIdTavolo())
            {
                    if (ord.getProdotto().getTipoPortata()== TipoPortata.PIATTI || ord.getProdotto().getTipoPortata()== TipoPortata.DOLCI)
                    {
                       if(ord.getProdotto().getTempoPreparazione()>=max) {
                           max = ord.getProdotto().getTempoPreparazione();
                       }
                    }
            }
        }
        return  max*60;
    }


    public void setPronto(ActionEvent event)  {
        JFXButton o = (JFXButton) event.getSource();
        int  index = 0;
        for(ProdottoOrdinato ord : ordini){
            if (HomeController.getnTavolo() == ord.getIdTavolo()) {
                    if (o.getId().equals(Integer.toString(index)))
                    {
                        serverCentraleInterno.changeStatoProdottoOrdinato(ord,StatoProdottoOrdinato.CONSEGNATO);
                        index=0;
                        break;
                    }
                    index++;
            }
        }
    }
}
