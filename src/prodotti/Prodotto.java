package prodotti;

public class Prodotto {
    private String nome;
    private float prezzo;

    public Prodotto(String nome,float prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return  '\n' + nome + '|' + descrizione + '|' + prezzo;
    }
}

