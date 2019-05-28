package gui;

import gui.utils.Clock;
import gui.utils.FXMLManager;
import gui.utils.ListFiller;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VisualizzaProdottiController implements Initializable {

    public Label time;
    public VBox vBoxList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.initClock(time);
        ListFiller fillList = new ListFiller();

        switch (HomeController.getIndex()){
            case 0:
                fillList.piatti(vBoxList);
                break;
            case 1:
                fillList.bevande(vBoxList);
                break;
            case 2:
                fillList.vini(vBoxList);
                break;
            case 3:
                fillList.dolci(vBoxList);
                break;
            default:
                System.out.println("NOPE");
        }
    }

    public void loadHome(ActionEvent event) throws IOException {
        FXMLManager.loadFXML(event, "/gui/Home.fxml");
    }
}
