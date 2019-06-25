package prodotti;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatoProdottoOrdinato {
    ORDINATO(0), LAVORAZIONE(1), CONSEGNATO(2);
	
	
	private final int value;
	
	private StatoProdottoOrdinato(final int value) {
		this.value = value;
	}
	
	@JsonValue
	public final int value() {
		return this.value;
	}
	
	public String toJsonString() {
		return "{\"statoProdottoOrdinato\": " + this.value + "}";
	}
	
}
