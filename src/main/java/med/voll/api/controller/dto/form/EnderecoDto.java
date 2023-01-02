package med.voll.api.controller.dto.form;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Sony
 * 
 * Usado no verbo PUT para poder realizar a alteração com segurança
 */
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
