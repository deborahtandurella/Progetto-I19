package prodotti;

public class Prodotto {
    private String nome;
    private float prezzo;
    private String descrizione;

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
        return  '\n' + nome + '|' + descrizione + '|' + prezzo;
    }
}

