package serverCentrale;

import prodotti.ProdottoOrdinato;
import ordinazioni.Ordinazione;

public interface InterfaceServerCentrale
{
    public boolean creaOrdinazione(idTavolo, ProdottoOrdinato p);

    public float getConto(idTavolo);

    public Ordinazione inviaOrdineCucina();

    public void eliminaOrdinazione(idOrdinazione);
}
