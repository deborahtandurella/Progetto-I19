package serverCentrale;

import prodotti.ProdottoOrdinato;

public interface InterfaceServerCentrale
{
    public boolean creaOrdinazione(idTavolo, ProdottoOrdinato);

    public float getConto(idTavolo);

    public Ordinazione inviaOrdineCucina();

    public void eliminaOrdinazione(idOrdinazione);
}
