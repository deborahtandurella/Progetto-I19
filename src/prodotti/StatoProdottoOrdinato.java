package prodotti;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatoProdottoOrdinato {
    ORDINATO(1), LAVORAZIONE(2), CONSEGNATO(3);
	
	private final int value;
	
	private StatoProdottoOrdinato(final int value) {
		this.value = value;
	}
	
	@JsonValue
	public final int value() {
		return this.value;
	}
}
