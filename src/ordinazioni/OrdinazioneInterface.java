package ordinazioni;

import prodotti.*;

public interface OrdinazioneInterface extends ProdottoOrdinato {

    public boolean AggiungiOrdini(Prodotto prodotto, int quantita);

}
