package eccezioni;

public class ErrorTableException extends  Exception {
    public ErrorTableException() {
        super("Inserisci un numero tavolo corretto");
    }
}
