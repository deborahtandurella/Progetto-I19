package prodotti;

import eccezioni.PrezzoNegativoException;

public class Prodotto {
    private final int id;
    private final String nome;
    private final float prezzo;
    private final String descrizione;
    private int tempoPreparazione;
    private TipoProdotto tipo;
    private TipoPortata tipoPortata;
    private static int newId = 0;

    public Prodotto(String nome,float prezzo, String descrizione, int tempoPreparazione, TipoProdotto tipo, TipoPortata tipoP) throws PrezzoNegativoException {
    	
    	if(prezzo <= 0){
            throw new PrezzoNegativoException();
        }
        this.prezzo = prezzo;
        this.nome = nome;
        this.descrizione = descrizione;
        this.tempoPreparazione = tempoPreparazione;
        this.tipo = tipo;
        this.tipoPortata = tipoP;
        this.id = newId++;
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

    public int getId() {
        return id;
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

    public TipoPortata getTipoPortata() {
        return tipoPortata;
    }
}

