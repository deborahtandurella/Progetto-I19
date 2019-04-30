package eccezioni;

public class PrezzoNegativoException extends Exception {

    public PrezzoNegativoException() {

        super("Attenzione: Prezzo prodotto negativo");

    }
}
