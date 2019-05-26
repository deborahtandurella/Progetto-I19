package gui.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //Sarebbe preferibile creare una classe FXMLManager
    protected void loadFXML(ActionEvent event, String path) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(root);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void loadMenuPiatti(ActionEvent event) throws IOException {
        loadFXML(event, "VisualizzaProdotti.fxml");
    }

    public void loadDolci(ActionEvent event) throws IOException {
        loadFXML(event, "VisualizzaProdotti.fxml");
    }

    public void loadVini(ActionEvent event) throws IOException {
        loadFXML(event, "VisualizzaProdotti.fxml");
    }

    public void loadBevande(ActionEvent event) throws IOException {
        loadFXML(event, "VisualizzaProdotti.fxml");
    }

    //Sistemare i metodi load, quando sarà presente il db andranno caricate le liste dei prodotti
    //Aggiungere logica button "Ordinazione"
}
