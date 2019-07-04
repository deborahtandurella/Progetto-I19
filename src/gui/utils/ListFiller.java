package gui.utils;

import com.jfoenix.controls.JFXButton;
import eccezioni.OrdinazioneNegativaException;
import gui.VisualizzaProdottiController;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import prodotti.Prodotto;
import prodotti.TipoPortata;
import serverCentrale.ServerCentrale;
import serverCentrale.ServerCentraleEsterno;

import java.util.ArrayList;
import java.util.List;

public class ListFiller {

    private VisualizzaProdottiController visualizzaProdottiController;
    static ArrayList<Prodotto> prodotti= new ArrayList<>();
    private ServerCentraleEsterno serverCentraleEsterno = new ServerCentraleEsterno();

    public ListFiller (VisualizzaProdottiController visualizzaProdottiController, VBox vBox, TipoPortata tipoPortata){
        this.visualizzaProdottiController = visualizzaProdottiController;
        vBoxFiller(serverCentraleEsterno.getMenu(tipoPortata), vBox);
    }

    private ArrayList<Prodotto> getMenu(){
        ServerCentrale serverCentrale = new ServerCentrale();
        return (ArrayList<Prodotto>) serverCentrale.getMenu();
    }

    public void vBoxFiller(List<Prodotto> aLP, VBox vbox){

        for(Prodotto p : aLP){
            AnchorPane tempPane = new AnchorPane();
            JFXButton addTemp = new JFXButton("+");
            Text titleTemp = new Text(p.getNome());
            titleTemp.setId("titletemp");
            Text descTemp = new Text(p.getDescrizione());
            descTemp.setId("desctemp");

            tempPane.getChildren().addAll(titleTemp, descTemp, addTemp);
            tempPane.getStylesheets().add(getClass().getResource("/gui/style/Style.css").toExternalForm());

            addTemp.setLayoutX(562.0);
            addTemp.setLayoutY(6.0);
            addTemp.setId(Integer.toString(p.getId()));
            addTemp.setOnAction(this::addProdotto);
            titleTemp.setLayoutX(7.0);
            titleTemp.setLayoutY(22.0);
            descTemp.setLayoutX(7.0);
            descTemp.setLayoutY(42.0);
            descTemp.setWrappingWidth(540.0);

            vbox.getChildren().addAll(tempPane);
        }
    }

    public void addProdotto(ActionEvent event) {
        JFXButton o= (JFXButton) event.getSource();
        Prodotto pTemp = null;

        for(Prodotto p : getMenu()) {
            if(p.getId() == Integer.parseInt(o.getId())){
                pTemp = p;
                break;
            }
        }

        try {
            ManagerOrdinazioni.addProdOrd(pTemp, visualizzaProdottiController.carrello);
        } catch(OrdinazioneNegativaException e) {
            System.err.println(e.getMessage());
        }

    }

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }
}
