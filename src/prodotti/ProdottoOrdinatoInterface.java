package prodotti;

public interface ProdottoOrdinatoInterface {

	public float getCostoParziale();
	public void addQuantita();
	public void setStatoProdottoOrdinatoLavorazione();
	public int getTempoElaborazioneRimanente(int max);
}
