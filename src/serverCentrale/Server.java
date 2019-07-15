package serverCentrale;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class Server {
	
	protected RestTemplate restTemplate;
	protected ApiURL apiURL;
	
	public Server(boolean test) {
		this.restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		this.apiURL = new ApiURL(test);
	}
	
	protected void resetDatabase() {
		this.restTemplate.exchange(apiURL.getResetDatabase(), HttpMethod.GET, null, 
				new ParameterizedTypeReference<Void>() {
				});
	}
}
