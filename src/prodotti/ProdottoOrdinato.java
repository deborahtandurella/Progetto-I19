package prodotti;

import eccezioni.OrdinazioneNegativaException;

import java.time.Duration;
import java.time.LocalDateTime;

public class ProdottoOrdinato {
	private final Prodotto prodotto;
	private final int quantita;
	private StatoProdottoOrdinato stato;
	private LocalDateTime tempoInizioLavorazione;

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
	
	public int getTempoElaborazioneRimanente() {
		int tempoPassato = (int)Duration.between(this.tempoInizioLavorazione, LocalDateTime.now()).getSeconds();
		return this.prodotto.getTempoPreparazione() - tempoPassato;
	}
    
}
