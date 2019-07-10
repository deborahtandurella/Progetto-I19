package serverCentrale;

import java.util.List;

import prodotti.ProdottoOrdinato;
import prodotti.StatoProdottoOrdinato;
import prodotti.TipoProdotto;

public interface ServerCentraleInternoInterface {

	public List<ProdottoOrdinato> getOrdini(TipoProdotto tipoProdotto);
	public List<ProdottoOrdinato> getOrdini(TipoProdotto tipoProdotto, StatoProdottoOrdinato statoProdottoOrdinato);
	public ProdottoOrdinato changeStatoProdottoOrdinato(ProdottoOrdinato prodottoOrdinato, StatoProdottoOrdinato statoProdottoOrdinato);
	public List<Integer> getTavoli();
	public List<Integer> getTavoli(StatoProdottoOrdinato statoProdottoOrdinato);
	
}
