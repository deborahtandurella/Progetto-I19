package gui.cliente.utils;

import com.jfoenix.controls.JFXButton;
import eccezioni.OrdinazioneNegativaException;
import gui.cliente.controller.TableIdController;
import prodotti.prodotto.Prodotto;
import prodotti.prodotto_ordinato.ProdottoOrdinato;

import java.util.ArrayList;

public class ManagerOrdinazioni {
    private static ArrayList<ProdottoOrdinato> prodottiOrdinati = new ArrayList<>();

    static void addProdOrd(Prodotto prodotto, JFXButton carrello) throws OrdinazioneNegativaException {
        for(ProdottoOrdinato prodottoOrdinato1 : prodottiOrdinati){
            if(prodottoOrdinato1.getProdotto().getId() == prodotto.getId()){
                prodottoOrdinato1.addQuantita();
                refreshOrdinazioniButton(carrello);
                return;
            }
        }
        ProdottoOrdinato prodottoOrdinato = new ProdottoOrdinato(prodotto, 1, TableIdController.idTavolo);
        prodottiOrdinati.add(prodottoOrdinato);
        refreshOrdinazioniButton(carrello);

    }

    public static void refreshOrdinazioniButton(JFXButton carrello){
        carrello.setText(String.valueOf(getNumeroProdottiOrdinati()));
    }

    public static void removeProdottoOrdinato(int id, JFXButton carrello){
        prodottiOrdinati.remove(getProdottoOrdinatoById(id));
        refreshOrdinazioniButton(carrello);
    }

    private static ProdottoOrdinato getProdottoOrdinatoById(int id){
        ProdottoOrdinato prodottoOrdinato = null;
        for(ProdottoOrdinato p : prodottiOrdinati){
            if(id == p.getProdotto().getId()){
                prodottoOrdinato = p;
            }
        }
        return prodottoOrdinato;
    }

    public static ArrayList<ProdottoOrdinato> getProdottiOrdinati() {
        return prodottiOrdinati;
    }

    public static int getNumeroProdottiOrdinati(){
        int tot = 0;
        for(ProdottoOrdinato p : prodottiOrdinati){
            tot += p.getQuantita();
        }
        return tot;
    }

    public static void clearProdottiOrdinatiFromLocal(){
        prodottiOrdinati.clear();
    }
}
