package tests;

import java.util.ArrayList;

import ordinazioni.Ordinazione;
import prodotti.Prodotto;
import prodotti.ProdottoOrdinato;
import serverCentrale.ServerCentrale;

public class Test {

	public static void main(String[] args) {
		
		Prodotto p1= new Prodotto("pasta",10,"pasta carbonara");				//creo prodotti 
		Prodotto p2= new Prodotto("riso",12,"riso carnaroli");
		Prodotto p3= new Prodotto("carne",18,"carne rossa");
		
		ArrayList<Prodotto> a=new ArrayList<Prodotto>();				//creo arraylist per immissione prodotti
		a.add(p1);
		a.add(p2);											//aggiunta prodotti
		a.add(p3);
		
		ServerCentrale s = new ServerCentrale();     		//creo server centrale
					
		s.setMenu(a);										//creo menu
		
		Ordinazione o = s.creaOrdinazione(1);				//creo ordinazione tavolo 1
		
		o.aggiungiOrdini(p1,2);
		o.aggiungiOrdini(p3,2);								//aggiungo due ordini all'ordinazione
		
		s.inviaOrdine(o.getIdOrdinazione());
		
		for(Ordinazione o2 : s.getOrdiniInviati()){
			for(ProdottoOrdinato po : o2.getOrdini() ){
				System.out.println(po.getProdotto());
			}
		}
		
		Ordinazione o1=s.creaOrdinazione(1);				//creo altra ordinazione tavolo 1
		
		o1.aggiungiOrdini(p2,2);
		
		for(Ordinazione o2 : s.getOrdiniInviati()){
			for(ProdottoOrdinato po : o2.getOrdini() ){
				System.out.println(po.getProdotto());
			}
		}
	}

}
