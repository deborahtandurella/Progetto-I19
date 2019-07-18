package eccezioni;

public class TavoloIdException extends RuntimeException {

    public TavoloIdException(){
        super("ID tavolo già usato: cambiare numero.");
    }
}
