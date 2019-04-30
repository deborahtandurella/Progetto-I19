package serverCentrale;

import java.util.ArrayList;
import ordinazioni.Ordinazione;

public interface ServerCentraleInterface
{
    public Ordinazione creaOrdinazione(int idTavolo);
    public float getConto(int idTavolo);
    public boolean inviaOrdine(Ordinazione ordinazione);
    public ArrayList<Ordinazione> getOrdiniCucina();
    public ArrayList<Ordinazione> getOrdiniCaffetteria();
    public boolean eleminaOrindazione(Ordinazione ordinazione);
    
}