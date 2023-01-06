package med.voll.api.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import med.voll.api.filter.FiltrarToken;


/*
 * Classe de configuração e segurança
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Autowired
	private FiltrarToken filtrarToken;

	/*
	 * Não será mais gerado o formulário de login e senha, quem faz isso é a nossa
	 * aplicação mobile no front-end, além de bloquear as requisições por não confirmar a
	 * autenticação, deixando apenas o Login Livre
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and().authorizeHttpRequests()
	            .requestMatchers(HttpMethod.POST, "/login").permitAll()
	            .anyRequest().authenticated()
	            .and().addFilterBefore(filtrarToken, UsernamePasswordAuthenticationFilter.class)
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
