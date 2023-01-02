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
import med.voll.api.controller.dto.GetMedicoDto;
import med.voll.api.controller.dto.ReturnMedicoDto;
import med.voll.api.controller.dto.form.MedicoDto;
import med.voll.api.controller.dto.form.PutMedicoDto;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<ReturnMedicoDto> cadastrarMedico(@RequestBody @Valid MedicoDto medicoForm, UriComponentsBuilder uriBuilder) {
		var medico = new Medico(medicoForm);
		repository.save(medico);
		
		var uri = uriBuilder.path("medicos/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(uri).body(new ReturnMedicoDto(medico));
	}
	
	@GetMapping
	public ResponseEntity<Page<GetMedicoDto>> listarMedico(@PageableDefault(size = 2, sort = {"nome"}) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(GetMedicoDto::new);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReturnMedicoDto> listarMedico(@PathVariable Long id){
		var medico = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new ReturnMedicoDto(medico));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<ReturnMedicoDto> atualizarMedico(@RequestBody PutMedicoDto medicoForm) {
		Medico medico = repository.getReferenceById(medicoForm.id());
		//a substituição no SGDB é automática
		medico.atualizarInformacao(medicoForm);
		
		return ResponseEntity.ok(new ReturnMedicoDto(medico));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> desativarMedico(@PathVariable Long id) {
		Medico medico = repository.getReferenceById(id);
		medico.desativar();
		
		return ResponseEntity.noContent().build();
	}
}
