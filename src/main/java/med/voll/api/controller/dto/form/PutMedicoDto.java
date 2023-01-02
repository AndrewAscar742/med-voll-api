package med.voll.api.controller.dto.form;

import jakarta.validation.constraints.NotNull;

public record PutMedicoDto(
		@NotNull
		Long id,
		String nome,
		String telefone,
		EnderecoDto endereco
		) {

}
