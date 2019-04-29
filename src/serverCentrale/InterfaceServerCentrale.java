package serverCentrale;

import prodotti.ProdottoOrdinato;
import ordinazioni.Ordinazione;

public interface InterfaceServerCentrale
{
    public boolean creaOrdinazione(int idTavolo);

    public float getConto(int idTavolo);

    public Ordinazione inviaOrdineCucina(Ordinazione ordine);

    public void eliminaOrdinazione(int idOrdinazione);
}
