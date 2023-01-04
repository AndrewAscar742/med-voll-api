package med.voll.api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import med.voll.api.model.Usuario;

@Service
public class TokenService {
	
	@Value(value = "{api.security.token}")
	private String token;

	public String gerarToken(Usuario usuario) {

		try {
			//Escolha do algoritmo pra gerar o Token
			var algoritmo = Algorithm.HMAC256(token);
			return JWT.create()
					.withIssuer("Voll.med") //quem é o dono que está utilizando
					.withSubject(usuario.getLogin()) //esse token deve pertencer há alguem
					.withExpiresAt(devolverDataExpiracao()) //esse token deve expirar
					.sign(algoritmo);
			
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro ao gerar o token", e);
		}

	}

	private Instant devolverDataExpiracao() {
		//Horário de Brasília
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
