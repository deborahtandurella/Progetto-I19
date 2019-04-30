package ordinazioni;

import prodotti.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ordinazione implements OrdinazioneInterface {

    private int idTavolo;
    private String idOrdinazione;
    private StatoOrdinazione stato;
    private ArrayList<ProdottoOrdinato> ordini;

    public Ordinazione(int idTavolo){
    	
    	Random rand = new Random();
    	int n=rand.nextInt(99)+1;
        this.idOrdinazione = idTavolo+":"+n;
        this.idTavolo = idTavolo;
        this.stato = null;
        this.ordini  = new ArrayList<ProdottoOrdinato>();
    }
    
    public float getContoParziale(){
    	
    	float totale=0;
    	for (ProdottoOrdinato p : ordini){
    		totale=p.getCostoParziale()+totale;
    	}
    	return totale;
    }

    public void setStato(StatoOrdinazione stato) {
        this.stato = stato;
    }

    @Override
    public boolean aggiungiOrdini(Prodotto prodotto, int quantita) {
        this.ordini.add(new ProdottoOrdinato(prodotto, quantita));
        return true;
    }

	


}
