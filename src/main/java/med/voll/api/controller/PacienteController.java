package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.controller.dto.GetPacienteDto;
import med.voll.api.controller.dto.PacienteDto;
import med.voll.api.controller.dto.PutMedicoDto;
import med.voll.api.model.Paciente;
import med.voll.api.repository.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	
	@Autowired
	PacienteRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrarPaciente(@RequestBody @Valid PacienteDto pacienteForm) {
		repository.save(new Paciente(pacienteForm));
	}
	
	@GetMapping
	public Page<GetPacienteDto> listarPaciente(@PageableDefault(size = 2, sort = {"nome"}) Pageable paginacao){
		return repository.findAll(paginacao).map(GetPacienteDto::new);
	}
	
	
}
