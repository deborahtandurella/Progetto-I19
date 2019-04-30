package eccezioni;

public class ProdottoNonConsegnatoException extends Exception {

    public ProdottoNonConsegnatoException() {
        super("Impossibile richidere il conto: un prodotto non è stato consegnato.");
    }
}
