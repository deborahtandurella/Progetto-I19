package serverCentrale;

import eccezioni.NessunOrdineException;
import eccezioni.NessunProdottoException;
import eccezioni.ProdottoNonConsegnatoException;
import ordinazioni.ListaOrdinazioni;
import ordinazioni.Ordinazione;
import prodotti.Prodotto;
import prodotti.ProdottoOrdinato;
import prodotti.StatoProdottoOrdinato;
import prodotti.TipoProdotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ServerCentrale implements ServerCentraleInterface {

	private ListaOrdinazioni listaOrdinazioni;
	private ArrayList<Prodotto> menu;

	public ServerCentrale() {
		this.listaOrdinazioni = new ListaOrdinazioni();
		this.menu = new ArrayList<>();
	}

	@Override
	public float getConto(int idTavolo) throws NessunOrdineException, ProdottoNonConsegnatoException {
		float totale = 0;

		if (this.listaOrdinazioni.getElementsByIdTavolo(idTavolo).isEmpty()) {
			throw new NessunOrdineException();
		}

		for (Ordinazione ordine : this.listaOrdinazioni.getElementsByIdTavolo(idTavolo)) {
			for (ProdottoOrdinato p : ordine.getOrdini()) {
				if (p.getStato() != StatoProdottoOrdinato.CONSEGNATO) {
					throw new ProdottoNonConsegnatoException();
				}
			}
			totale += ordine.getContoParziale();

		}
		return totale;
	}

	// L'ORDINAZIONE VIENE CREATA QUANDO VENGONO INVIATI IN CUCINA TUTTI I PRODOTTI

	@Override
	public long inviaOrdine(int idTavolo, ArrayList<ProdottoOrdinato> ordini)
			throws NessunProdottoException {

		Ordinazione ordinazione = new Ordinazione(idTavolo, ordini);

		if (ordinazione.getOrdini().isEmpty()) {
			throw new NessunProdottoException();
		}
		
		this.listaOrdinazioni.put(ordinazione.getIdOrdinazione(), ordinazione);
		return ordinazione.getIdOrdinazione();
	}

	public String getStatoOrdinazione(long idOrdinazione) {
		Ordinazione ordinazione = this.listaOrdinazioni.get(idOrdinazione);
		return ordinazione.getStatoProdottiOrdinati();
	}
	
	@Override
	public ArrayList<Ordinazione> getOrdini(TipoProdotto tipoProdotto) {
	 	ArrayList<Ordinazione> listaOrdinazione = new ArrayList<>();
	 	
	 	Ordinazione[] array_ordinazioni = (Ordinazione[]) this.listaOrdinazioni.values().toArray();
	 	Arrays.sort(array_ordinazioni, new Comparator<Ordinazione>() { // Ordino in ordine cronologico dato che l'ID dell'ordine è un timestamp

			@Override
			public int compare(Ordinazione o1, Ordinazione o2) {
				
				return Long.compare(o1.getIdOrdinazione(), o2.getIdOrdinazione());
			}
		});
	 	
	 	for(Ordinazione ordinazione : array_ordinazioni) {
	 		if(ordinazione.hasAllProdottiConsegnati(tipoProdotto)) {
	 			listaOrdinazione.add(ordinazione);
	 		}
	 	}
	 	
	 	return listaOrdinazione;
	 }

	@Override
	public void consegnaProdotto(ProdottoOrdinato prodottoOrdinato) {
		prodottoOrdinato.setStato(StatoProdottoOrdinato.CONSEGNATO);
	}
	@Override
	public void lavoraProdotto(ProdottoOrdinato prodottoOrdinato) {
		prodottoOrdinato.setStato(StatoProdottoOrdinato.LAVORAZIONE);
	}
	
	@Override
	public boolean eleminaOrdinazione(long idOrdinazione) {
		this.listaOrdinazioni.remove(idOrdinazione);
		return true;
	}

	public ArrayList<Prodotto> getMenu() {
		return menu;
	}

	@Override
	public void aggiungiProdottoMenu(Prodotto prodotto) {
		this.menu.add(prodotto);

	}

}
