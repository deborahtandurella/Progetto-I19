package ordinazioni;

import eccezioni.EliminaProdNonOrdException;
import eccezioni.OrdinazioneNegativaException;
import eccezioni.PrezzoNegativoException;
import prodotti.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import static ordinazioni.StatoOrdinazione.ORDINATO;

public class Ordinazione implements OrdinazioneInterface {

    private int idTavolo;
    private String idOrdinazione;
    private StatoOrdinazione stato;
    private ArrayList<ProdottoOrdinato> ordini;
    private LocalDateTime tempoInizioOrdinato;

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
    public boolean aggiungiOrdini(Prodotto prodotto, int quantita)   {

        try {

            this.ordini.add(new ProdottoOrdinato(prodotto, quantita));

        } catch (OrdinazioneNegativaException e) {
            e.printStackTrace();
        }

        return true;
    }

	public int getIdTavolo() {
		return idTavolo;
	}

	public String getIdOrdinazione() {
		return idOrdinazione;
	}

	public StatoOrdinazione getStato() {
		return stato;
	}

	public ArrayList<ProdottoOrdinato> getOrdini() {
		return ordini;
	}

	public void eliminaProdotto(Prodotto p) throws EliminaProdNonOrdException
    {
        if(stato == null  ) {
            throw new EliminaProdNonOrdException();
        }

        for (ProdottoOrdinato prodotti : ordini)
        {
            if(prodotti.getProdotto().equals(p))
            {
                ordini.remove(prodotti);
            }
        }
    }

    public void setTempoInizioOrdinato() {
        this.tempoInizioOrdinato = LocalDateTime.now();
    }
}
