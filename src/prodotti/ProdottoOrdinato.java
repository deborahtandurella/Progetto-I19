package prodotti;

import eccezioni.OrdinazioneNegativaException;

public class ProdottoOrdinato {
    private final Prodotto prodotto;
    private final int quantita;

    public ProdottoOrdinato(Prodotto prodotto, int quantita) throws OrdinazioneNegativaException {
        this.prodotto = prodotto;
        this.quantita = quantita;

        if(quantita < 0){
            throw new OrdinazioneNegativaException();
        }

    }
    
    public float getCostoParziale(){
    	return prodotto.getPrezzo()*quantita;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public int getQuantita() {
        return quantita;
    }
}

