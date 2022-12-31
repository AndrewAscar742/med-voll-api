package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.controller.dto.GetPacienteDto;
import med.voll.api.controller.dto.PacienteDto;
import med.voll.api.controller.dto.PutMedicoDto;
import med.voll.api.controller.dto.PutPacienteDto;
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
		return repository.findAllByAtivoTrue(paginacao).map(GetPacienteDto::new);
	}
	
	@PutMapping
	@Transactional
	public void alterarPaciente(@RequestBody @Valid PutPacienteDto pacienteForm) {
		Paciente paciente = repository.getReferenceById(pacienteForm.id());
		paciente.atualizarInformacoes(pacienteForm);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void excluirPaciente(@PathVariable Long id) {
		Paciente paciente = repository.getReferenceById(id);
		paciente.inativar();
	}
	
}
