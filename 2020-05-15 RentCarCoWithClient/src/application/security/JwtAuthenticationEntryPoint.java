package application.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.addHeader("www-authenticate", "Basic");//to permit browser auth popup
		String msg = (String) request.getAttribute("wrong token");
		if(msg != null) response.sendError(HttpServletResponse.SC_UNAUTHORIZED, msg);
		else response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthenticated: " + authException.getMessage());
		
	}

}
