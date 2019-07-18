package eccezioni;

public class NessunProdottoException extends Exception {

    public NessunProdottoException() {
        super("Errore: nessun prodotto selezionato.");
    }
}

