package serverCentrale;

import eccezioni.ResetDatabaseException;

public final class ApiURL {
	
	private final String VPS_IP = "http://137.74.165.12";
	private final String PRODOTTO = "prodotto/";
	private final String PRODOTTO_ORDINATO = "prodotto_ordinato/";
	private final String ORDINAZIONE = "ordinazione/";
	private final String ID_TAVOLO = "id_tavolo/";
	private final String CONTO = "conto/";
	private final String RESET_DATABASE = "reset_testdb/";
	
	private final String base_name;
	private final boolean test;
	
	public ApiURL(boolean test) {
		this.test = test;
		if(test) {
			this.base_name = this.VPS_IP + ":8081/";
		}else {
			this.base_name = this.VPS_IP + "/";
		}
	}

	public String getProdotto() {
		return this.base_name + PRODOTTO;
	}

	public String getProdottoOrdinato() {
		return this.base_name + PRODOTTO_ORDINATO;
	}

	public String getOrdinazione() {
		return this.base_name + ORDINAZIONE;
	}

	public String getIDTavolo() {
		return this.base_name + ID_TAVOLO;
	}

	public String getConto() {
		return this.base_name + CONTO;
	}
	
	public String getResetDatabase() {
		if(!this.test) {
			throw new ResetDatabaseException();
		}
		
		return this.base_name + RESET_DATABASE;
	}
	
}
