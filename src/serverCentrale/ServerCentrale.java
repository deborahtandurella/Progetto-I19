package serverCentrale;

import ordinazioni.ListaOrdinazioni;
import ordinazioni.Ordinazione;
import ordinazioni.StatoOrdinazione;
import prodotti.Prodotto;
import java.util.ArrayList;

public class ServerCentrale implements ServerCentraleInterface {

    private ListaOrdinazioni listaOrdinazioni;
    private ArrayList<Prodotto> menu;

    public ServerCentrale() {
    	this.listaOrdinazioni = new ListaOrdinazioni();
		this.menu = new ArrayList<>();
	}
    
    @Override
	public Ordinazione creaOrdinazione(int idTavolo) {
    	Ordinazione ordinazione = new Ordinazione(idTavolo);
    	listaOrdinazioni.put(ordinazione.getIdOrdinazione(), ordinazione);
		return ordinazione;
	}

	@Override
	public float getConto(int idTavolo) {
		float totale = 0;
		for(Ordinazione ordine : this.listaOrdinazioni.getElementsByIdTavolo(idTavolo)) {
			if(ordine.getIdTavolo() == idTavolo) {
				totale += ordine.getContoParziale();
			}
		}
		return totale;
	}

	@Override
	public boolean inviaOrdine(String idOrdinazione) {
		Ordinazione ordinazione = this.listaOrdinazioni.get(idOrdinazione);
		ordinazione.setStato(StatoOrdinazione.ORDINATO);
		return true;
	}

	@Override
	public ArrayList<Ordinazione> getOrdiniInviati() {
		ArrayList<Ordinazione> ordiniInviati = new ArrayList<Ordinazione>();
		for(Ordinazione ordine : this.listaOrdinazioni.getElementsByStatoOrdinazione(StatoOrdinazione.ORDINATO)) {		
			ordiniInviati.add(ordine);			
		}
		return ordiniInviati;
	}

	@Override
	public boolean eleminaOrdinazione(String idOrdinazione) {
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
