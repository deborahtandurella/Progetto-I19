package ordinazioni;

import eccezioni.EliminaProdNonOrdException;
import eccezioni.OrdinazioneNegativaException;
import prodotti.*;
import java.util.ArrayList;

public class Ordinazione implements OrdinazioneInterface {

	private int idTavolo;
	private long idOrdinazione;
	private ArrayList<ProdottoOrdinato> ordini;

	public Ordinazione(int idTavolo, ArrayList<ProdottoOrdinato> ordini) {
		
		this.idOrdinazione = System.currentTimeMillis(); // Utilizzo il timestamp per avere un ordine temporale delle ordianazioni
		this.idTavolo = idTavolo;
		this.ordini = ordini;
		this.setStatoTuttiProdotti(StatoProdottoOrdinato.ORDINATO);
	}

	public float getContoParziale() {

		float totale = 0;
		for (ProdottoOrdinato p : ordini) {
			totale = p.getCostoParziale() + totale;
		}
		return totale;
	}

	@Override
	public boolean aggiungiOrdini(Prodotto prodotto, int quantita) {
		try {
			this.ordini.add(new ProdottoOrdinato(prodotto, quantita));
		} catch (OrdinazioneNegativaException e) {
			e.getMessage();
		}

		return true;
	}

	public int getIdTavolo() {
		return idTavolo;
	}

	public long getIdOrdinazione() {
		return idOrdinazione;
	}

	public ArrayList<ProdottoOrdinato> getOrdini() {
		return ordini;
	}

	public void eliminaProdotto(Prodotto p) throws EliminaProdNonOrdException {
		// TODO: fix bug, ordini è un ArrayList di ProdottiOrdinati. ProdottoOrdinato non è una classe che estende Prodotto
		if (!ordini.contains(p)) {
			throw new EliminaProdNonOrdException();
		}

		for (ProdottoOrdinato prodotti : ordini) {
			if (prodotti.getProdotto().equals(p)) {
				ordini.remove(prodotti);
			}
		}
	}
	
	public ArrayList<ProdottoOrdinato> getProdottiOrdinati(StatoProdottoOrdinato statoProdottoOrdinato) {
		ArrayList<ProdottoOrdinato> listaProdotti = new ArrayList<>();
		
		for(ProdottoOrdinato prodottoOrdinato : this.ordini) {
			if(prodottoOrdinato.getStato() == statoProdottoOrdinato) {
				listaProdotti.add(prodottoOrdinato);
			}
		}
		
		return listaProdotti;
	}
	
	public ArrayList<ProdottoOrdinato> getProdottiOrdinati(TipoProdotto tipoProdotto) {
		ArrayList<ProdottoOrdinato> listaProdotti = new ArrayList<>();
		
		for(ProdottoOrdinato prodottoOrdinato : this.ordini) {
			if(prodottoOrdinato.getProdotto().getTipo() == tipoProdotto) {
				listaProdotti.add(prodottoOrdinato);
			}
		}
		
		return listaProdotti;
	}
	
	public void setStatoTuttiProdotti(StatoProdottoOrdinato statoProdottoOrdinato) {
		for(ProdottoOrdinato prodottoOrdinato : this.ordini) {
			prodottoOrdinato.setStato(statoProdottoOrdinato);
		}
	}
}
