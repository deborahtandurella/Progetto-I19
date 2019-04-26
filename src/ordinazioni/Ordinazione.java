
import java.util.ArrayList;
import java.util.List;

public class Ordinazione implements OrdinazineInterface  extends StatoOrdinazione{

    private int idTavolo;
    private int id;
    private ordinazioni.StatoOrdinazione stato;
    private List<prodotti.ProdottoOrdinato> ordini;

    public Ordinazione(int idTavolo, int id, ordinazioni.StatoOrdinazione stato, List<ProdottoOrdinato> ordini){

        this.id = id;
        this.idTavolo = idTavolo;
        this.stato = stato;
        this.ordini = new ArrayList<ProdottoOrdinato>;
    }

    public boolean AggiungiOrdini(prodotti.Prodotto prodotto, int quantita){

        ordini.add(new ProdottoOrdinato(prodotto, quantita));

    }


}
