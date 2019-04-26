package prodotti;

public class Prodotto {
    private String nome, descrizione;
    private float prezzo;

    public Prodotto(String nome, String descrizione,float prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public String toString() {
        return  '\n' + nome + '|' + descrizione + '|' + prezzo;
    }
}
