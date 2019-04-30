package prodotti;

public class Prodotto {
    private final String nome;
    private final float prezzo;
    private final String descrizione;
    private int tempoPreparazione;

    public Prodotto(String nome,float prezzo, String descrizione, int tempoPreparazione) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.tempoPreparazione = tempoPreparazione;
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
}

