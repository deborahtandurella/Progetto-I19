package gui;

import com.jfoenix.controls.JFXButton;
import gui.utils.Clock;
import gui.utils.FXMLManager;
import gui.utils.ListFiller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import prodotti.TipoPortata;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VisualizzaProdottiController implements Initializable {

    public Label time;
    public VBox vBoxList;
    public JFXButton viniButton;
    public JFXButton bevandeButton;
    public JFXButton dolciButton;
    public JFXButton piattiButton;

    private int n=0;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
        ListFiller fillList = new ListFiller();

        switch (HomeController.getIndex()){
            case 0:
                piattiButton.setTextFill(Paint.valueOf("#d50000"));
                fillList.piatti(vBoxList);
                break;
            case 1:
                bevandeButton.setTextFill(Paint.valueOf("#d50000"));
                fillList.bevande(vBoxList);
                break;
            case 2:
                viniButton.setTextFill(Paint.valueOf("#d50000"));
                fillList.vini(vBoxList);
                break;
            case 3:
                dolciButton.setTextFill(Paint.valueOf("#d50000"));
                fillList.dolci(vBoxList);
                break;
            default:
                System.out.println("NOPE");
        }
    }

    public void loadHome(ActionEvent event) throws IOException {
        FXMLManager.loadFXML(event, "/gui/Home.fxml");
    }
    
    public void aggiornaConteggioOrdine(ActionEvent event) {

       JFXButton ButtonConteggio=new JFXButton();
       ButtonConteggio.setText(Integer.toString(n));
       n++;
        System.out.println(n);
    }

    public void loadBevande(ActionEvent event) throws IOException {
        HomeController.setIndex(1);
        FXMLManager.loadFXML(event, "/gui/VisualizzaProdotti.fxml");
    }

    public void loadDolci(ActionEvent event) throws IOException {
        HomeController.setIndex(3);
        FXMLManager.loadFXML(event, "/gui/VisualizzaProdotti.fxml");
    }

    public void loadVini(ActionEvent event) throws IOException {
        HomeController.setIndex(2);
        FXMLManager.loadFXML(event, "/gui/VisualizzaProdotti.fxml");
    }

    public void loadPiatti(ActionEvent event) throws IOException {
        HomeController.setIndex(0);
        FXMLManager.loadFXML(event, "/gui/VisualizzaProdotti.fxml");
    }
}
