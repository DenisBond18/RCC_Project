package client;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import application.security.jwt.JWTRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Value;
import view.ConsoleInputOutput;
import view.Item;
import view.Menu;

public class SecurityClientController {

	@Autowired SecurityServiceProxyImpl service;
	
	public SecurityClientController(SecurityServiceProxyImpl service) {
		super();
		this.service = service;
	}
	public void run() {		
		new Menu(
			"Hello My Friend",
			Item.of("Get Your Access", io -> {
				String[] data = io.readString("Enter Login Password").split(" ");
				String jwtResponse = service.createToken(new JWTRequest(data[0], data[1]));
				
				Claims claims = Jwts.parser().setSigningKey("12345").parseClaimsJws(jwtResponse).getBody();
				List<Map<String, String>> roles = claims.get("roles", List.class);
				List<SimpleGrantedAuthority> role = roles.stream()
						.map(r -> new SimpleGrantedAuthority(r.get("authority")))
						.collect(Collectors.toList());
				for(int i = 0; i < role.size(); i++) {
					switch (role.get(i).getAuthority()) {
					case "ROLE_ADMIN":
						new RentCarClientAdminController(new RentCarClientSecurityImpl(jwtResponse)).run();
						break;
					case "ROLE_BOSS":
						new RentCarClientBossController(new RentCarServiceProxyImpl(jwtResponse)).run();
						break;
					case "ROLE_MANAGER":
						new RentCarClientMenegerController(new RentCarServiceProxyImpl(jwtResponse)).run();
						break;
					default:
						break;
					}		
				}
			}),
			Item.exit())
	.perform(new ConsoleInputOutput());
	}
}
