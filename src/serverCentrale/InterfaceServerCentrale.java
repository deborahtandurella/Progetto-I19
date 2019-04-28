package serverCentrale;

import prodotti.ProdottoOrdinato;
import ordinazioni.Ordinazione;

public interface InterfaceServerCentrale
{
    public boolean creaOrdinazione(int idTavolo, ProdottoOrdinato p);

    public float getConto(int idTavolo);

    public Ordinazione inviaOrdineCucina();

    public void eliminaOrdinazione(int idOrdinazione);
}
