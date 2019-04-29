package ordinazioni;

import prodotti.*;

import java.util.ArrayList;
import java.util.List;

public class Ordinazione implements OrdinazioneInterface {

    private int idTavolo;
    private int idOrdinazione;
    private StatoOrdinazione stato;
    private List<ProdottoOrdinato> ordini;
    private static int progressive = 1;

    public Ordinazione(int idTavolo){
        this.idOrdinazione = progressive++;
        this.idTavolo = idTavolo;
        this.stato = null;
        this.ordini  = new ArrayList<ProdottoOrdinato>();
    }

    public void setStato(StatoOrdinazione stato) {
        this.stato = stato;
    }

    @Override
    public boolean AggiungiOrdini(Prodotto prodotto, int quantita) {
        this.ordini.add(new ProdottoOrdinato(prodotto, quantita));
        return true;
    }


}
