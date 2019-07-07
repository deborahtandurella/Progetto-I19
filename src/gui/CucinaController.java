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

    private static List<ProdottoOrdinato> ordini = new ArrayList<>();
    ServerCentraleInterno serverCentraleInterno = new ServerCentraleInterno();
    public VBox vbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) { refresh(vbox); }

    public VBox loadProdottiOrdinati(){
        int indiceBottone=0;

        VBox vBox = new VBox();
        for(ProdottoOrdinato ord : ordini){
            Text table = new Text("TAVOLO N. " + ord.getIdTavolo());
            JFXButton startTimer = new JFXButton("START TIMER");
            startTimer.setId(Integer.toString(ord.getIdTavolo()));
            startTimer.setOnAction(this::setTimer);

            VBox vBox1 = new VBox();
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

            tempPane1.getChildren().addAll(table, startTimer, vBox1);
            tempPane1.setId("mainAnchor");
            tempPane1.setPrefHeight(115);
            tempPane1.setPrefWidth(959);

            tempPane1.getStylesheets().add(getClass().getResource("/gui/style/StyleCucina.css").toExternalForm());


            startTimer.setLayoutX(711);
            startTimer.setLayoutY(28);
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
        }
        ),
                new KeyFrame(Duration.seconds(0.5)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }


    public void setOrdini() {
        ordini = serverCentraleInterno.getOrdini(TipoProdotto.CUCINA);
    }

    public  List<ProdottoOrdinato> getOrdini() {
        return ordini;
    }

    public void setTimer(ActionEvent event)  {
        JFXButton b= (JFXButton)event.getSource();
        for(ProdottoOrdinato ord : ordini){
            if (b.getId().equals(Integer.toString(ord.getIdTavolo())))
            {
                    if (ord.getProdotto().getTipoPortata()== TipoPortata.PIATTI || ord.getProdotto().getTipoPortata()== TipoPortata.DOLCI)
                    {
                        serverCentraleInterno.changeStatoProdottoOrdinato(ord, StatoProdottoOrdinato.LAVORAZIONE);
                        ord.setStatoProdottoOrdinatoLavorazione();
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
                        //ManagerOrdinazioni.removeProdottoOrdinato(Integer.parseInt(o.getId()));
                        ordini.remove(Integer.parseInt(o.getId()));
                        index=0;
                        //System.out.println(o.getId());
                        break;
                    }
                    index++;
            }
        }
    }
}
