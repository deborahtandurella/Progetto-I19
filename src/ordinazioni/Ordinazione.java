package ordinazioni;

import prodotti.ProdottoOrdinato;

import java.util.ArrayList;
import java.util.List;

public class Ordinazione implements OrdinazioneInterface extends StatoOrdinazione{

    private int idTavolo;
    private int id;
    private StatoOrdinazione stato;
    private List<ProdottoOrdinato> ordini = new ArrayList<ProdottoOrdinato>();

    public Ordinazione(int idTavolo, int id, StatoOrdinazione stato, List<ProdottoOrdinato> ordini){

        this.id = id;
        this.idTavolo = idTavolo;
        this.stato = stato;
        this.ordini = ordini;
    }

    public boolean AggiungiOrdini(prodotti.Prodotto prodotto, int quantita){

        ordini.add(new ProdottoOrdinato(prodotto, quantita));

        return true;

    }


}
