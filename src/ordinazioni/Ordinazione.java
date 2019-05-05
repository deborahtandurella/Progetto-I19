package ordinazioni;

import eccezioni.EliminaProdNonOrdException;
import eccezioni.OrdinazioneNegativaException;
import prodotti.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class Ordinazione implements OrdinazioneInterface {

	private int idTavolo;
	private String idOrdinazione;
	private ArrayList<ProdottoOrdinato> ordini;

	public Ordinazione(int idTavolo) {
		Random rand = new Random();
		int n = rand.nextInt(99) + 1;
		this.idOrdinazione = idTavolo + ":" + n;
		this.idTavolo = idTavolo;
		this.ordini = new ArrayList<>();
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

	public String getIdOrdinazione() {
		return idOrdinazione;
	}

	public ArrayList<ProdottoOrdinato> getOrdini() {
		return ordini;
	}

	public void eliminaProdotto(Prodotto p) throws EliminaProdNonOrdException {
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
	
	public void setStatoTuttiProdotti(StatoProdottoOrdinato statoProdottoOrdinato) {
		for(ProdottoOrdinato prodottoOrdinato : this.ordini) {
			prodottoOrdinato.setStato(statoProdottoOrdinato);
		}
	}
}
