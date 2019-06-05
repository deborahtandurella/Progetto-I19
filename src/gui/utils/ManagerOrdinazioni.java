package gui.utils;

import gui.HomeController;
import ordinazioni.Ordinazione;
import prodotti.ProdottoOrdinato;

import java.util.ArrayList;

public class ManagerOrdinazioni {

    private static ArrayList<Ordinazione> ordinazioni = new ArrayList<>();
    private static ArrayList<ProdottoOrdinato> prodottiOrdinati = new ArrayList<>();

    static void addProdOrd(ProdottoOrdinato p){
        if(prodottiOrdinati.contains(p)){
            p.addQuantita();
        }
        else {
            prodottiOrdinati.add(p);
        }
    }

    public static void removeProdottoOrdinato(int id){
        prodottiOrdinati.remove(getPOrdById(id));
    }

    public static ProdottoOrdinato getPOrdById(int id){
        ProdottoOrdinato po = null;
        for(ProdottoOrdinato p : prodottiOrdinati){
            if(id == p.getProdotto().getId()){
                po = p;
            }
        }
        return po;
    }

    public ArrayList<Ordinazione> getOrdinazioni() {
        return ordinazioni;
    }

    public static ArrayList<ProdottoOrdinato> getProdottiOrdinati() {
        return prodottiOrdinati;
    }

    public void confermaOrdinazione(){
        ordinazioni.add(new Ordinazione(HomeController.getnTavolo(), prodottiOrdinati));
        prodottiOrdinati.clear();
    }

    public static int getNumProdOrd(){
        int tot = 0;
        for(ProdottoOrdinato p : prodottiOrdinati){
            tot += p.getQuantita();
        }
        return tot;
    }
}
