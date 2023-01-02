package med.voll.api.controller.dto.form;

import jakarta.validation.Valid;

public record PutPacienteDto(Long id,
	    String nome,
	    String telefone,
	    @Valid EnderecoDto endereco) {

}
