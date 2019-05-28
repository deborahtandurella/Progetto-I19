package gui;

import eccezioni.PrezzoNegativoException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import prodotti.Prodotto;
import prodotti.TipoProdotto;

import java.util.ArrayList;

public class Laucher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));

        primaryStage.setTitle("Ristorante");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /*
    Creazione prodotti per testare la grafica
     */
    static Prodotto p1, p2, p3;

    static {
        try {
            p1 = new Prodotto("Pasta Carbonara",10,"Pasta alla carbonara con guanciale", 10, TipoProdotto.CUCINA);
            p2 = new Prodotto("Risotto alla Milanese",12,"Il segreto di questo piatto della tradizione milanese risiede nella semplicità dei suoi ingredienti, che accostati tra loro creano un sapore raffinato e setoso.",10, TipoProdotto.CUCINA);
            p3 = new Prodotto("carne",18,"carne rossa", 15,TipoProdotto.CUCINA);

        } catch (PrezzoNegativoException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList retProdotti(){
        ArrayList<Prodotto> list = new ArrayList();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        return list;
    }
}
