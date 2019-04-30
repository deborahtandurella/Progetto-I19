package serverCentrale;

import ordinazioni.Ordinazione;
import ordinazioni.StatoOrdinazione;
import prodotti.ProdottoOrdinato;

import java.util.ArrayList;
import java.util.List;

public class ServerCentrale implements ServerCentraleInterface
{

    private List<Ordinazione> ordini;

    public ServerCentrale() {
        this.ordini  = new ArrayList<Ordinazione>();
    }

    @Override
    public boolean creaOrdinazione(int idTavolo) {
        Ordinazione ordinazione= new Ordinazione(idTavolo);
        ordini.add(ordinazione);
        return false;
    }

    @Override
    public float getConto(int idTavolo){
        
        return 0;
    }

    @Override
    public void eliminaOrdinazione(int idOrdinazione)
    {
        ordini.remove(idOrdinazione); // DA TESTARE
    }

    public Ordinazione inviaOrdineCucina(Ordinazione ordine)
    {
        ordine.setStato(StatoOrdinazione.ORDINATO);


        return ordine;
    }

}
