package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.controller.dto.ReturnToken;
import med.voll.api.controller.dto.form.UsuarioLogin;
import med.voll.api.model.Usuario;
import med.voll.api.service.TokenService;

@RestController
@RequestMapping("/login")
/*
 * Esse controller chama a classe que faz a autenticação
 */
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService jwtToken;
	
	@PostMapping
	public ResponseEntity<?> efetuarLogin(@RequestBody @Valid UsuarioLogin login){
		var dados = new UsernamePasswordAuthenticationToken(login.login(), login.senha());
		var authentication = manager.authenticate(dados);
		var token = jwtToken.gerarToken((Usuario) authentication.getPrincipal());
		
		return ResponseEntity.ok(new ReturnToken(token));
	}
}
