package prodotti;

import eccezioni.PrezzoNegativoException;

public class Prodotto {
    private final String nome;
    private final float prezzo;
    private final String descrizione;
    private int tempoPreparazione;
    private TipoProdotto tipo;

    public Prodotto(String nome,float prezzo, String descrizione, int tempoPreparazione, TipoProdotto tipo) throws PrezzoNegativoException {
    	
    	if(prezzo <= 0){
            throw new PrezzoNegativoException();
        }
        this.prezzo = prezzo;
        this.nome = nome;
        this.descrizione = descrizione;
        this.tempoPreparazione = tempoPreparazione;
        this.tipo= tipo;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione(){ return  descrizione; }

    public int getTempoPreparazione() {
        return tempoPreparazione;
    }

    @Override
    public String toString() {
        return '\n' + nome + '|' + prezzo;
    }

    public TipoProdotto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProdotto tipo) {
        this.tipo = tipo;
    }
}

