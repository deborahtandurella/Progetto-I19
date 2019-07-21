package serverCentrale;

import java.util.List;

import prodotti.prodotto_ordinato.ProdottoOrdinato;
import prodotti.prodotto_ordinato.StatoProdottoOrdinato;
import prodotti.prodotto.TipoProdotto;

public interface ServerCentraleStaffInterface {

	List<ProdottoOrdinato> getOrdini(TipoProdotto tipoProdotto);
	List<ProdottoOrdinato> getOrdini(TipoProdotto tipoProdotto, StatoProdottoOrdinato statoProdottoOrdinato);
	ProdottoOrdinato changeStatoProdottoOrdinato(ProdottoOrdinato prodottoOrdinato, StatoProdottoOrdinato statoProdottoOrdinato);
	List<Integer> getTavoli();
	List<Integer> getTavoli(StatoProdottoOrdinato statoProdottoOrdinato);
	List<Integer> getTavoli(StatoProdottoOrdinato statoProdottoOrdinato, TipoProdotto tipoProdotto);
}
