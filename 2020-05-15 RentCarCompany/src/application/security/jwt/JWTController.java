package application.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/jwt")
public class JWTController {

	@Autowired AuthenticationManager authenticationManager;
	@Autowired JWTTokenUtil jwtTokenUtil;
	@Autowired UserDetailsService userDetailsService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createToken(@RequestBody JWTRequest jwtRequest){
		try{
			Authentication authentication = 
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
														jwtRequest.getLogin(), 
														jwtRequest.getPassword()));
		String token = jwtTokenUtil.generateToken(authentication);
		System.out.println(token + ".......................................");
		return ResponseEntity.ok(new JWTResponse(token));
		}catch (BadCredentialsException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong login-password pair");
		}
	}
	
	
	
}
