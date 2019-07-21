package prodotti.prodotto_ordinato;

public interface ProdottoOrdinatoInterface {

	float getCostoParziale();
	void addQuantita();
	void setStatoProdottoOrdinatoLavorazione();
	int getTempoElaborazioneRimanente(int max);
}
