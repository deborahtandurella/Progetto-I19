package serverCentrale;

import ordinazioni.Ordinazione;
import prodotti.Prodotto;
import java.util.ArrayList;

public class ServerCentrale implements ServerCentraleInterface {

    private ArrayList<Ordinazione> listaOrdinazioni;
    private ArrayList<Prodotto> menu;

    @Override
	public Ordinazione creaOrdinazione(int idTavolo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getConto(int idTavolo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean inviaOrdine(Ordinazione ordinazione) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Ordinazione> getOrdiniCucina() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Ordinazione> getOrdiniCaffetteria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eleminaOrindazione(Ordinazione ordinazione) {
		// TODO Auto-generated method stub
		return false;
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
