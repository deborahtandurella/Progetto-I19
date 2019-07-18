package serverCentrale;

import java.util.ArrayList;
import java.util.List;

import eccezioni.NessunProdottoException;
import prodotti.prodotto.Prodotto;
import prodotti.prodotto_ordinato.ProdottoOrdinato;
import prodotti.prodotto.TipoPortata;

public interface ServerCentraleClienteInterface {

	public List<ProdottoOrdinato> inviaOrdine(ArrayList<ProdottoOrdinato> ordini) throws NessunProdottoException;
	public List<Prodotto> getMenu(TipoPortata tipoPortata);
	public List<Prodotto> getMenu();
	public float getConto(int idTavolo);
	public List<ProdottoOrdinato> getOrdini(int idTavolo);
}
