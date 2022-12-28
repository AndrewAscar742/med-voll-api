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
import med.voll.api.controller.dto.GetMedicoDto;
import med.voll.api.controller.dto.MedicoDto;
import med.voll.api.controller.dto.PutMedicoDto;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrarMedico(@RequestBody @Valid MedicoDto medicoForm) {
		repository.save(new Medico(medicoForm));
	}
	
	@GetMapping
	public Page<GetMedicoDto> listarMedico(@PageableDefault(size = 2, sort = {"nome"}) Pageable paginacao) {
		return repository.findAllByAtivoTrue(paginacao).map(GetMedicoDto::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizarMedico(@RequestBody PutMedicoDto medicoForm) {
		Medico medico = repository.getReferenceById(medicoForm.id());
		//a substituição no SGDB é automática
		medico.atualizarInformacao(medicoForm);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void desativarMedico(@PathVariable Long id) {
		Medico medico = repository.getReferenceById(id);
		medico.desativar();
	}
}
