package med.voll.api.controller.dto;

import med.voll.api.model.Paciente;

public record GetPacienteDto(Long id, String nome, String email, String cpf) {
	
	public GetPacienteDto(Paciente paciente) {
		this(paciente.getId() ,paciente.getNome(), paciente.getEmail(), paciente.getCpf());
	}
}
