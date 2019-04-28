package serverCentrale;

import ordinazioni.Ordinazione;
import prodotti.ProdottoOrdinato;

public class ServerCentrale implements InterfaceServerCentrale
{

    @Override
    public boolean creaOrdinazione(int idTavolo, ProdottoOrdinato p) {
        return false;
    }

    @Override
    public float getConto(int idTavolo) {
        return 0;
    }

    @Override
    public void eliminaOrdinazione(int idOrdinazione) {

    }

    @Override
    public Ordinazione inviaOrdineCucina() {
        return null;
    }

}
