package prodotti;

public class ProdottoOrdinato {
    private Prodotto prodotto;
    private int quantita;

    public ProdottoOrdinato(Prodotto prodotto, int quantita) {
        this.prodotto = prodotto;
        this.quantita = quantita;
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

