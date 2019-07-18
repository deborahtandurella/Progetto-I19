package eccezioni;

public class NessunOrdineException extends Exception {

    public NessunOrdineException() {
        super("Impossibile richiedere il conto: nessun ordine effettuato.");
    }
}
