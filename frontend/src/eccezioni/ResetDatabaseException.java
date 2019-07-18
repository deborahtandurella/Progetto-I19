package eccezioni;

public class ResetDatabaseException extends RuntimeException{
	
	public ResetDatabaseException() {
		super("Non puoi resettare il Database di produzione");
	}
	
}
