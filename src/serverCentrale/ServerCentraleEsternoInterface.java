package serverCentrale;

import java.util.ArrayList;
import java.util.List;

import eccezioni.NessunProdottoException;
import prodotti.Prodotto;
import prodotti.ProdottoOrdinato;
import prodotti.TipoPortata;

public interface ServerCentraleEsternoInterface {

	
	public List<ProdottoOrdinato> inviaOrdine(ArrayList<ProdottoOrdinato> ordini) throws NessunProdottoException;
	public List<Prodotto> getMenu(TipoPortata tipoPortata);
}
