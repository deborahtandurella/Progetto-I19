package ordinazioni;

import prodotti.ProdottoOrdinato;

public interface OrdinazioneInterface extends ProdottoOrdinato {

    public boolean AggiungiOrdini(prodotti.Prodotto prodotto, int quantita);

}
