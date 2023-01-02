package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.controller.dto.GetPacienteDto;
import med.voll.api.controller.dto.ReturnPacienteDto;
import med.voll.api.controller.dto.form.PacienteDto;
import med.voll.api.controller.dto.form.PutMedicoDto;
import med.voll.api.controller.dto.form.PutPacienteDto;
import med.voll.api.model.Paciente;
import med.voll.api.repository.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	
	@Autowired
	PacienteRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<ReturnPacienteDto> cadastrarPaciente(@RequestBody @Valid PacienteDto pacienteForm, UriComponentsBuilder uriBuilder) {
		var paciente = new Paciente(pacienteForm);
		repository.save(paciente);
		
		var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ReturnPacienteDto(paciente));
	}
	
	@GetMapping
	public ResponseEntity<Page<GetPacienteDto>> listarPaciente(@PageableDefault(size = 2, sort = {"nome"}) Pageable paginacao){
		var paciente = repository.findAllByAtivoTrue(paginacao).map(GetPacienteDto::new);
		
		return ResponseEntity.ok(paciente);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReturnPacienteDto> listarPaciente(@PathVariable Long id){
		var paciente = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new ReturnPacienteDto(paciente));
	}
	
	@PutMapping
	@Transactional
	public void alterarPaciente(@RequestBody @Valid PutPacienteDto pacienteForm) {
		Paciente paciente = repository.getReferenceById(pacienteForm.id());
		paciente.atualizarInformacoes(pacienteForm);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluirPaciente(@PathVariable Long id) {
		Paciente paciente = repository.getReferenceById(id);
		paciente.inativar();
		
		return ResponseEntity.noContent().build();
	}
	
}
