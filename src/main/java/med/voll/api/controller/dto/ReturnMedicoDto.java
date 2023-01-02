package med.voll.api.controller.dto;

import med.voll.api.model.Endereco;
import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;

/*
 * Record que é devolvido no método Put
 * 
 */
public record ReturnMedicoDto(Long id, 
		String nome, 
		String email, 
		String crm, 
		String telefone, Especialidade especialidade, Endereco endereco) {

	public ReturnMedicoDto(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getCrm(),
				medico.getEspecialidade(), medico.getEndereco());
	}

	
}
