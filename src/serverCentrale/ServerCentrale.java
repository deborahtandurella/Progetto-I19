package serverCentrale;

import eccezioni.NessunOrdineException;
import eccezioni.NessunProdottoException;
import eccezioni.ProdottoNonConsegnatoException;
import ordinazioni.ListaOrdinazioni;
import ordinazioni.Ordinazione;
import prodotti.Prodotto;
import prodotti.ProdottoOrdinato;
import prodotti.StatoProdottoOrdinato;
import prodotti.TipoPortata;
import prodotti.TipoProdotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class ServerCentrale implements ServerCentraleInterface {

	private ListaOrdinazioni listaOrdinazioni;
	private ArrayList<Prodotto> menu;
	private RestTemplate restTemplate;

	public ServerCentrale() {
		this.listaOrdinazioni = new ListaOrdinazioni();
		this.menu = new ArrayList<>();
		this.restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	}

	@Override
	public float getConto(int idTavolo) throws NessunOrdineException, ProdottoNonConsegnatoException {
		float totale = 0;

		if (this.listaOrdinazioni.getElementsByIdTavolo(idTavolo).isEmpty()) {
			throw new NessunOrdineException();
		}

		for (Ordinazione ordine : this.listaOrdinazioni.getElementsByIdTavolo(idTavolo)) {
			for (ProdottoOrdinato p : ordine.getOrdini()) {
				if (p.getStato() != StatoProdottoOrdinato.CONSEGNATO) {
					throw new ProdottoNonConsegnatoException();
				}
			}
			totale += ordine.getContoParziale();

		}
		return totale;
	}

	@Override
	public List<ProdottoOrdinato> inviaOrdine(ArrayList<ProdottoOrdinato> ordini) throws NessunProdottoException {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<List<ProdottoOrdinato>> entity = new HttpEntity<List<ProdottoOrdinato>>(ordini, headers);
		ResponseEntity<List<ProdottoOrdinato>> ret = restTemplate.exchange(ApiURL.PRODOTTO_ORDINATO, HttpMethod.POST,
				entity, new ParameterizedTypeReference<List<ProdottoOrdinato>>() {
				});
		return ret.getBody();

	}

	public String getStatoOrdinazione(long idOrdinazione) {
		Ordinazione ordinazione = this.listaOrdinazioni.get(idOrdinazione);
		return ordinazione.getStatoProdottiOrdinati();
	}

	@Override
	public List<ProdottoOrdinato> getOrdini(TipoProdotto tipoProdotto) {

		UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromHttpUrl(ApiURL.PRODOTTO_ORDINATO)
				.queryParam("tipo", tipoProdotto.value());

		return this.getOrdini(queryBuilder.toUriString());
	}

	private List<ProdottoOrdinato> getOrdini(String url) {
		
		ResponseEntity<List<ProdottoOrdinato>> ret = restTemplate.exchange(url, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<ProdottoOrdinato>>() {
				});

		return ret.getBody();
	}
	
	@Override
	public void consegnaProdotto(ProdottoOrdinato prodottoOrdinato) {
		prodottoOrdinato.setStato(StatoProdottoOrdinato.CONSEGNATO);
	}

	@Override
	public void lavoraProdotto(ProdottoOrdinato prodottoOrdinato) {
		prodottoOrdinato.setStato(StatoProdottoOrdinato.LAVORAZIONE);
	}

	@Override
	public boolean eleminaOrdinazione(long idOrdinazione) {
		this.listaOrdinazioni.remove(idOrdinazione);
		return true;
	}

	@Override
	public List<Prodotto> getMenu() {
		return this.getMenu(ApiURL.PRODOTTO);
	}

	@Override
	public List<Prodotto> getMenu(TipoPortata tipoPortata) {

		UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromHttpUrl(ApiURL.PRODOTTO).queryParam("tipoPortata",
				tipoPortata.value());

		return this.getMenu(queryBuilder.toUriString());
	}

	private List<Prodotto> getMenu(String url) {
		ResponseEntity<List<Prodotto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Prodotto>>() {
				});
		List<Prodotto> menu = response.getBody();
		return menu;
	}

	@Override
	public void aggiungiProdottoMenu(Prodotto prodotto) {
		this.menu.add(prodotto);

	}
	

}
