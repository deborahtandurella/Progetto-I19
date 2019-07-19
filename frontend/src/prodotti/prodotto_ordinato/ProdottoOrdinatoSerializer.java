package prodotti.prodotto_ordinato;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import prodotti.prodotto_ordinato.ProdottoOrdinato;

/**
 * Classe che permette la conversione di oggetti da Json a Java
 */
public class ProdottoOrdinatoSerializer extends StdSerializer<ProdottoOrdinato>{

	public ProdottoOrdinatoSerializer() {
		this(ProdottoOrdinato.class);
	}
	
	public ProdottoOrdinatoSerializer(Class<ProdottoOrdinato> t) {
		super(t);
	}
	
	@Override
	public void serialize(ProdottoOrdinato value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		
		gen.writeNumberField("idTavolo", value.getIdTavolo());
		gen.writeNumberField("quantita", value.getQuantita());
		
		if(value.getTempoInizioLavorazione() == null) {
			gen.writeStringField("tempoInizioLavorazione", null);
		}else {
			gen.writeStringField("tempoInizioLavorazione", value.getTempoInizioLavorazione().toString());
		}
		
		gen.writeNumberField("prodotto", value.getProdotto().getId());
		gen.writeNumberField("statoProdottoOrdinato", value.getStato().value());

		gen.writeEndObject();
		
	}

}
