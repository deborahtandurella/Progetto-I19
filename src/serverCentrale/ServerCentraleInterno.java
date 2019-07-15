package serverCentrale;

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

import prodotti.ProdottoOrdinato;
import prodotti.StatoProdottoOrdinato;
import prodotti.TipoProdotto;

public class ServerCentraleInterno extends Server implements ServerCentraleInternoInterface{
	
	public ServerCentraleInterno() {
		super(false);
	}
	
	public ServerCentraleInterno(boolean test) {
		super(test);
	}
	
	@Override
	public List<ProdottoOrdinato> getOrdini(TipoProdotto tipoProdotto) {

		UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromHttpUrl(super.apiURL.getProdottoOrdinato())
				.queryParam("prodotto__tipo", tipoProdotto.value());

		return this.getOrdini(queryBuilder.toUriString());
	}
	
	@Override
	public List<ProdottoOrdinato> getOrdini(TipoProdotto tipoProdotto, StatoProdottoOrdinato statoProdottoOrdinato) {
		UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromHttpUrl(super.apiURL.getProdottoOrdinato())
				.queryParam("prodotto__tipo", tipoProdotto.value())
				.queryParam("statoProdottoOrdinato", statoProdottoOrdinato.value());

		return this.getOrdini(queryBuilder.toUriString());
	}

	private List<ProdottoOrdinato> getOrdini(String url) {
		
		ResponseEntity<List<ProdottoOrdinato>> ret = restTemplate.exchange(url, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<ProdottoOrdinato>>() {
				});

		return ret.getBody();
	}
	
	@Override
	public ProdottoOrdinato changeStatoProdottoOrdinato(ProdottoOrdinato prodottoOrdinato, StatoProdottoOrdinato statoProdottoOrdinato) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(statoProdottoOrdinato.toJsonString(), headers);
		
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		ResponseEntity<ProdottoOrdinato> response = restTemplate.exchange(super.apiURL.getProdottoOrdinato() + prodottoOrdinato.getId() + ".json",
				HttpMethod.PATCH,
				entity,
				new ParameterizedTypeReference<ProdottoOrdinato>() {});
		return response.getBody();
	}

	@Override
	public List<Integer> getTavoli() {
		ResponseEntity<List<Integer>> ret = restTemplate.exchange(super.apiURL.getIDTavolo(), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Integer>>() {
				});

		return ret.getBody();
	}
	
	@Override
	public List<Integer> getTavoli(StatoProdottoOrdinato statoProdottoOrdinato) {
		System.out.println(super.apiURL.getIDTavolo());
		UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromHttpUrl(super.apiURL.getIDTavolo())
				.queryParam("statoProdottoOrdinato", statoProdottoOrdinato.value());
		
		ResponseEntity<List<Integer>> ret = restTemplate.exchange(queryBuilder.toUriString(), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Integer>>() {
				});

		return ret.getBody();
	}
	
	@Override
	public List<Integer> getTavoli(StatoProdottoOrdinato statoProdottoOrdinato, TipoProdotto tipoProdotto) {
		UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromHttpUrl(super.apiURL.getIDTavolo())
				.queryParam("statoProdottoOrdinato", statoProdottoOrdinato.value())
				.queryParam("tipo", tipoProdotto.value());
		
		ResponseEntity<List<Integer>> ret = restTemplate.exchange(queryBuilder.toUriString(), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Integer>>() {
				});

		return ret.getBody();
	}
	
}
