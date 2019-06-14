package prodotti;

import eccezioni.OrdinazioneNegativaException;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdottoOrdinato {
	private final Prodotto prodotto;
	private int quantita;
	private StatoProdottoOrdinato stato;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime tempoInizioLavorazione;

	public ProdottoOrdinato() {
		this.prodotto = null;
	}
	
	public ProdottoOrdinato(Prodotto prodotto, int quantita) throws OrdinazioneNegativaException {
		if (quantita <= 0) {
			throw new OrdinazioneNegativaException();
		}
		
		this.prodotto = prodotto;
		this.quantita = quantita;
		this.stato = null;
		this.tempoInizioLavorazione = null;
	}

	public float getCostoParziale() {
		return prodotto.getPrezzo() * quantita;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void addQuantita(){
		quantita++;
	}

	public void setStato(StatoProdottoOrdinato stato) {
		this.stato = stato;
	}

	public StatoProdottoOrdinato getStato() {
		return stato;
	}

	public void setStatoProdottoOrdinatoLavorazione() {
		this.stato = StatoProdottoOrdinato.LAVORAZIONE;
		this.tempoInizioLavorazione = LocalDateTime.now();
	}
	
	public int getTempoElaborazioneRimanente(int max) {
		int tempoPassato = (int)Duration.between(this.tempoInizioLavorazione, LocalDateTime.now()).getSeconds();
		return max - tempoPassato;
	}
	
	public String getAllInfo(){
		return this.toString();
	}
	
    @Override
    public String toString() {
    	return  '{' + 
    			'\n' + '\t' + "\"prodotto\"" + ':' + '"' + getProdotto() + '"'  + ',' + 
    			'\n' + '\t' + "\"stato\"" + ':' + '"' + getStato() + '"' + ',' + 
    			//'\n' + '\t' + "\"temporimanente\"" + ':' + getTempoElaborazioneRimanente() +
    			'}'  ;
    }
}
