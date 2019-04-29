package serverCentrale;

import ordinazioni.Ordinazione;
import ordinazioni.StatoOrdinazione;
import prodotti.ProdottoOrdinato;

public class ServerCentrale implements InterfaceServerCentrale
{

    @Override
    public boolean creaOrdinazione(int idTavolo) {
        Ordinazione ordinazione= new Ordinazione(idTavolo);
        return false;
    }

    @Override
    public float getConto(int idTavolo){

        return 0;
    }

    @Override
    public void eliminaOrdinazione(int idOrdinazione) {

    }

    @Override
    public Ordinazione inviaOrdineCucina(Ordinazione ordine)
    {
        ordine.setStato(StatoOrdinazione.ORDINATO);
        return null;
    }

}
