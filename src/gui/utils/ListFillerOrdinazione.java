package gui.utils;

import com.jfoenix.controls.JFXButton;
import gui.ConfermaOrdinazioneController;
import gui.Launcher;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import prodotti.Prodotto;
import prodotti.TipoPortata;

import java.util.ArrayList;

public class ListFillerOrdinazione {

    public void vBoxFiller(ArrayList<Prodotto> aLP, VBox vbox){

        int indiceBottone=0;

        for(Prodotto p : aLP){
            AnchorPane tempPane = new AnchorPane();
            JFXButton addTemp = new JFXButton("ELIMINA");
            Text titleTemp = new Text(p.getNome());
            titleTemp.setId("titletemp");
            Text descTemp = new Text(p.getDescrizione());
            descTemp.setId("desctemp");

            tempPane.getChildren().addAll(titleTemp, descTemp, addTemp);
            tempPane.getStylesheets().add(getClass().getResource("/gui/style/StyleOrdination.css").toExternalForm());

            addTemp.setLayoutX(562.0);
            addTemp.setLayoutY(6.0);
            addTemp.setId(Integer.toString(indiceBottone));
            addTemp.setOnAction(this::deleteProdotto);
            titleTemp.setLayoutX(7.0);
            titleTemp.setLayoutY(22.0);
            descTemp.setLayoutX(7.0);
            descTemp.setLayoutY(42.0);
            descTemp.setWrappingWidth(540.0);

            vbox.getChildren().addAll(tempPane);
            indiceBottone++;
        }
    }


    public void deleteProdotto(ActionEvent event)  {
        JFXButton o= (JFXButton) event.getSource();
        ListFiller l=new ListFiller();
        ConfermaOrdinazioneController c=new ConfermaOrdinazioneController();
        l.getProdotti().remove(Integer.parseInt(o.getId()));
        vBoxFiller(l.getProdotti(),c.getvBoxList());
    }
}
