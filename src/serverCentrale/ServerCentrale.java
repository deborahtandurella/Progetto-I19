package serverCentrale;

import ordinazioni.Ordinazione;

public class ServerCentrale implements InterfaceServerCentrale
{
    
    @Override
    public boolean creaOrdinazione() {
        return false;
    }

    @Override
    public float getConto() {
        return 0;
    }

    @Override
    public Ordinazione inviaOrdineCucina() {
        return null;
    }

    @Override
    public void eliminaOrdinazione() {

    }
}
