package med.voll.api.config.validation;

import org.springframework.validation.FieldError;

public record ErrosDto(String campo, String mensagem) {
	
	public ErrosDto(FieldError erros) {
		this(erros.getField(), erros.getDefaultMessage());
	}
}
