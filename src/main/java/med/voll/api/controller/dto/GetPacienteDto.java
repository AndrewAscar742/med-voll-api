package med.voll.api.controller.dto;

import med.voll.api.model.Paciente;

public record GetPacienteDto(String nome, String email, String cpf) {
	
	public GetPacienteDto(Paciente paciente) {
		this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
	}
}
