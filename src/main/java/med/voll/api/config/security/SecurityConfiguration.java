package med.voll.api.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/*
 * Classe de configuração e segurança
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	/*
	 * Não será mais gerado o formulário de login e senha, quem faz isso é a nossa
	 * aplicação mobile no front-end. E, também, não bloqueará mais a URL.
	 */
	@Bean
	public SecurityFilterChain securiFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.build();
	}
	
	/*Para realmenter conseguir um autenticador, a classe configuração tem que ter um método, 
	 * dando esse objeto
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) 
			throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	//A senha vai ser criptografa quando for salvar no banco
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
