package serverCentrale;

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
import prodotti.Prodotto;
import prodotti.ProdottoOrdinato;
import prodotti.TipoPortata;

public class ServerCentraleEsterno implements ServerCentraleEsternoInterface{

	private RestTemplate restTemplate;
	
	public ServerCentraleEsterno() {
		this.restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	}
	
	public List<ProdottoOrdinato> inviaOrdine(ArrayList<ProdottoOrdinato> ordini) throws NessunProdottoException {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<List<ProdottoOrdinato>> entity = new HttpEntity<List<ProdottoOrdinato>>(ordini, headers);
		ResponseEntity<List<ProdottoOrdinato>> ret = restTemplate.exchange(ApiURL.PRODOTTO_ORDINATO, HttpMethod.POST,
				entity, new ParameterizedTypeReference<List<ProdottoOrdinato>>() {
				});
		return ret.getBody();

	}
	
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
	
}
