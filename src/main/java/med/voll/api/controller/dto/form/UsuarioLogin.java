package med.voll.api.controller.dto.form;

import jakarta.validation.constraints.NotBlank;

public record UsuarioLogin(
		@NotBlank
		String login,
		@NotBlank
		String senha) {

}
