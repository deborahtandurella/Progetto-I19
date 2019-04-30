package serverCentrale;

import java.util.ArrayList;
import ordinazioni.Ordinazione;
import prodotti.Prodotto;

public interface ServerCentraleInterface
{
    public Ordinazione creaOrdinazione(int idTavolo);
    public float getConto(int idTavolo);
    public boolean inviaOrdine(Ordinazione ordinazione);
    public ArrayList<Ordinazione> getOrdiniInviati();
    public boolean eleminaOrdinazione(Ordinazione ordinazione);
    public void aggiungiProdottoMenu(Prodotto prodotto);
}
