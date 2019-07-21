package gui.cliente.utils;

import com.jfoenix.controls.JFXButton;
import eccezioni.OrdinazioneNegativaException;
import gui.cliente.controller.SelectorTableIdController;
import prodotti.prodotto.Prodotto;
import prodotti.prodotto_ordinato.ProdottoOrdinato;

import java.util.ArrayList;

/**
 * Permette la visualizzazione dell'incrementazione/decrementazione dei prodotti nel carrello
 * Mi istanzia inoltre i prodotti Ordinati
 */
public class ManagerCarrello {
    private static ArrayList<ProdottoOrdinato> prodottiOrdinati = new ArrayList<>();

    /**
     * Incrementa quantità del carrello quando aggiungo prodotto
     * @param prodotto
     * @param carrello
     * @throws OrdinazioneNegativaException
     */
    static void addProdottoOrdinato(Prodotto prodotto, JFXButton carrello) throws OrdinazioneNegativaException {
        for(ProdottoOrdinato prodottoOrdinato1 : prodottiOrdinati){
            if(prodottoOrdinato1.getProdotto().getId() == prodotto.getId()){
                prodottoOrdinato1.addQuantita();
                refreshOrdinazioniButton(carrello);
                return;
            }
        }
        ProdottoOrdinato prodottoOrdinato = new ProdottoOrdinato(prodotto, 1, SelectorTableIdController.idTavolo);
        prodottiOrdinati.add(prodottoOrdinato);
        refreshOrdinazioniButton(carrello);
    }

    public static void refreshOrdinazioniButton(JFXButton carrello){
        carrello.setText(String.valueOf(getNumeroProdottiOrdinati()));
    }

    /**
     * Decrementa quantità del carello in base ad id
     * @param id
     * @param carrello
     */
    public static void removeProdottoOrdinato(int id, JFXButton carrello){
        boolean check=false;
        ProdottoOrdinato temp= new ProdottoOrdinato();
        for (ProdottoOrdinato p : prodottiOrdinati) {
                if (p.getProdotto().getId() == id) {
                    if (p.getQuantita() > 1) {
                        p.minusQuantita();
                    } else {
                        temp=p;
                        check=true;
                    }
                }
        }
        if(check) prodottiOrdinati.remove(temp);
        refreshOrdinazioniButton(carrello);
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
