package client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import application.business.dto.CarDto;
import application.security.dto.AccountDto;
import application.security.dto.AuthPair;

public class RentCarClientSecurityImpl{

	private static final String URL = "http://localhost:8080";
	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";
	private RestTemplate restTemplate = new RestTemplate();
	private String token;
	
	
	public RentCarClientSecurityImpl(String token) {
		this.token = token;
	}


	public AccountDto addAccount(String login, String password, String role) {
		AuthPair authPair = new AuthPair(login, password);
		RequestEntity<?> request = null;
		try {
			request = RequestEntity.post(new URI(URL + "/security/add_account?role=" + role))
					.header(AUTHORIZATION, BEARER + token)
					.body(authPair);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return restTemplate.exchange(request, AccountDto.class).getBody();
	}

	
	public AccountDto grantRole(String login, String role) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public AccountDto depriveRole(String login, String role) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Set<String> getRolesByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<AccountDto> getAllAccounts() {
		ParameterizedTypeReference<ArrayList<AccountDto>> listAccounts 
		= new ParameterizedTypeReference<ArrayList<AccountDto>>() {};
		RequestEntity<?> request = null;
		try {
			request = RequestEntity.get(new URI(URL + "/security/get_all_accounts"))
					.header(AUTHORIZATION, BEARER + token)
					.build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return restTemplate.exchange(request, listAccounts).getBody();
	}

	
	public AccountDto removeAccount(String login) {
		// TODO Auto-generated method stub
		return null;
	}

}
