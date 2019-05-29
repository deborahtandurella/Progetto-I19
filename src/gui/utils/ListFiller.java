package gui.utils;

import com.jfoenix.controls.JFXButton;
import gui.Launcher;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import prodotti.Prodotto;
import prodotti.TipoPortata;

import java.util.ArrayList;

public class ListFiller {

    public ArrayList<Prodotto> searchByType(TipoPortata type){
        ArrayList<Prodotto> listafull = Launcher.initFullMenu();
        ArrayList<Prodotto> lista = new ArrayList<>();
        for (Prodotto p : listafull){
            if (p.getTipoPortata() == type){
                lista.add(p);
            }
        }
        return lista;
    }

    public void vBoxFiller(ArrayList<Prodotto> aLP, VBox vbox){

        int indiceBottone=0;

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
            addTemp.setId(Integer.toString(indiceBottone));
            addTemp.setOnAction(this::addProdotto);
            titleTemp.setLayoutX(7.0);
            titleTemp.setLayoutY(22.0);
            descTemp.setLayoutX(7.0);
            descTemp.setLayoutY(42.0);
            descTemp.setWrappingWidth(540.0);

            vbox.getChildren().addAll(tempPane);
            indiceBottone++;
        }
    }

    public void piatti(VBox vbox){
        vBoxFiller(searchByType(TipoPortata.PIATTI), vbox);
    }

    public void bevande(VBox vbox){
        vBoxFiller(searchByType(TipoPortata.BEVANDE), vbox);
    }

    public void vini(VBox vbox){
        vBoxFiller(searchByType(TipoPortata.VINI), vbox);
    }

    public void dolci(VBox vbox){
        vBoxFiller(searchByType(TipoPortata.DOLCI), vbox);
    }

    public void addProdotto(ActionEvent event)  {
        JFXButton o= (JFXButton) event.getSource();
        /*if(o.getId().equals("0")) System.out.println("Belco");
        if(o.getId().equals("1")) System.out.println("Daniel");
        if(o.getId().equals("2")) System.out.println("terronz");*/
        ArrayList<Prodotto> p=searchByType(TipoPortata.PIATTI);
        System.out.println(p.get(Integer.parseInt(o.getId())).getNome());

    }
}
