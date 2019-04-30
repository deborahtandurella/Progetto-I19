package ordinazioni;

import java.util.ArrayList;
import java.util.HashMap;

import prodotti.ProdottoOrdinato;
import prodotti.TipoProdotto;

public class ListaOrdinazioni extends HashMap<String, Ordinazione>{
	
	public ListaOrdinazioni() {
		super();
	}
	
	public ArrayList<Ordinazione> getElementsByStatoOrdinazione(StatoOrdinazione statoOrdinazione) {
		ArrayList<Ordinazione> listaOrdinazioni = new ArrayList<>();
		for(Ordinazione ordinazione : this.values()) {
			if(ordinazione.getStato() == statoOrdinazione) {
				listaOrdinazioni.add(ordinazione);
			}
		}
		
		return listaOrdinazioni;
	}
	
	public ArrayList<Ordinazione> getElementsByIdTavolo(int idTavolo) {
		ArrayList<Ordinazione> listaOrdinazioni = new ArrayList<>();
		for(Ordinazione ordinazione : this.values()) {
			if(ordinazione.getIdTavolo() == idTavolo) {
				listaOrdinazioni.add(ordinazione);
			}
		}
		
		return listaOrdinazioni;
	}
	
	public ArrayList<ProdottoOrdinato> getElementsByTipoProdotto(TipoProdotto tipoProdotto) {
		ArrayList<ProdottoOrdinato> listaProdottiOrdinati = new ArrayList<>();
		for(Ordinazione ordinazione : this.values()) {
			for(ProdottoOrdinato prodottoOrdinato : ordinazione.getOrdini()) {
				if(prodottoOrdinato.getProdotto().getTipo() == tipoProdotto) {
					listaProdottiOrdinati.add(prodottoOrdinato);
				}
			}
		}
		
		return listaProdottiOrdinati;
	}
	
}
