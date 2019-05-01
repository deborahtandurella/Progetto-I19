package prodotti;

import eccezioni.OrdinazioneNegativaException;

public class ProdottoOrdinato {
    private final Prodotto prodotto;
    private final int quantita;
    private StatoProdottoOrdinato stato;

    public ProdottoOrdinato(Prodotto prodotto, int quantita) throws OrdinazioneNegativaException {
        this.prodotto = prodotto;
        this.quantita = quantita;
        this.stato = null;

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

    public void setStato(StatoProdottoOrdinato stato) {
        this.stato = stato;
    }

    public StatoProdottoOrdinato getStato() {
        return stato;
    }
}

