package serverCentrale;

import java.util.ArrayList;
import java.util.List;

import eccezioni.NessunOrdineException;
import eccezioni.NessunProdottoException;
import eccezioni.ProdottoNonConsegnatoException;
import ordinazioni.Ordinazione;
import prodotti.Prodotto;
import prodotti.ProdottoOrdinato;
import prodotti.TipoProdotto;

public interface ServerCentraleInterface
{
    public float getConto(int idTavolo) throws NessunOrdineException, ProdottoNonConsegnatoException;
    public List<ProdottoOrdinato> inviaOrdine(ArrayList<ProdottoOrdinato> ordini) throws NessunProdottoException;
    public boolean eleminaOrdinazione(long idOrdinazione);
    public void aggiungiProdottoMenu(Prodotto prodotto);
    public ArrayList<Ordinazione> getOrdini(TipoProdotto tipoProdotto);
    public void consegnaProdotto(ProdottoOrdinato prodottoOrdinato);
    public void lavoraProdotto(ProdottoOrdinato prodottoOrdinato);
}
