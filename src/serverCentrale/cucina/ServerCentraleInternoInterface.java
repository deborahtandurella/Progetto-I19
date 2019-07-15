package serverCentrale.cucina;

import java.util.List;

import prodotti.prodotto_ordinato.ProdottoOrdinato;
import prodotti.prodotto_ordinato.StatoProdottoOrdinato;
import prodotti.prodotto.TipoProdotto;

public interface ServerCentraleInternoInterface {

	public List<ProdottoOrdinato> getOrdini(TipoProdotto tipoProdotto);
	public List<ProdottoOrdinato> getOrdini(TipoProdotto tipoProdotto, StatoProdottoOrdinato statoProdottoOrdinato);
	public ProdottoOrdinato changeStatoProdottoOrdinato(ProdottoOrdinato prodottoOrdinato, StatoProdottoOrdinato statoProdottoOrdinato);
	public List<Integer> getTavoli();
	public List<Integer> getTavoli(StatoProdottoOrdinato statoProdottoOrdinato);
	public List<Integer> getTavoli(StatoProdottoOrdinato statoProdottoOrdinato, TipoProdotto tipoProdotto);
	
}
