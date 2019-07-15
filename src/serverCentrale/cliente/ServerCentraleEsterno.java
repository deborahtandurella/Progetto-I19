package serverCentrale.cliente;

import java.util.ArrayList;
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

import eccezioni.NessunProdottoException;
import prodotti.prodotto.Prodotto;
import prodotti.prodotto_ordinato.ProdottoOrdinato;
import prodotti.prodotto.TipoPortata;
import serverCentrale.ApiURL;
import serverCentrale.Server;

public class ServerCentraleEsterno extends Server implements ServerCentraleEsternoInterface{
	
	public ServerCentraleEsterno() {
		super(false);
	}
	
	public ServerCentraleEsterno(boolean test) {
		super(test);
	}
	
	public List<ProdottoOrdinato> inviaOrdine(ArrayList<ProdottoOrdinato> ordini) throws NessunProdottoException {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<List<ProdottoOrdinato>> entity = new HttpEntity<List<ProdottoOrdinato>>(ordini, headers);
		ResponseEntity<List<ProdottoOrdinato>> ret = restTemplate.exchange(this.apiURL.getProdottoOrdinato(), HttpMethod.POST,
				entity, new ParameterizedTypeReference<List<ProdottoOrdinato>>() {
				});
		return ret.getBody();

	}
	
	public List<Prodotto> getMenu(TipoPortata tipoPortata) {

		UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromHttpUrl(this.apiURL.getProdotto()).queryParam("tipoPortata",
				tipoPortata.value());

		return this.getMenu(queryBuilder.toUriString());
	}
	
	public List<Prodotto> getMenu() {
		return this.getMenu(super.apiURL.getProdotto());
	}

	private List<Prodotto> getMenu(String url) {
		ResponseEntity<List<Prodotto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Prodotto>>() {
				});
		List<Prodotto> menu = response.getBody();
		return menu;
	}
	
	@Override
	public List<ProdottoOrdinato> getOrdini(int idTavolo) {
		UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromHttpUrl(this.apiURL.getProdottoOrdinato())
				.queryParam("idTavolo", idTavolo);
		ResponseEntity<List<ProdottoOrdinato>> ret = restTemplate.exchange(queryBuilder.toUriString(), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<ProdottoOrdinato>>() {
				});

		return ret.getBody();
	}
	
	@Override
	public float getConto(int idTavolo) {
		UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromHttpUrl(this.apiURL.getConto())
				.queryParam("idTavolo", idTavolo);
		ResponseEntity<Float> ret = restTemplate.exchange(queryBuilder.toUriString(), HttpMethod.GET,
				null, new ParameterizedTypeReference<Float>() {
				});

		return ret.getBody();
	}
	
}
