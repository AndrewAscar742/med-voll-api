package med.voll.api.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.repository.UsuarioRepository;
import med.voll.api.service.TokenService;

@Component
public class FiltrarToken extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;
	@Autowired
	private UsuarioRepository repository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var token = recuperarToken(request);

		if (token != null) {
			var tokenValidado = tokenService.validarToken(token);
			System.out.println(tokenValidado);
			//Aqui tem que fazer uma autenticação forçada para o Spring
			var usuario = repository.findByLogin(tokenValidado);

			var autenticador = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(autenticador);

		}

		filterChain.doFilter(request, response);

	}

	//Essa função retorna o token
	private String recuperarToken(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");
		
		if (authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
		}

		return null;
	}

}
