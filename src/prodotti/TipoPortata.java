package prodotti;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoPortata {
    PIATTI(1), BEVANDE(2), VINI(3), DOLCI(4);
	
	private final int value;
	
	private TipoPortata(final int value) {
		this.value = value;
	}
	
	@JsonValue
	final int value() {
		return this.value;
	}
}
