package client;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import application.security.jwt.JWTRequest;
import application.security.jwt.JWTResponse;

public class SecurityServiceProxyImpl {

	private static final String URL = "http://localhost:8080";
	private RestTemplate restTemplate = new RestTemplate();
	
	public String createToken(JWTRequest jwtRequest) {
		RequestEntity<?> request = null;
		try {
			
			request = RequestEntity.post(new URI(URL + "/jwt/authenticate"))
					.header("Content-Type", "application/json")
					.body(jwtRequest);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return restTemplate.exchange(request, JWTResponse.class).getBody().getJwttoken();
	} 
	
}
