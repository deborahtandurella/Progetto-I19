package ordinazioni;

import prodotti.*;

import java.util.ArrayList;
import java.util.List;

public class Ordinazione implements OrdinazioneInterface extends StatoOrdinazione{

    private int idTavolo;
    private int id;
    private StatoOrdinazione stato;
    private List<ProdottoOrdinato> ordini;

    public Ordinazione(int idTavolo, int id, StatoOrdinazione stato){

        this.id = id;
        this.idTavolo = idTavolo;
        this.stato = stato;
        this.ordini  = new ArrayList<ProdottoOrdinato>();
    }

    public boolean aggiungiOrdini(Prodotto prodotto, int quantita){

        this.ordini.add(new ProdottoOrdinato(prodotto, quantita));
        return true;

    }


}
