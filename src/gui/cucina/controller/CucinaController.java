package gui.cucina.controller;

import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import prodotti.prodotto.TipoProdotto;

public class CucinaController extends AbstractGUIStaffController {

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
