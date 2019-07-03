package gui.utils;

import com.jfoenix.controls.JFXButton;
import eccezioni.OrdinazioneNegativaException;
import gui.HomeController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import prodotti.Prodotto;
import prodotti.ProdottoOrdinato;

import java.util.ArrayList;

public class ManagerOrdinazioni {
    private static ArrayList<ProdottoOrdinato> prodottiOrdinati = new ArrayList<>();

    static void addProdOrd(Prodotto prodotto, JFXButton carrello) throws OrdinazioneNegativaException {
        for(ProdottoOrdinato prodottoOrdinato1 : prodottiOrdinati){
            if(prodottoOrdinato1.getProdotto() == prodotto){
                prodottoOrdinato1.addQuantita();
                refreshOrdinazioniButton(carrello);
                return;
            }
        }
        ProdottoOrdinato prodottoOrdinato = new ProdottoOrdinato(prodotto, 1, HomeController.getnTavolo());
        prodottiOrdinati.add(prodottoOrdinato);
        refreshOrdinazioniButton(carrello);

    }

    public static void refreshOrdinazioniButton(JFXButton carrello){
        carrello.setText(String.valueOf(ManagerOrdinazioni.getNumProdOrd()));
    }
    public static void removeProdottoOrdinato(int id){
        prodottiOrdinati.remove(id);
        //prodottiOrdinati.remove(getPOrdById(id));
    }

    public static ProdottoOrdinato getPOrdById(int id){
        ProdottoOrdinato po = null;
        for(ProdottoOrdinato p : prodottiOrdinati){
            if(id == p.getProdotto().getId()){
                po = p;
            }
        }
        return po;
    }

    public static ArrayList<ProdottoOrdinato> getProdottiOrdinati() {
        return prodottiOrdinati;
    }

    public static int getNumProdOrd(){
        int tot = 0;
        for(ProdottoOrdinato p : prodottiOrdinati){
            tot += p.getQuantita();
        }
        return tot;
    }
}
