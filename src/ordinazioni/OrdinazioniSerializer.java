package ordinazioni;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import prodotti.ProdottoOrdinato;
import prodotti.StatoProdottoOrdinato;

public class OrdinazioniSerializer extends StdSerializer<Ordinazione>{

	public OrdinazioniSerializer() {
		this(Ordinazione.class);
	}

	protected OrdinazioniSerializer(Class<Ordinazione> t) {
		super(t);
	}
	
	@Override
	public void serialize(Ordinazione value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		
		gen.writeStartObject();
		gen.writeStringField("id_tavolo", Integer.toString(value.getIdTavolo()));
		gen.writeArrayFieldStart("ordini");
		
		for (ProdottoOrdinato prodottoOrdinato : value.getOrdini()) {
			gen.writeStartObject();
			gen.writeNumberField("quantita", prodottoOrdinato.getQuantita());
			gen.writeNumberField("prodotto", prodottoOrdinato.getProdotto().getId());
			gen.writeNumberField("stato", prodottoOrdinato.getStato().value());
			gen.writeEndObject();
		}
		
		gen.writeEndArray();
		gen.writeEndObject();
		
	}

}
