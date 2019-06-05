package gui;

import eccezioni.PrezzoNegativoException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import prodotti.Prodotto;
import prodotti.TipoPortata;
import prodotti.TipoProdotto;

import java.util.ArrayList;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));

        primaryStage.setTitle("Ristorante");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Stage secondStage = new Stage();
        Parent cucina = FXMLLoader.load(getClass().getResource("Cucina.fxml"));
        secondStage.setTitle("Cucina");
        secondStage.setScene(new Scene(cucina));
        secondStage.show();
    }

    /*
    Creazione prodotti per testare la grafica
     */
    static private Prodotto p1, p2, p3, p4, b1, v1, d1;

    static {
        try {
            p1 = new Prodotto("Pasta Carbonara",10,"Pasta alla carbonara con guanciale", 10, TipoProdotto.CUCINA, TipoPortata.PIATTI);
            p2 = new Prodotto("Risotto alla Milanese",12,"Il segreto di questo piatto della tradizione milanese risiede nella semplicità dei suoi ingredienti, che accostati tra loro creano un sapore raffinato e setoso.",10, TipoProdotto.CUCINA, TipoPortata.PIATTI);
            p3 = new Prodotto("carne",18,"carne rossa", 15,TipoProdotto.CUCINA, TipoPortata.PIATTI);
            p4 = new Prodotto("Caponata",15, "ALDOOOOOO",15,TipoProdotto.CUCINA,TipoPortata.PIATTI);
            b1 = new Prodotto("Sbobba",18,"La borra", 2,TipoProdotto.CAFFETTERIA,  TipoPortata.BEVANDE);
            v1 = new Prodotto("Sbobba Alcolica",18,"La borralcool", 2,TipoProdotto.CAFFETTERIA,  TipoPortata.VINI);
            d1 = new Prodotto("Tiramisù a 90",18,"con sbobba alcolica", 2,TipoProdotto.CUCINA,  TipoPortata.DOLCI);
        } catch (PrezzoNegativoException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Prodotto> initFullMenu(){
        ArrayList<Prodotto> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(b1);
        list.add(v1);
        list.add(d1);
        return list;
    }
}
