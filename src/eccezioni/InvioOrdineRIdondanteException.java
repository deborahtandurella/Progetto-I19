package eccezioni;

public class InvioOrdineRIdondanteException extends Exception {

    public InvioOrdineRIdondanteException() {
        super("Ordinazione già effettuata.");
    }
}
