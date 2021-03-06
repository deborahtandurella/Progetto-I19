package prodotti.prodotto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import eccezioni.PrezzoNegativoException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Prodotto{
    private final int id;
    private final String nome;
    private final float prezzo;
    private final String descrizione;
    private int tempoPreparazione;
    private TipoProdotto tipo;
    private TipoPortata tipoPortata;

    public Prodotto() {
    	this.id = 0;
    	this.nome = "";
    	this.prezzo = 0;
    	this.descrizione = "";
    }
    
    public Prodotto(int id, String nome, float prezzo, String descrizione, int tempoPreparazione, TipoProdotto tipo, TipoPortata tipoP) throws PrezzoNegativoException {
    	
    	this.id = id;
    	if(prezzo <= 0){
            throw new PrezzoNegativoException();
        }
        this.prezzo = prezzo;
        this.nome = nome;
        this.descrizione = descrizione;
        this.tempoPreparazione = tempoPreparazione;
        this.tipo = tipo;
        this.tipoPortata = tipoP;
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

    public TipoProdotto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProdotto tipo) {
        this.tipo = tipo;
    }

    public TipoPortata getTipoPortata() {
        return tipoPortata;
    }

    @Override
    public boolean equals(Object o){

        Prodotto p = (Prodotto) o;

        return p.getNome().equals(this.getNome()) &&
               p.getDescrizione().equals(this.getDescrizione()) &&
                p.getTempoPreparazione() == this.getTempoPreparazione() &&
                p.getTipo() == this.getTipo() &&
                p.getTipoPortata() == this.getTipoPortata() &&
                p.getPrezzo() == this.getPrezzo();
    }
}

