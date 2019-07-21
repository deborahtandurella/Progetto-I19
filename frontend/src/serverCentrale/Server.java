package serverCentrale;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import eccezioni.ResetDatabaseException;

public class Server {
	
	protected RestTemplate restTemplate;
	protected ApiURL apiURL;
	
	public Server(boolean test) {
		this.restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		this.apiURL = new ApiURL(test);
	}
	
	public void resetDatabase() {
		ResponseEntity<Void> ret = this.restTemplate.exchange(apiURL.getResetDatabase(), HttpMethod.GET, null, 
				new ParameterizedTypeReference<Void>() {
				});
		
		if(ret.getStatusCode() == HttpStatus.METHOD_NOT_ALLOWED) {
			 throw new ResetDatabaseException();
		}
	}
}
