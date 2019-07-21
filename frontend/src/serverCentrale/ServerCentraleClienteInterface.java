package serverCentrale;

import java.util.ArrayList;
import java.util.List;

import eccezioni.NessunProdottoException;
import prodotti.prodotto.Prodotto;
import prodotti.prodotto_ordinato.ProdottoOrdinato;
import prodotti.prodotto.TipoPortata;

public interface ServerCentraleClienteInterface {

	List<ProdottoOrdinato> inviaOrdine(ArrayList<ProdottoOrdinato> ordini) throws NessunProdottoException;
	List<Prodotto> getMenu(TipoPortata tipoPortata);
	List<Prodotto> getMenu();
	float getConto(int idTavolo);
	List<ProdottoOrdinato> getOrdini(int idTavolo);
}
