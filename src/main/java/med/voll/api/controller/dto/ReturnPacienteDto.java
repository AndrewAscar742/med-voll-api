package med.voll.api.controller.dto;

import med.voll.api.model.Endereco;
import med.voll.api.model.Paciente;

public record ReturnPacienteDto(Long id, 
		String nome, 
		String email, 
		String telefone, 
		String cpf, 
		Endereco endereco) {

	public ReturnPacienteDto(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), 
				paciente.getCpf(), paciente.getEndereco());
	}

}
