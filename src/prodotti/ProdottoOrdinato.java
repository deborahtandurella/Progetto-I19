package prodotti;

import eccezioni.OrdinazioneNegativaException;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ProdottoOrdinatoSerializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdottoOrdinato implements ProdottoOrdinatoInterface{

	private final int id;
	private final Prodotto prodotto;
	private int quantita;
	private StatoProdottoOrdinato statoProdottoOrdinato;
	private int idTavolo;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime tempoInizioLavorazione;

	
	public ProdottoOrdinato() {
		this.prodotto = null;
		this.id = 0;
	}
	
	public ProdottoOrdinato(Prodotto prodotto, int quantita, int idTavolo) throws OrdinazioneNegativaException {
		if (quantita <= 0) {
			throw new OrdinazioneNegativaException();
		}
		
		this.prodotto = prodotto;
		this.quantita = quantita;
		this.statoProdottoOrdinato = StatoProdottoOrdinato.ORDINATO;
		this.tempoInizioLavorazione = null;
		this.idTavolo = idTavolo;
		this.id = 0;
	}

	
	public int getId() {
		return id;
	}

	public LocalDateTime getTempoInizioLavorazione() {
		return tempoInizioLavorazione;
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
		this.statoProdottoOrdinato = stato;
	}

	public StatoProdottoOrdinato getStato() {
		return statoProdottoOrdinato;
	}

	public void setStatoProdottoOrdinatoLavorazione() {
		this.statoProdottoOrdinato = StatoProdottoOrdinato.LAVORAZIONE;
		this.tempoInizioLavorazione = LocalDateTime.now();
	}
	
	public int getTempoElaborazioneRimanente(int max) {
		int tempoPassato = (int)Duration.between(this.tempoInizioLavorazione, LocalDateTime.now()).getSeconds();
		return max - tempoPassato;
	}
	
	public int getIdTavolo() {
		return idTavolo;
	}
	
	public void setStatoProdottoOrdinato(StatoProdottoOrdinato statoProdottoOrdinato) {
		this.statoProdottoOrdinato = statoProdottoOrdinato;
	}

	public void setTempoInizioLavorazione(LocalDateTime tempoInizioLavorazione) {
		this.tempoInizioLavorazione = tempoInizioLavorazione;
	}

	@Override
	public boolean equals(Object o){

		ProdottoOrdinato p = (ProdottoOrdinato) o;

		return p.getProdotto().equals(this.getProdotto()) &&
				p.getQuantita() == this.getQuantita() &&
				p.getIdTavolo() == this.getIdTavolo();
	}
}
