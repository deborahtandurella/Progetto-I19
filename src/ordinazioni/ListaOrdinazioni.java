package ordinazioni;

import java.util.ArrayList;
import java.util.HashMap;

import prodotti.ProdottoOrdinato;
import prodotti.StatoProdottoOrdinato;
import prodotti.TipoProdotto;


// Classe per la gestione delle Ordinazioni avente metodi che ritornano Ordinazioni in base a dei parametri di filtro
public class ListaOrdinazioni extends HashMap<Long, Ordinazione>{
	
	public ListaOrdinazioni() {
		super();
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
	
}