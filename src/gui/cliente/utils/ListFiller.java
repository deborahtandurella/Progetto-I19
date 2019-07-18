package gui.cliente.utils;

import com.jfoenix.controls.JFXButton;
import eccezioni.OrdinazioneNegativaException;
import gui.cliente.controller.VisualizzaProdottiController;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import prodotti.prodotto.Prodotto;
import serverCentrale.ServerCentraleCliente;

import java.util.ArrayList;
import java.util.List;

public class ListFiller {
    private ServerCentraleCliente serverCentraleCliente = new ServerCentraleCliente();
    private VisualizzaProdottiController visualizzaProdottiController;
    public static ArrayList<Prodotto> prodotti= new ArrayList<>();

    public ListFiller (VisualizzaProdottiController visualizzaProdottiController, VBox vBox, ArrayList menu){
        this.visualizzaProdottiController = visualizzaProdottiController;
        this.vBoxFiller(menu, vBox);
    }

    private ArrayList<Prodotto> getMenu(){
        return (ArrayList<Prodotto>) serverCentraleCliente.getMenu();
    }
    private void vBoxFiller(List<Prodotto> lista, VBox vbox){
        for(Prodotto p : lista){
            AnchorPane pane = initPane(p);
            vbox.getChildren().addAll(pane);
        }
    }

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }

    private void addProdotto(ActionEvent event) {
        JFXButton button = (JFXButton) event.getSource();
        Prodotto temp = null;
        for(Prodotto prodotto : getMenu()) {
            if(prodotto.getId() == Integer.parseInt(button.getId())){
                temp = prodotto;
                break;
            }
        }
        try {
            ManagerCarrello.addProdottoOrdinato(temp, visualizzaProdottiController.carrello);
        } catch(OrdinazioneNegativaException e) {
            System.err.println(e.getMessage());
        }
    }
    private AnchorPane initPane(Prodotto prodotto){
        AnchorPane tempPane = new AnchorPane();

        JFXButton addTemp = new JFXButton("+");
        addTemp.setLayoutX(562.0);
        addTemp.setLayoutY(6.0);
        addTemp.setId(Integer.toString(prodotto.getId()));
        addTemp.setOnAction(this::addProdotto);

        Text titleTemp = new Text(prodotto.getNome());
        titleTemp.setId("titletemp");
        titleTemp.setLayoutX(7.0);
        titleTemp.setLayoutY(22.0);

        Text valueTemp = new Text("" + prodotto.getPrezzo() +" euro");
        valueTemp.setId("valuetemp");
        valueTemp.setLayoutX(300);
        valueTemp.setLayoutY(22);

        Text descTemp = new Text(prodotto.getDescrizione());
        descTemp.setId("desctemp");
        descTemp.setLayoutX(7.0);
        descTemp.setLayoutY(42.0);
        descTemp.setWrappingWidth(540.0);

        tempPane.getChildren().addAll(titleTemp, descTemp,valueTemp, addTemp);
        tempPane.getStylesheets().add(getClass().getResource("/gui/cliente/style/Style.css").toExternalForm());

        return tempPane;
    }
}
