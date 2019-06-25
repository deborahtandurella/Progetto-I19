package prodotti;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoPortata {
    PIATTI(0), BEVANDE(1), VINI(2), DOLCI(3);
	
	private final int value;
	
	private TipoPortata(final int value) {
		this.value = value;
	}
	
	@JsonValue
	public final int value() {
		return this.value;
	}
}
