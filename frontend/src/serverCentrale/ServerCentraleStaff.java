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

import prodotti.prodotto_ordinato.ProdottoOrdinato;
import prodotti.prodotto_ordinato.StatoProdottoOrdinato;
import prodotti.prodotto.TipoProdotto;

public class ServerCentraleStaff extends Server implements ServerCentraleStaffInterface {
	
	public ServerCentraleStaff() {
		super(false);
	}
	
	public ServerCentraleStaff(boolean test) {
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

	private List<Integer> getTavoli(String url) {
		ResponseEntity<List<Integer>> ret = restTemplate.exchange(url, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Integer>>() {
				});

		return ret.getBody();
	}
	
	@Override
	public List<Integer> getTavoli() {
		return this.getTavoli(super.apiURL.getIDTavolo());
	}
	
	@Override
	public List<Integer> getTavoli(StatoProdottoOrdinato statoProdottoOrdinato) {
		System.out.println(super.apiURL.getIDTavolo());
		UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromHttpUrl(super.apiURL.getIDTavolo())
				.queryParam("statoProdottoOrdinato", statoProdottoOrdinato.value());
		
		return this.getTavoli(queryBuilder.toUriString());
	}
	
	@Override
	public List<Integer> getTavoli(StatoProdottoOrdinato statoProdottoOrdinato, TipoProdotto tipoProdotto) {
		UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromHttpUrl(super.apiURL.getIDTavolo())
				.queryParam("statoProdottoOrdinato", statoProdottoOrdinato.value())
				.queryParam("tipo", tipoProdotto.value());
		
		return this.getTavoli(queryBuilder.toUriString());
	}
	
}
