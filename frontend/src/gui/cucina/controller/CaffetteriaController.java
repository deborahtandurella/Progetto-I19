package gui.cucina.controller;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import prodotti.prodotto.TipoProdotto;

/**
 * Controller per la Caffetteria, gestisce prodotti di tipo Caffetteria
 */
public class CaffetteriaController extends AbstractGUIStaffController {

    public CaffetteriaController(TipoProdotto tipoProdotto) {
        super(tipoProdotto);
    }

    @Override
    public VBox loadProdottiTemp(){
        VBox vBox = new VBox();
        for(Integer tavolo : this.tavoli){
            VBox vBox1 = initVboxProdotti(tavolo);
            AnchorPane tempPane = initPaneTavolo(tavolo, vBox1);
            vBox.getChildren().addAll(tempPane);
        }
        return vBox;
    }
}
