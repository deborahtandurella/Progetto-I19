package serverCentrale;

import ordinazioni.Ordinazione;
import ordinazioni.StatoOrdinazione;
import prodotti.Prodotto;
import java.util.ArrayList;

public class ServerCentrale implements ServerCentraleInterface {

    private ArrayList<Ordinazione> listaOrdinazioni;
    private ArrayList<Prodotto> menu;

    public ServerCentrale() {
		listaOrdinazioni=new ArrayList<>();
		menu=new ArrayList<>();
	}
    
    @Override
	public Ordinazione creaOrdinazione(int idTavolo) {
    	Ordinazione ordinazione = new Ordinazione(idTavolo);
    	listaOrdinazioni.add(ordinazione);
		return ordinazione;
	}

	@Override
	public float getConto(int idTavolo) {
		float totale = 0;
		for(Ordinazione ordine : this.listaOrdinazioni) {
			if(ordine.getIdTavolo() == idTavolo) {
				totale += ordine.getContoParziale();
			}
		}
		return totale;
	}

	@Override
	public boolean inviaOrdine(Ordinazione ordinazione) {
		ordinazione.setStato(StatoOrdinazione.ORDINATO);
		return true;
	}

	@Override
	public ArrayList<Ordinazione> getOrdiniInviati() {
		ArrayList<Ordinazione> ordiniInviati = new ArrayList<Ordinazione>();
		for(Ordinazione ordine : this.listaOrdinazioni) {
			if(ordine.getStato() == StatoOrdinazione.ORDINATO) {
				ordiniInviati.add(ordine);
			}
		}
		return ordiniInviati;
	}

	@Override
	public boolean eleminaOrdinazione(Ordinazione ordinazione) {
		this.listaOrdinazioni.remove(ordinazione);
		return true;
	}
    
	public ArrayList<Ordinazione> getListaOrdinazioni() {
		return listaOrdinazioni;
	}

	public void setListaOrdinazioni(ArrayList<Ordinazione> listaOrdinazioni) {
		this.listaOrdinazioni = listaOrdinazioni;
	}

	public ArrayList<Prodotto> getMenu() {
		return menu;
	}

	public void setMenu(ArrayList<Prodotto> menu) {
		this.menu = menu;
	}
}
