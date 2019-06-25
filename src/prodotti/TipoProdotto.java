package prodotti;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoProdotto {
    CUCINA(0), CAFFETTERIA(1);
	
	private final int value;
	
	private TipoProdotto(final int value) {
		this.value = value;
	}
	
	@JsonValue
	public final int value() {
		return this.value;
	}
}
