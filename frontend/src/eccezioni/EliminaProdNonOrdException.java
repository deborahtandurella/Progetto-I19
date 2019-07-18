package eccezioni;

public class EliminaProdNonOrdException extends Exception {

    public EliminaProdNonOrdException() {
        super("Impossibile eliminare: Prodotto non ordinato");
    }
}
