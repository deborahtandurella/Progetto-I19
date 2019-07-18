package eccezioni;

public class TempoScadutoException extends Exception {

    public TempoScadutoException() {
        super("Tempo di preparazione scaduto!");
    }
}
