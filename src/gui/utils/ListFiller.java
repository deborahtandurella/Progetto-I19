package gui.utils;

import com.jfoenix.controls.JFXButton;
import eccezioni.OrdinazioneNegativaException;
import gui.cliente.controller.VisualizzaProdottiController;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import prodotti.Prodotto;
import serverCentrale.ServerCentraleEsterno;

import java.util.ArrayList;
import java.util.List;

public class ListFiller {

    private ServerCentraleEsterno serverCentraleEsterno = new ServerCentraleEsterno();
    private VisualizzaProdottiController visualizzaProdottiController;
    public static ArrayList<Prodotto> prodotti= new ArrayList<>();

    public ListFiller (VisualizzaProdottiController visualizzaProdottiController, VBox vBox, ArrayList menu){
        this.visualizzaProdottiController = visualizzaProdottiController;
        this.vBoxFiller(menu, vBox);
    }

    private ArrayList<Prodotto> getMenu(){
        return (ArrayList<Prodotto>) serverCentraleEsterno.getMenu();
    }

    private void vBoxFiller(List<Prodotto> aLP, VBox vbox){

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

    private void addProdotto(ActionEvent event) {
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
