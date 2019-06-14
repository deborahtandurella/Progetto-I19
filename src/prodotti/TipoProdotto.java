package prodotti;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoProdotto {
    CUCINA(1), CAFFETTERIA(2);
	
	private final int value;
	
	private TipoProdotto(final int value) {
		this.value = value;
	}
	
	@JsonValue
	public final int value() {
		return this.value;
	}
}
