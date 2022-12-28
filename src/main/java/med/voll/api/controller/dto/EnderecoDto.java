package med.voll.api.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDto(
		@NotBlank
		String logradouro,
		
		@NotBlank
		String bairro,
		
		@NotBlank
		String cep,
		
		@NotBlank
		String cidade,
		
		@NotBlank
		String uf,
		
		String complemento, 
		
		String numero) {

}
