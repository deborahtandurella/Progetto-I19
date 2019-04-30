package prodotti;

public class Prodotto {
    private final String nome;
    private final float prezzo;
    private final String descrizione;

    public Prodotto(String nome,float prezzo, String descrizione) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.descrizione=descrizione;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione(){ return  descrizione; }

    @Override
    public String toString() {
        return  '\n' + nome + '|' + prezzo;
    }
}

